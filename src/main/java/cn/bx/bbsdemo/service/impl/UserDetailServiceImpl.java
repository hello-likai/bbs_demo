package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.LoginUser;
import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userDao.findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException("用户名："+ username +"不存在");

        LoginUser u= new LoginUser(users);
        //将来可以从权限表种获得
        u.getAuthorities().add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "post";
            }
        });
        u.getAuthorities().add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "userlist";
            }
        });
        return u;
    }
}