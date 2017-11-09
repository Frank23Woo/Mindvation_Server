package com.mdvns.mdvn.comment.papi.service;


import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoRequest;
import com.mdvns.mdvn.common.beans.RestResponse;

public interface CommentService {

    RestResponse createCommentInfo(CreateCommentInfoRequest request);


}
