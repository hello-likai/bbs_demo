package cn.bx.bbsdemo.controller;

import cn.bx.bbsdemo.config.JwtUtils;
import cn.bx.bbsdemo.config.MyStore;
import cn.bx.bbsdemo.entity.LoginUser;
import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.service.UserService;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("users")
@RestController
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MyStore redisTemplate;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 所有用户
     * @return
     */
    @ApiOperation(value = "查询所有用户")
    @GetMapping(value = "list")
    public Map<String,Object> getAllUsers(){
        List<User> list= userService.allUsers();

        return ResultUtils.getResult(ResultInfo.SUCCESS,"查询成功", list);
    }

    /**
     * 用户注册
     * @param user
     */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "register")
    public Map<String,Object> register(User user){
        // 使用security来加密密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        userService.register(user);
        return ResultUtils.getResult(ResultInfo.SUCCESS, "注册成功");
    }

    /**
     * 登录接口
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("login")
    public Map<String,Object> login( String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginUser user=(LoginUser)authentication.getPrincipal();//这里就是用户
        String token= JwtUtils.createJWT(user,"sss","super",20000000L,JwtUtils.key);

        //缓存一下
        String key= UUID.randomUUID().toString();
        redisTemplate.set(key,token);

        return ResultUtils.getResult(ResultInfo.SUCCESS, "登录成功",token);
    }

}
