package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.service.UserService;
import cn.bx.bbsdemo.utils.BCryptPasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;


    @Override
    public List<User> allUsers() {
        List<User> list = userDao.findAll( );
        return list;
    }

    @Override
    public void register(User user) {
        userDao.save(user);
    }


}
