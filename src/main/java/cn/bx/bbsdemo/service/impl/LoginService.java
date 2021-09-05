package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录service类
 */
@Service
public class LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    public LoginUser login(String username, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return (LoginUser) authentication.getPrincipal();//用户信息

    }
}
