package com.mdvns.mdvn.comment.papi.domain;


import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;


/**
 * 回复类
 */
@Component
public class ReplyDetail {
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
    private Long createTime;
    //相同两个人来回@的次数0--1--2--3--4--
//    private Integer atNumber;
    private Integer isDeleted;

    private Staff passiveAtInfo;

    public Staff getPassiveAtInfo() {
        return passiveAtInfo;
    }

    public void setPassiveAtInfo(Staff passiveAtInfo) {
        this.passiveAtInfo = passiveAtInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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
