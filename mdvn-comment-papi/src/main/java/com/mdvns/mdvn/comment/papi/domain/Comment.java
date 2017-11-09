package com.mdvns.mdvn.comment.papi.domain;

import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;


/**
 * 评论类
 */

@Component
public class Comment {

    private Integer uuId;
    //所属的项目Id
    private String projId;
    //所属需求或者story的Id
    private String subjectId;
    //评论的Id
    private String commentId;
    //回复的时哪条评论的Id(可为空)
    private String replyId;
    //发起@的人(创建这条评论的人)
    private String creatorId;
//    //被@的人(可以是多个人)
//    private String passiveAts;
    //评论的内容
    private String content;
    //点赞这个评论的人数
    private Integer likeQty;
    //踩这个评论的人数
    private Integer dislikeQty;
    //点赞的所有人的Id
    private String likeIds;
    //踩的所有人的Id
    private String dislikeIds;
    //创建评论的时间
    private Long createTime;

    private Staff creatorInfo;

    public Staff getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Staff creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

//    public String getPassiveAts() {
//        return passiveAts;
//    }
//
//    public void setPassiveAts(String passiveAts) {
//        this.passiveAts = passiveAts;
//    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeQty() {
        return likeQty;
    }

    public void setLikeQty(Integer likeQty) {
        this.likeQty = likeQty;
    }

    public Integer getDislikeQty() {
        return dislikeQty;
    }

    public void setDislikeQty(Integer dislikeQty) {
        this.dislikeQty = dislikeQty;
    }

    public String getLikeIds() {
        return likeIds;
    }

    public void setLikeIds(String likeIds) {
        this.likeIds = likeIds;
    }

    public String getDislikeIds() {
        return dislikeIds;
    }

    public void setDislikeIds(String dislikeIds) {
        this.dislikeIds = dislikeIds;
    }

    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}


