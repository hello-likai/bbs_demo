package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * dao 层 继承 JpaRepository 接口
 *  代码直接复制案例代码，未修改
 *
 * JpaRepository<User,String> 泛型分别指的是：实体类和实体类的id类型，long或者String
 */

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * 用户列表
     * @return
     */
    List<User> findAll();

    User findByUsername(String username);

}
