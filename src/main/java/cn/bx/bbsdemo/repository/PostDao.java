package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {

    List<Post> findByUserId(long UserId);
}
