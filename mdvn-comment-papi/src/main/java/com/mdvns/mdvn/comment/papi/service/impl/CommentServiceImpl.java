package com.mdvns.mdvn.comment.papi.service.impl;


import com.mdvns.mdvn.comment.papi.config.WebConfig;
import com.mdvns.mdvn.comment.papi.domain.Comment;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoRequest;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoResponse;
import com.mdvns.mdvn.comment.papi.domain.LikeCommentRequest;
import com.mdvns.mdvn.comment.papi.service.CommentService;
import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    /* 注入RestTemplate*/
    @Autowired
    private RestTemplate restTemplate;

    /*注入WebConfig*/
    @Autowired
    private WebConfig webConfig;

    /*注入RestResponse*/
    @Autowired
    private RestResponse restResponse;

    @Override
    public RestResponse createCommentInfo(CreateCommentInfoRequest request) {
        LOG.info("开始执行{} createCommentInfo()方法.", this.CLASS);
        if (request == null || request.getProjId() == null || request.getSubjectId() == null || request.getCreatorId() == null) {
            throw new NullPointerException("createCommentInfo 或项目Id/subjectId/登录者Id不能为空");
        }
        CreateCommentInfoResponse createCommentInfoResponse = new CreateCommentInfoResponse();
        String createCommentInfoUrl = webConfig.getCreateCommentInfoUrl();
        //如果是回复的话，把回复的人加入到passiveAts中
        String commentId = request.getReplyId();
        if (request.getReplyId() != null) {
            Comment replyComm = restTemplate.postForObject(webConfig.getRtrvCommentDetailInfoUrl(), commentId, Comment.class);
            List<String> passiveAts = request.getPassiveAts();
            if (!passiveAts.contains(replyComm.getCreatorId())) {
                passiveAts.add(replyComm.getCreatorId());
            }
        }
        try {
            createCommentInfoResponse = restTemplate.postForObject(createCommentInfoUrl, request, CreateCommentInfoResponse.class);
        } catch (Exception ex) {
            LOG.info("创建或者回复评论失败");
            throw new BusinessException(ExceptionEnum.COMMENT__NOT_CREATE);
        }
        //创建者返回对象
        String staffUrl = webConfig.getRtrvStaffInfoUrl();
        String creatorId = createCommentInfoResponse.getComment().getCreatorId();
        Staff staff = restTemplate.postForObject(staffUrl, creatorId, Staff.class);
        createCommentInfoResponse.getComment().setCreatorInfo(staff);

        //被@的人返回对象
        if (request.getReplyId() != null) {
            String passiveAt = createCommentInfoResponse.getReplyDetail().getCreatorId();
            Staff passiveAtInfo = restTemplate.postForObject(staffUrl, passiveAt, Staff.class);
            createCommentInfoResponse.getReplyDetail().setCreatorInfo(passiveAtInfo);
        }
        restResponse.setResponseBody(createCommentInfoResponse);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        LOG.info("结束执行{} createCommentInfo()方法.", this.CLASS);

        /**
         * 消息推送(创建comment)
         */
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            ServerPush serverPush = new ServerPush();
            String initiatorId = request.getCreatorId();
            String subjectId = request.getSubjectId();
            Staff initiator = this.restTemplate.postForObject(webConfig.getRtrvStaffInfoUrl(), initiatorId, Staff.class);
            serverPush.setInitiator(initiator);
            serverPush.setSubjectType("comment");
            serverPush.setSubjectId(subjectId);
            serverPush.setType("at");
            //查询所评论的需求或者story的创建者
            String createId = this.restTemplate.postForObject(webConfig.getRtrvCreatorIdUrl(), subjectId, String.class);
            if (request.getPassiveAts().size() > 0) {//回复
                sendMessageRequest.setStaffIds(request.getPassiveAts());
            } else {//不@人
                List<String> staffIds = new ArrayList<>();
                staffIds.add(createId);
                sendMessageRequest.setStaffIds(staffIds);
            }
            sendMessageRequest.setInitiatorId(initiatorId);
            sendMessageRequest.setServerPushResponse(serverPush);
            Boolean flag = this.restTemplate.postForObject(webConfig.getSendMessageUrl(), sendMessageRequest, Boolean.class);
            System.out.println(flag);
        } catch (Exception e) {
            LOG.error("消息推送(创建comment)出现异常，异常信息：" + e);
        }

        return restResponse;
    }

    @Override
    public RestResponse likeOrDislike(LikeCommentRequest request) {
        LOG.info("开始执行{} likeOrDislikeUrl()方法.", this.CLASS);
        CreateCommentInfoResponse createCommentInfoResponse = new CreateCommentInfoResponse();
        String likeOrDislikeUrl = webConfig.getLikeOrDislikeUrl();
        Comment comment = new Comment();
        try {
            createCommentInfoResponse = restTemplate.postForObject(likeOrDislikeUrl, request, CreateCommentInfoResponse.class);
        } catch (Exception ex) {
            LOG.info("点赞或者踩评论失败");
            throw new BusinessException(ExceptionEnum.COMMENT__LIKEORDISLIKE_FAILD);
        }
        //创建者返回对象
        String staffUrl = webConfig.getRtrvStaffInfoUrl();
        String creatorId = createCommentInfoResponse.getComment().getCreatorId();
        Staff staff = restTemplate.postForObject(staffUrl, creatorId, Staff.class);
        createCommentInfoResponse.getComment().setCreatorInfo(staff);
        //被@的人返回对象
        String replyId = createCommentInfoResponse.getComment().getReplyId();
        if (!StringUtils.isEmpty(replyId)) {
            String passiveAt = createCommentInfoResponse.getReplyDetail().getCreatorId();
            Staff passiveAtInfo = restTemplate.postForObject(staffUrl, passiveAt, Staff.class);
            createCommentInfoResponse.getReplyDetail().setCreatorInfo(passiveAtInfo);
        }
        restResponse.setResponseBody(createCommentInfoResponse);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        LOG.info("结束执行{} likeOrDislike()方法.", this.CLASS);
        return restResponse;
    }

}
