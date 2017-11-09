package com.mdvns.mdvn.comment.sapi.repository;

import com.mdvns.mdvn.comment.sapi.domain.entity.ReplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyDetailRepository extends JpaRepository<ReplyDetail, Integer> {

    ReplyDetail findByReplyIdAndCreatorIdAndPassiveAt(String commentId, String creatorId, String passiveAt);

    List<ReplyDetail> findByCommentIdAndIsDeleted(String commentId,Integer isDeleted);
}
