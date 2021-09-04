package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.AuthUser;
import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.service.UserService;
import cn.bx.bbsdemo.utils.BCryptPasswordEncoderUtil;
import cn.bx.bbsdemo.utils.JwtTokenUtil;
import cn.bx.bbsdemo.utils.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(User user) throws Exception {
        String token = null;
        if(checkLogin(user.getUserName(),user.getPassWord())){
            token = TokenCache.getTokenFromCache(user.getUsername());
            if(token ==null) {
                AuthUser authUser = new AuthUser(user.getUserName(),user.getPassWord());
                token = jwtTokenUtil.generateToken(authUser);
                //把新的token存储到缓存中
                TokenCache.setToken(user.getUsername(),token);
            }
        }
        return token;
    }

    @Override
    public List<User> allUsers() {
        List<User> list = userDao.findAll( );
        return list;
    }

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public boolean checkLogin(String username,String rawPassword) throws Exception {
        User userEntity = userDao.findByUserName(username);

        if (userEntity == null) {
            //设置友好提示
            throw  new Exception("账号不存在，请重新尝试！");
        }else {
            //加密的密码
            String encodedPassword = userEntity.getPassWord();
            //和加密后的密码进行比配
            if(!bCryptPasswordEncoderUtil.matches(rawPassword,encodedPassword)) {
                //设置友好提示
                throw new Exception("密码不正确！");
            }else{
                return  true;
            }
        }
    }
}
