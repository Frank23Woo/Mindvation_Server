package com.mdvns.mdvn.tag.papi.service.impl;

import com.mdvns.mdvn.tag.papi.domain.CreateTagRequest;
import com.mdvns.mdvn.tag.papi.domain.CreateTagResponse;
import com.mdvns.mdvn.tag.papi.domain.Tag;
import com.mdvns.mdvn.tag.papi.service.TagService;
import com.mdvns.mdvn.tag.papi.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TagServiceImpl implements TagService {

    /* 注入RestTamplate*/
    @Autowired
    private RestTemplate  restTemplate;

    /* 注入Tag*/
    @Autowired
    private Tag tag;

    /*注入response*/
    @Autowired
    private CreateTagResponse createTagResponse;


    /*
    *调用SAPI保存Tag
    * 1. 根据
    */
    @Override
    public CreateTagResponse createTag(CreateTagRequest createTagRequest) {
        LogUtil.sreviceStartLog("createTag");

        if (createTagRequest == null|| createTagRequest.getCreatorId()==null) {
            throw new NullPointerException("createTagRequest 或 creatorId 不能为空");
        }

        tag.setCreatorId(createTagRequest.getCreatorId());
        if (!StringUtils.isEmpty(createTagRequest.getName())) {
            tag.setName(createTagRequest.getName());
        }

        if (!StringUtils.isEmpty(createTagRequest.getColor())) {
            tag.setColor(createTagRequest.getColor());
        }
        if (!StringUtils.isEmpty(createTagRequest.getRemarks())) {
            tag.setRemarks(createTagRequest.getRemarks());
        }

        /*sapi srevice url*/
        String url = "http://localhost:10002/mdvn-tag-sapi/saveTag";
        try {
            tag = this.restTemplate.postForObject(url, tag, Tag.class);
        }catch (Exception ex){
            LogUtil.errorLog(ex);
            throw new RuntimeException("调用SAPI保存数据失败.");
        }

        createTagResponse.setTag(tag);

        LogUtil.sreviceEndLog("createTag");
        return createTagResponse;
    }
}
