package cn.bx.bbsdemo.handler;

import cn.bx.bbsdemo.config.JSONAuthentication;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 成功退出处理器
 */
@Component
public class MyLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        String username = user.getUsername();
        Map<String, Object> result = ResultUtils.getResult(ResultInfo.FAILED, "退出成功");
        super.WriteJSON(request,response,result);
    }
}
