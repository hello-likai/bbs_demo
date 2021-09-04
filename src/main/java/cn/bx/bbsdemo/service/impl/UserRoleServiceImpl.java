package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.UserRole;
import cn.bx.bbsdemo.repository.UserRoleDao;
import cn.bx.bbsdemo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void setRole(UserRole userRole) {
        userRoleDao.save(userRole);
    }
}
