package com.mdvns.mdvn.comment.sapi.repository;

import com.mdvns.mdvn.comment.sapi.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByProjIdAndSubjectIdAndIsDeleted(String projId,String subjectId,Integer isDeleted);
    Comment findByCommentId(String commentId);

}
