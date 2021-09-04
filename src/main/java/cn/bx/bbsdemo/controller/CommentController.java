package cn.bx.bbsdemo.controller;

import cn.bx.bbsdemo.entity.Comment;
import cn.bx.bbsdemo.service.CommentService;
import cn.bx.bbsdemo.utils.ResultInfo;
import cn.bx.bbsdemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comments")
@Api(tags = "评论模块")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 创建评论
     * @param comment
     */
    @ApiOperation(value = "创建评论")
    @PostMapping("createComment")
    public Map<String,Object> createComment(Comment comment){
        comment.setCreateTime(new Date());
        commentService.createComment(comment);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"添加成功");
    }

    /**
     * 更新评论
     * @param comment
     */
    @ApiOperation(value = "更新评论")
    @PutMapping("updateComment")
    public Map<String,Object> updateComment(Comment comment){
        comment.setUpdateTime(new Date());
        commentService.updateComment(comment);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"更新成功");
    }

    /**
     * 根据id删除评论
     * @param id
     */
    @ApiOperation(value = "根据id删除评论")
    @DeleteMapping("deleteComment")
    public Map<String,Object> deleteComment(long id){
        commentService.deleteComment(id);

        return ResultUtils.getResult(ResultInfo.SUCCESS,"删除成功");
    }


    /**
     * 根据用户id查询评论
     * @param userId
     */
    @ApiOperation(value = "根据用户id查询评论，倒序排列")
    @GetMapping("getCommentsByUserId")
    public Map<String,Object> getCommentsByUserId(long userId){
        List<Comment> comments = commentService.findAllByUserId(userId);

        return ResultUtils.getResult(ResultInfo.SUCCESS, "查询成功",comments);
    }



}
