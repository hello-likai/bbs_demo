package cn.bx.bbsdemo.controller;

import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.service.UserService;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RequestMapping("users")
@RestController
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    // 要在启动类里面，使用@bean注解将它加入到容器
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

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
    @PostMapping(value = "save")
    public Map<String,Object> register(User user){
        // 使用security来加密密码
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        userService.register(user);
        return ResultUtils.getResult(ResultInfo.SUCCESS, "注册成功");
    }

    /**
     * 登录
     * @param user
     */

    /**
     * 修改密码
     * @param user
     */

    /**
     * 找回密码
     * @param user
     */


}
