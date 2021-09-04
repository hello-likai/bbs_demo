package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.Role;
import cn.bx.bbsdemo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    List<Role> findByUserId(long userId);

}
