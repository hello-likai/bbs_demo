package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.Comment;
import cn.bx.bbsdemo.repository.CommentDao;
import cn.bx.bbsdemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 创建评论
     * @param comment
     */
    @Override
    public void createComment(Comment comment) {
        commentDao.save(comment);
    }

    /**
     * 更新评论
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        commentDao.save(comment);
    }

    /**
     * 删除评论
     * @param id
     */
    @Override
    public void deleteComment(long id) {
        commentDao.deleteById(id);
    }

    /**
     * 根据用户id查询评论
     * @param id
     */
    @Override
    public List<Comment> findAllByUserId(long id) {
        // 注意这个createTime，要是实体类中的字段，而不是数据库的字段
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        List<Comment> comments = commentDao.findByUserId(id, sort);

        return comments;
    }

}
