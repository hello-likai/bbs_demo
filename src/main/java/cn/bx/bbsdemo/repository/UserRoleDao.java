package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.Role;
import cn.bx.bbsdemo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    List<Role> findByUserId(long userId);

}
