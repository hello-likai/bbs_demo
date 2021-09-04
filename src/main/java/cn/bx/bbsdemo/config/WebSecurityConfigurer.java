package cn.bx.bbsdemo.config;

import cn.bx.bbsdemo.handler.*;
import cn.bx.bbsdemo.utils.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * Security授权配置主文件
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("authUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private MyOncePerRequestFilter myOncePerRequestFilter;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    //登录成功处理器
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    //退出处理器
    @Autowired
    private MyLogoutHandler myLogoutHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;


    /**
     * 从容器中取出 AuthenticationManagerBuilder，执行方法里面的逻辑之后，放回容器
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoderUtil);
    }

    /**
     * 核心方法
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        //第2步：让Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        // 前后端分离之后，不需要用到session
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        //第3步：请求权限配置
        //放行的请求：注册API请求，其它任何请求都必须经过身份验证.
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/register").permitAll()
                //ROLE_ADMIN可以操作任何事情
                .antMatchers("/**").hasRole("ADMIN");
                //同等上一行代码
                //.antMatchers("/**").hasAuthority("ROLE_ADMIN")


        /**
         * 第4步：拦截账号、密码。覆盖 UsernamePasswordAuthenticationFilter过滤器
         * 这里使用自定义的拦截器，覆盖原来的UsernamePasswordAuthenticationFilter过滤器
         */
        http.addFilterAt(myUsernamePasswordAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class);

        //第5步：拦截token，并检测。在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
        http.addFilterBefore(myOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //第6步：处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);

        //第7步：登录,因为使用前端发送JSON方式进行登录，所以登录模式不设置也是可以的。
        http.formLogin();

        //第8步：退出
        http.logout().addLogoutHandler(myLogoutHandler).logoutSuccessHandler(myLogoutSuccessHandler);
    }

    /**
     * 手动注册账号、密码拦截器，用来覆盖原来自带的UsernamePasswordAuthenticationFilter拦截器
     * 同时指定自定义的，认证成功和失败的处理器
     */
    @Bean
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        // 这里可以点进去看
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        //成功后处理
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        //失败后处理
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}