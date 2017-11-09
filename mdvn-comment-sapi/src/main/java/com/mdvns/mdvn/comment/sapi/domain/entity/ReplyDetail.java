/*
package com.mdvns.mdvn.comment.sapi.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

*/
/**
 * 回复类
 *//*

@Entity
@Component
public class ReplyDetail {
    @Id
    @GeneratedValue
    private Integer uuId;
    private String commentId;
    //回复的时哪条评论的Id(可为空)
    private String replyId;
    private String creatorId;
    //被@的人(可以是多个人,回复表里单独保存)
    private String passiveAt;
    //回复上一次被@的差值(间隔时间)
    private Long intervalTime;
    //创建评论的时间
    @Column(columnDefinition = "timestamp", nullable = false)
    private Timestamp createTime;
    //相同两个人来回@的次数0--1--2--3--4--
//    private Integer atNumber;
    private Integer isDeleted;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getPassiveAt() {
        return passiveAt;
    }

    public void setPassiveAt(String passiveAt) {
        this.passiveAt = passiveAt;
    }

    public Long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Long intervalTime) {
        this.intervalTime = intervalTime;
    }

}
*/
