package com.mdvns.mdvn.tag.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ReturnFormat;
import com.mdvns.mdvn.tag.papi.config.WebConfig;
import com.mdvns.mdvn.tag.papi.domain.*;
import com.mdvns.mdvn.tag.papi.service.TagService;
import com.mdvns.mdvn.tag.papi.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    /* 注入RestTamplate*/
    @Autowired
    private RestTemplate restTemplate;

    /* 注入Tag*/
    @Autowired
    private Tag tag;

    /*注入response*/
    @Autowired
    private CreateTagResponse createTagResponse;

    @Autowired
    private RetrieveTagListResponse retrieveTagListResponse;

    /*注入WebConfig*/
    @Autowired
    private WebConfig webConfig;

    /*注入RestDefaultResponse*/
    @Autowired
    private RestDefaultResponse restDefaultResponse;


    /**
     * 调用Sapi 保存Tag：
     * 1. 如果createTagRequest 为空, 抛出异常 NullPointException
     * 2. 依次对createTagRequest的字段做非空校验并赋值给Tag对象
     * 3. 调用SAPI保存tag数据
     *
     * @param createTagRequest
     * @return
     */
    @Override
    public RestDefaultResponse createTag(CreateTagRequest createTagRequest) {
        LogUtil.sreviceStartLog("createTag ");

        tag.setCreatorId(createTagRequest.getCreatorId());
        tag.setName(createTagRequest.getName());
        tag.setColor(createTagRequest.getColor());

        /*调用sapi保存tag 的 url*/
        String url = webConfig.getSaveTagUrl();
        LogUtil.logInfo("保存标签的URL：", url);
        ResponseEntity<?> responseEntity = null;

//        tag = this.restTemplate.postForEntity(url, tag, Tag.class);
        restDefaultResponse = this.restTemplate.postForObject(url, tag, RestDefaultResponse.class);
        if (restDefaultResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
            return restDefaultResponse;
        }

        throw new BusinessException(restDefaultResponse.getResponseCode(), restDefaultResponse.getResponseBody().toString());

    }


    /**
     * 调用SAPI获取Tag列表
     *
     * @param retrieveTagListRequest
     * @return
     */
    public RetrieveTagListResponse rtrvTagList(RetrieveTagListRequest retrieveTagListRequest) {

        String url = webConfig.getRtrvTagListUrl();
        List<Tag> tags = (ArrayList<Tag>) this.restTemplate.postForEntity(url, retrieveTagListRequest, List.class).getBody();
        retrieveTagListResponse.setTags(tags);
        return retrieveTagListResponse;
    }


    public Tag updateQuoteCnt(UpdateQuoteCntRequest updateQuoteCntRequest) {
        String tagId = updateQuoteCntRequest.getTagId();
        if (StringUtils.isEmpty(tagId)) {
            LogUtil.errorLog("标签编号不能为空");
            throw new NullPointerException("标签编号不能为空");
        }

        String url = webConfig.getUpdateQuoteCntUrl() + "/" + updateQuoteCntRequest.getTagId();

        tag = this.restTemplate.postForObject(url, updateQuoteCntRequest, Tag.class);

        return tag;
    }

}
