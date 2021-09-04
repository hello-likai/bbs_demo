package cn.bx.bbsdemo.service;

import cn.bx.bbsdemo.entity.Post;

import java.util.List;

public interface PostService {

    /**
     * 新增帖子
     */
    void createNewPost(Post post);

    /**
     * 编辑帖子
     */
    void updatePost(Post post);

    /**
     * 删除帖子
     */
    void deletePost(long id);

    /**
     * 根据id查询帖子详情
     */
    Post getPostById(long id);

    /**
     * 根据用户id查询帖子
     */
    List<Post> getPostByUserId(long id);
}
