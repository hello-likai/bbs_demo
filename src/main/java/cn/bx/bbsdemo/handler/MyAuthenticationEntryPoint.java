package cn.bx.bbsdemo.handler;

import cn.bx.bbsdemo.config.JSONAuthentication;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 身份校验失败处理器，如 token 错误
 */
@Component
public class MyAuthenticationEntryPoint extends JSONAuthentication implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        Map<String, Object> result = ResultUtils.getResult(ResultInfo.FAILED, "访问此资源需要完全身份验证");
        //输出
        this.WriteJSON(request, response, result);
    }
}
