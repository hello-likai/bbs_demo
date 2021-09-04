package cn.bx.bbsdemo.controller;

import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.entity.UserRole;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.service.UserRoleService;
import cn.bx.bbsdemo.service.UserService;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleService userRoleService;

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
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        user.setCreateTime(new Date());
        userService.register(user);
        return ResultUtils.getResult(ResultInfo.SUCCESS, "注册成功");
    }

    /**
     * 登录
     * @param user
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "login")
    public Map<String,Object> login(User user) throws Exception {
        String token = userService.login(user);

        return ResultUtils.getResult(ResultInfo.SUCCESS, "设置成功",token);
    }


    /**
     * 修改密码
     * @param user
     */

    /**
     * 找回密码
     * @param user
     */

    /**
     * 给用户配置角色
     * @param userRole
     */
    @ApiOperation(value = "给用户设置角色")
    @PostMapping(value = "setRole")
    public Map<String,Object> setRole(UserRole userRole){
        userRoleService.setRole(userRole);

        return ResultUtils.getResult(ResultInfo.SUCCESS, "设置成功");
    }


}
