package com.mdvns.mdvn.comment.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateCommentInfoResponse {

    private Comment comment;
    private Comment replyDetail;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment getReplyDetail() {
        return replyDetail;
    }

    public void setReplyDetail(Comment replyDetail) {
        this.replyDetail = replyDetail;
    }
}
