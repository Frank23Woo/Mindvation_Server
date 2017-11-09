package com.mdvns.mdvn.comment.sapi.web;

import com.mdvns.mdvn.comment.sapi.domain.*;
import com.mdvns.mdvn.comment.sapi.domain.entity.Comment;
import com.mdvns.mdvn.comment.sapi.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 评论控制层
 */

@RestController
@RequestMapping(value = {"/comments", "/v1.0/comments"})
public class CommentController {

    private Logger LOG = LoggerFactory.getLogger(CommentController.class);
    private final String CLASS = this.getClass().getName();

    /*评论service注入*/
    @Autowired
    private CommentService commentService;


    /**
     * 创建评论
     * @param request
     * @return
     */
    @PostMapping(value = "/createCommentInfo")
    public CreateCommentInfoResponse createCommentInfo(@RequestBody CreateCommentInfoRequest request) {
        LOG.info("开始执行{} createCommentInfo()方法.", this.CLASS);
        return this.commentService.createCommentInfo(request);
    }

    /**
     * 获取每个reqmnt或者story的评论（获取列表时需要）
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvCommentInfos")
    public List<CommentDetail> rtrvCommentInfos(@RequestBody RtrvCommentInfosRequest request) {
        LOG.info("开始执行{} rtrvCommentInfos()方法.", this.CLASS);
        return this.commentService.rtrvCommentInfos(request);
    }

    /**
     * 点赞或踩
     * @param request
     * @return
     */
    @PostMapping(value = "/likeOrDislike")
    public Comment likeComment(@RequestBody LikeCommentRequest request) {
        LOG.info("开始执行{} likeComment()方法.", this.CLASS);
        return this.commentService.likeComment(request);
    }



}
