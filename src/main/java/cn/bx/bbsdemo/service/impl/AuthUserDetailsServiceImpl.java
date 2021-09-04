package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.AuthUser;
import cn.bx.bbsdemo.entity.Role;
import cn.bx.bbsdemo.entity.User;
import cn.bx.bbsdemo.repository.RoleDao;
import cn.bx.bbsdemo.repository.UserDao;
import cn.bx.bbsdemo.repository.UserRoleDao;
import cn.bx.bbsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 要实现UserDetailsService接口，这个接口是security提供的
 */
@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 通过账号查找用户、角色的信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
        }else {
            //查找角色
            List<Role> roles = userRoleDao.findByUserId(user.getId());
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role : roles) {
                Optional<Role> r = roleDao.findById(role.getId());
                authorities.add(new SimpleGrantedAuthority(r.get().getRoleName()));
            }
            return new AuthUser(user.getUserName(), user.getPassWord(), authorities);
        }
    }
}
