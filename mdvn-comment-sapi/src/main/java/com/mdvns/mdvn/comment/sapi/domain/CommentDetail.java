package com.mdvns.mdvn.comment.sapi.domain;

import com.mdvns.mdvn.comment.sapi.domain.entity.Comment;
import com.mdvns.mdvn.comment.sapi.domain.entity.ReplyDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDetail {
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
