package com.mdvns.mdvn.tag.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
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

@Service
public class TagServiceImpl implements TagService {

    /* 注入RestTemplate*/
    @Autowired
    private RestTemplate restTemplate;

    /* 注入Tag*/
    @Autowired
    private Tag tag;


    /*注入WebConfig*/
    @Autowired
    private WebConfig webConfig;

    /*注入RestResponse*/
    @Autowired
    private RestResponse restResponse;


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
    public ResponseEntity<?> createTag(CreateTagRequest createTagRequest) {
        LogUtil.sreviceStartLog("createTag");
        String tagName = createTagRequest.getName();
        //根据name查询tag是否存在
        String findByNameUrl = webConfig.getFindByNameUrl() + "/" + tagName;
        ResponseEntity<Tag> responseEntity;
        responseEntity = this.restTemplate.postForEntity(findByNameUrl, tagName, Tag.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        tag = responseEntity.getBody();
        if (null != tag) {
            throw new BusinessException(ExceptionEnum.TAG_IS_CREATED);
        }
        LogUtil.errorLog("新建Tag的名称为：" + tagName);
        tag = new Tag();
        tag.setName(tagName);
        tag.setColor(createTagRequest.getColor());
        tag.setCreatorId(createTagRequest.getCreatorId());
        String url = webConfig.getSaveTagUrl();
        //调用Sapi保存标签
        responseEntity = this.restTemplate.postForEntity(url, tag, Tag.class);
        RestResponse restResponse = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            restResponse = RestResponseUtil.success(responseEntity.getBody());
            return ResponseEntity.ok(restResponse);
        }
        throw new BusinessException(ExceptionEnum.UNKNOW_EXCEPTION);
    }

    /*根据指定名称获取Tag*/
    public ResponseEntity<?> findByName(String name) {
        String findByNameUrl = webConfig.getFindByNameUrl() + "/" + name;
        ResponseEntity<Tag> responseEntity = this.restTemplate.postForEntity(findByNameUrl, name, Tag.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        tag = responseEntity.getBody();
        if (tag == null) {
            throw new BusinessException(ExceptionEnum.TAG_NOT_FOUND);
        }
        restResponse = RestResponseUtil.success(tag);
        return ResponseEntity.ok(restResponse);
    }

    /**
     * 根据Id查询标签
     * @param tagId
     * @return
     */
    public ResponseEntity<?> findById(String tagId) {
        String findByIdUrl = webConfig.getFindByIdUrl();
        findByIdUrl = StringUtils.replace(findByIdUrl, "{tagId}", tagId);
        tag = this.restTemplate.postForObject(findByIdUrl, tagId, Tag.class);
        restResponse = RestResponseUtil.success(tag);
        return ResponseEntity.ok(restResponse);
    }

    /**
     * 调用SAPI获取Tag列表
     *
     * @param retrieveTagListRequest
     * @return
     */
    public ResponseEntity<?> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest) {

        String url = webConfig.getRtrvTagListUrl();
        RetrieveTagListResponse retrieveTagListResponse = this.restTemplate.postForObject(url, retrieveTagListRequest, RetrieveTagListResponse.class);
        restResponse = RestResponseUtil.success(retrieveTagListResponse);

        return ResponseEntity.ok(restResponse);
    }


    /**
     * 跟新标签引用次数: 根据tagId给quoteCnt值 +1
     *
     * @param tagId
     * @return
     */
    public ResponseEntity<?> updateQuoteCnt(String tagId) {

        String url = webConfig.getUpdateQuoteCntUrl() + "/" + tagId;

        tag = this.restTemplate.postForObject(url, tagId, Tag.class);
        if (tag == null) {
            throw new BusinessException(ExceptionEnum.TAG_NOT_FOUND);
        }
        restResponse = RestResponseUtil.success(tag);
        return ResponseEntity.ok(restResponse);
    }

}
