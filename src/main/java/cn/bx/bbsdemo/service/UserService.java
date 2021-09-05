package cn.bx.bbsdemo.service;

import cn.bx.bbsdemo.entity.User;

import java.util.List;

public interface UserService {
    
    List<User> allUsers();

    void register(User user);

}
