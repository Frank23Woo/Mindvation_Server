package com.mdvns.mdvn.comment.papi.service.impl;


import com.mdvns.mdvn.comment.papi.config.WebConfig;
import com.mdvns.mdvn.comment.papi.domain.Comment;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoRequest;
import com.mdvns.mdvn.comment.papi.domain.CreateCommentInfoResponse;
import com.mdvns.mdvn.comment.papi.domain.ReplyDetail;
import com.mdvns.mdvn.comment.papi.service.CommentService;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import org.hibernate.validator.constraints.ModCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
        Staff staff = restTemplate.postForObject(staffUrl,creatorId,Staff.class);
        createCommentInfoResponse.getComment().setCreatorInfo(staff);
        //被@的人返回对象
        List<ReplyDetail> replyDetails = createCommentInfoResponse.getReplyDetails();
        for (int i = 0; i < replyDetails.size(); i++) {
            String passiveAt = replyDetails.get(i).getPassiveAt();
            Staff passiveAtInfo = restTemplate.postForObject(staffUrl,passiveAt,Staff.class);
            replyDetails.get(i).setPassiveAtInfo(passiveAtInfo);
        }
        restResponse.setResponseBody(createCommentInfoResponse);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        LOG.info("结束执行{} createCommentInfo()方法.", this.CLASS);
        return restResponse;
    }
}
