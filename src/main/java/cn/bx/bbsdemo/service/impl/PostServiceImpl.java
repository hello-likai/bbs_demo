package cn.bx.bbsdemo.service.impl;

import cn.bx.bbsdemo.entity.Post;
import cn.bx.bbsdemo.repository.PostDao;
import cn.bx.bbsdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public void createNewPost(Post post) {
        postDao.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postDao.save(post);
    }

    @Override
    public void deletePost(long id) {
        postDao.deleteById(id);
    }

    @Override
    public Post getPostById(long id) {
        return postDao.getById(id);
    }

    @Override
    public List<Post> getPostByUserId(long id) {
        return postDao.findByUserId(id);
    }
}
