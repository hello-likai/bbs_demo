package cn.bx.bbsdemo.config;

import cn.bx.bbsdemo.entity.LoginUser;
import cn.bx.bbsdemo.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;



@Component
public class LindTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private MyStore redisTemplate;
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    String tokenHead = "SANGE ";
    String tokenHeader = "Authorization";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);

        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            if (authToken != null && redisTemplate.hasKey(authToken)) {
                String token = (String)redisTemplate.get(authToken);

                Claims claims = JwtUtils.parseJWT(token, JwtUtils.key);
                //没有考虑claims等于null的情况
                Map map=claims.get("user", Map.class);

                String username=(String)(((Map)(map.get("user"))).get("name"));
                //可以不管，parse的时候已经考虑了失效的情况
                if(claims.getExpiration().before(new Date())){ //已经过期
                    throw  new RuntimeException("用户信息过期");
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        try {
            LoginUser user=(LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
