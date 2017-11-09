package com.mdvns.mdvn.comment.sapi.service;

import com.mdvns.mdvn.comment.sapi.domain.*;
import com.mdvns.mdvn.comment.sapi.domain.entity.Comment;

import java.util.List;

/**
 * 评论接口
 */

public interface CommentService {

    CreateCommentInfoResponse createCommentInfo(CreateCommentInfoRequest request);

    Comment likeComment(LikeCommentRequest request);

    List<CommentDetail> rtrvCommentInfos(RtrvCommentInfosRequest request);

}