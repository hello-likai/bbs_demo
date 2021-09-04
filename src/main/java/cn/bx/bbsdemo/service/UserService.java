package cn.bx.bbsdemo.service;

import cn.bx.bbsdemo.entity.User;

import java.util.List;

public interface UserService {

    String login(User user) throws Exception;

    List<User> allUsers();

    void register(User user);

    boolean checkLogin(String username,String rawPassword) throws Exception;
}
