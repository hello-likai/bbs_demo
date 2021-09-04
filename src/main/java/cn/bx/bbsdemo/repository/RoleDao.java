package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.Post;
import cn.bx.bbsdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {



}
