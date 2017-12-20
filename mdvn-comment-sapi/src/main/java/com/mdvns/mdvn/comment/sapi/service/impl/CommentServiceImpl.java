package com.mdvns.mdvn.comment.sapi.service.impl;


import com.mdvns.mdvn.comment.sapi.domain.*;
import com.mdvns.mdvn.comment.sapi.domain.entity.Comment;
import com.mdvns.mdvn.comment.sapi.repository.CommentRepository;
import com.mdvns.mdvn.comment.sapi.service.CommentService;
import com.mdvns.mdvn.common.utils.MdvnStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:
 * @Description: Comment sapi业务处理
 * @Date:
 */
@Service
public class CommentServiceImpl implements CommentService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final String CLASS = this.getClass().getName();
    /*Dashboard Repository*/
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public CreateCommentInfoResponse createCommentInfo(CreateCommentInfoRequest request) {
        LOG.info("开始执行{} createCommentInfo()方法.", this.CLASS);

        CreateCommentInfoResponse createCommentInfoResponse = new CreateCommentInfoResponse();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        //save Comment表
        Comment comment = new Comment();
        String projId = request.getProjId();
        String subjectId = request.getSubjectId();
        String creatorId = request.getCreatorId();
        String content = request.getContent();
        comment.setCreateTime(currentTime);
        comment.setProjId(projId);
        comment.setSubjectId(subjectId);
        comment.setCreatorId(creatorId);
        comment.setContent(content);
        comment.setLikeQty(0);
        comment.setDislikeQty(0);
        comment.setIsDeleted(0);
        List<String> passiveAtList = request.getPassiveAts();
        if (passiveAtList != null && passiveAtList.size() > 0) {
            String passiveAts = MdvnStringUtil.join(passiveAtList, ",");
            comment.setPassiveAts(passiveAts);
        }
        //如果是回复
        String replyId = request.getReplyId();
        if (!StringUtils.isEmpty(replyId)) {
            Comment comm = new Comment();
            comment.setReplyId(replyId);
            //算出间隔的时间
            comm = this.commentRepository.findByCommentId(replyId);
            createCommentInfoResponse.setReplyDetail(comm);
            //间隔时间
            if (comm != null) {
                long intervalTime = currentTime.getTime() - comm.getCreateTime().getTime();
                comment.setIntervalTime(intervalTime);
            }
        }
        comment = this.commentRepository.saveAndFlush(comment);
        comment.setCommentId("C" + comment.getUuId());
        comment = this.commentRepository.saveAndFlush(comment);

        createCommentInfoResponse.setComment(comment);

        LOG.info("结束执行{} createCommentInfo()方法.", this.CLASS);
        return createCommentInfoResponse;
    }


    @Override
    public CreateCommentInfoResponse likeComment(LikeCommentRequest request) {
        LOG.info("开始执行{} likeComment()方法.", this.CLASS);
        CreateCommentInfoResponse createCommentInfoResponse = new CreateCommentInfoResponse();
        String remark = request.getRemark();
        String commentId = request.getCommentId();
        String creatorId = request.getCreatorId();
        Comment comment = this.commentRepository.findByCommentId(commentId);
        if (remark.equals("like")) {
            //对于点赞这边
            String likeIds = comment.getLikeIds();
            if (!StringUtils.isEmpty(likeIds)) {
                String[] likeArrayIds = likeIds.split(",");
                List<String> likeIdList = Arrays.asList(likeArrayIds);
                List list = new ArrayList(likeIdList);
                for (int i = 0; i < list.size(); i++) {
                    if (creatorId.equals(list.get(i))) {
                        list.remove(creatorId);
                    }
                }
                likeIds = MdvnStringUtil.join(list, ",");
                if (likeIdList.size() == list.size()) {
                    comment.setLikeQty(comment.getLikeQty() + 1);
                    comment.setLikeIds(likeIds + "," + creatorId);
                } else {
                    comment.setLikeQty(comment.getLikeQty() - 1);
                    comment.setLikeIds(likeIds);
                }
            } else {
                comment.setLikeQty(1);
                comment.setLikeIds(creatorId);
            }
            //对于踩这边
            String dislikeIds = comment.getDislikeIds();
            if (!StringUtils.isEmpty(dislikeIds)) {
                String[] dislikeArrayIds = dislikeIds.split(",");
                List<String> dislikeIdList = Arrays.asList(dislikeArrayIds);
                List list = new ArrayList(dislikeIdList);
                for (int i = 0; i < list.size(); i++) {
                    if (creatorId.equals(list.get(i))) {
                        list.remove(creatorId);
                    }
                }
                dislikeIds = MdvnStringUtil.join(list, ",");
                if (dislikeIdList.size() != list.size()) {
                    comment.setDislikeQty(comment.getDislikeQty() - 1);
                    comment.setDislikeIds(dislikeIds);
                }
            }
        }
        if (remark.equals("dislike")) {
            //对于踩这边
            String dislikeIds = comment.getDislikeIds();
            if (!StringUtils.isEmpty(dislikeIds)) {
                String[] dislikeArrayIds = dislikeIds.split(",");
                List<String> dislikeIdList = Arrays.asList(dislikeArrayIds);
                List list = new ArrayList(dislikeIdList);
                for (int i = 0; i < list.size(); i++) {
                    if (creatorId.equals(list.get(i))) {
                        list.remove(creatorId);
                    }
                }
                dislikeIds = MdvnStringUtil.join(list, ",");
                if (dislikeIdList.size() == list.size()) {
                    comment.setDislikeQty(comment.getDislikeQty() + 1);
                    comment.setDislikeIds(dislikeIds + "," + creatorId);
                } else {
                    comment.setDislikeQty(comment.getDislikeQty() - 1);
                    comment.setDislikeIds(dislikeIds);
                }
            } else {
                comment.setDislikeQty(1);
                comment.setDislikeIds(creatorId);
            }
            //对于点赞这边
            String likeIds = comment.getLikeIds();
            if (!StringUtils.isEmpty(likeIds)) {
                String[] likeArrayIds = likeIds.split(",");
                List<String> likeIdList = Arrays.asList(likeArrayIds);
                List list = new ArrayList(likeIdList);
                for (int i = 0; i < list.size(); i++) {
                    if (creatorId.equals(list.get(i))) {
                        list.remove(creatorId);
                    }
                }
                likeIds = MdvnStringUtil.join(list, ",");
                if (likeIdList.size() != list.size()) {
                    comment.setLikeQty(comment.getLikeQty() - 1);
                    comment.setLikeIds(likeIds);
                }
            }
        }
        comment = this.commentRepository.saveAndFlush(comment);
        createCommentInfoResponse.setComment(comment);
        if (!StringUtils.isEmpty(comment.getReplyId())) {
            Comment comm = this.commentRepository.findByCommentId(comment.getReplyId());
            createCommentInfoResponse.setReplyDetail(comm);
        }
        LOG.info("结束执行{} likeComment()方法.", this.CLASS);
        return createCommentInfoResponse;
    }

    /**
     * 获取每个reqmnt或者story的评论（获取列表时需要）
     *
     * @param request
     * @return
     */
    @Override
    public List<CommentDetail> rtrvCommentInfos(RtrvCommentInfosRequest request) {
        LOG.info("开始执行{} rtrvCommentInfos()方法.", this.CLASS);
        List<CommentDetail> commentDetails = new ArrayList<>();
        String projId = request.getProjId();
        String subjectId = request.getSubjectId();
        List<Comment> comments = this.commentRepository.findByProjIdAndSubjectIdAndIsDeleted(projId, subjectId, 0);
        for (int i = 0; i < comments.size(); i++) {
            CommentDetail commentDetail = new CommentDetail();
            Comment comment = comments.get(i);
            commentDetail.setComment(comment);
            String replyId = comment.getReplyId();
            if (!StringUtils.isEmpty(replyId)) {
                Comment comm = this.commentRepository.findByCommentId(replyId);
                commentDetail.setReplyDetail(comm);
            }
            commentDetails.add(commentDetail);
        }
        LOG.info("结束执行{} rtrvCommentInfos()方法.", this.CLASS);
        return commentDetails;
    }

    @Override
    public String rtrvCreatorId(String subjectId) {
        String createId = this.commentRepository.findCreateId(subjectId);
        return createId;
    }

    @Override
    public Comment rtrvCommentDetailInfo(String commentId) {
        Comment comm = this.commentRepository.findByCommentId(commentId);
        return comm;
    }
}
