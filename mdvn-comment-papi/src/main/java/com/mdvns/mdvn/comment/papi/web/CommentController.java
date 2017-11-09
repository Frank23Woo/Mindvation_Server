package com.mdvns.mdvn.comment.papi.web;

import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoRequest;
import com.mdvns.mdvn.comment.papi.domain.LikeCommentRequest;
import com.mdvns.mdvn.comment.papi.service.CommentService;
import com.mdvns.mdvn.common.beans.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/comments", "/v1.0/dashboard"})
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 创建评论
     * @param request
     * @return
     */
    @PostMapping(value = "/createCommentInfo")
    public RestResponse createCommentInfo(@RequestBody CreateCommentInfoRequest request) {
        return this.commentService.createCommentInfo(request);
    }

    /**
     * 创建评论
     * @param request
     * @return
     */
    @PostMapping(value = "/likeOrDislike")
    public RestResponse likeOrDislike(@RequestBody LikeCommentRequest request) {
        return this.commentService.likeOrDislike(request);
    }
//    /**
//     * 获取某个reqmnt评论信息
//     * @param request
//     * @return
//     */
//    @PostMapping(value = "/createCommentInfo")
//    public RestResponse createCommentInfo(@RequestBody CreateCommentInfoRequest request) {
//        return this.commentService.createCommentInfo(request);
//    }





}
