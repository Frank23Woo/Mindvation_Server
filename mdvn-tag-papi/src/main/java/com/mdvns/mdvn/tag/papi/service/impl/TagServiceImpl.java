package com.mdvns.mdvn.tag.papi.service.impl;

import com.mdvns.mdvn.common.exception.RestDefautResponse;
import com.mdvns.mdvn.common.exception.ReturnFormat;
import com.mdvns.mdvn.tag.papi.config.WebConfig;
import com.mdvns.mdvn.tag.papi.domain.*;
import com.mdvns.mdvn.tag.papi.service.TagService;
import com.mdvns.mdvn.tag.papi.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    private RestDefautResponse restDefautResponse;



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
    public RestDefautResponse createTag(CreateTagRequest createTagRequest) throws HttpClientErrorException {
        LogUtil.sreviceStartLog("createTag ");

        tag.setCreatorId(createTagRequest.getCreatorId());
        tag.setName(createTagRequest.getName());
        tag.setColor(createTagRequest.getColor());


        /*调用sapi保存tag 的 url*/
        String url = webConfig.getSaveTagUrl()+"/1";
        LogUtil.logInfo("保存标签的URL：", url);
        ResponseEntity<?> responseEntity = this.restTemplate.postForEntity(url, tag, RestDefautResponse.class);
//        tag = this.restTemplate.postForObject(url, tag, Tag.class);
        /*try {
            tag = this.restTemplate.postForObject(url, tag, Tag.class);
        } catch (Exception ex) {
            LogUtil.errorLog(ex);
            throw new RuntimeException("调用SAPI保存数据失败.");
        }*/
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return ReturnFormat.retParam(HttpStatus.CREATED.toString(), "000",tag);
        }
        LogUtil.logInfo("responseBody:{}", responseEntity.getBody());
        LogUtil.logInfo("保存的标签对象为:", tag);
//        throw new RuntimeException(responseEntity.getStatusCode(), );
        throw new HttpClientErrorException(responseEntity.getStatusCode(), responseEntity.getBody().toString());
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
