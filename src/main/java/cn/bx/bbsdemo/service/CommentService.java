package cn.bx.bbsdemo.service;

import cn.bx.bbsdemo.entity.Comment;

import java.util.List;

public interface CommentService {
    // 创建评论
    void createComment(Comment comment);

    // 更新评论
    void updateComment(Comment comment);

    // 删除评论
    void deleteComment(long id);

    // 根据用户id查询评论
    List<Comment> findAllByUserId(long id);
}
