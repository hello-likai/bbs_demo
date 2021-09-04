package cn.bx.bbsdemo.controller;

import cn.bx.bbsdemo.entity.Post;
import cn.bx.bbsdemo.service.PostService;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("posts")
@RestController
@Api(tags = "帖子模块")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 新增帖子
     * 需要做数据校验，user_id, title, content 必填
     */
    @ApiOperation(value = "创建帖子")
    @PostMapping("store")
    public Map<String,Object> store(Post post ){
        post.setCreateTime(new Date());
        postService.createNewPost(post);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"发帖成功");
    }

    /**
     * 根据用户id查询帖子
     */
    @ApiOperation(value = "根据用户id查询帖子")
    @GetMapping("getPostByUserId")
    public Map<String,Object> getPostByUserId( long userId ){
        List<Post> posts = postService.getPostByUserId(userId);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"添加成功", posts);
    }

    /**
     * 更新帖子
     */
    @ApiOperation(value = "更新帖子")
    @PutMapping("updateById")
    public Map<String,Object> updateById( Post post){
        post.setUpdateTime(new Date());
        postService.updatePost(post);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"更新成功");
    }

    /**
     * 删除帖子
     */
    @ApiOperation(value = "删除帖子")
    @DeleteMapping("deleteById")
    public Map<String,Object> deleteById( long id){
        postService.deletePost(id);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"删除成功");
    }

    /**
     * 根据id查询帖子详情
     */
    @ApiOperation(value = "根据id查询帖子详情")
    @GetMapping("getPostById")
    public Map<String,Object> getPostById( long id ){
        Post post = postService.getPostById(id);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"查询成功", post);
    }


}
