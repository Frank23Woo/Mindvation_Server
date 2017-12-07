package com.mdvns.mdvn.comment.papi.service.impl;


import com.mdvns.mdvn.comment.papi.config.WebConfig;
import com.mdvns.mdvn.comment.papi.domain.Comment;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoRequest;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoResponse;
import com.mdvns.mdvn.comment.papi.domain.LikeCommentRequest;
import com.mdvns.mdvn.comment.papi.service.CommentService;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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
