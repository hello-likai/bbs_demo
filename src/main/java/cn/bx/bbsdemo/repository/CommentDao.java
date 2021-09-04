package cn.bx.bbsdemo.repository;

import cn.bx.bbsdemo.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(long userId, Sort sort);

}
