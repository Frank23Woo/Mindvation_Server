package com.mdvns.mdvn.comment.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateCommentInfoResponse {

    private Comment comment;
    private List<ReplyDetail> replyDetails;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<ReplyDetail> getReplyDetails() {
        return replyDetails;
    }

    public void setReplyDetails(List<ReplyDetail> replyDetails) {
        this.replyDetails = replyDetails;
    }
}
