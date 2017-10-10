package com.mdvns.mdvn.model.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.model.papi.config.WebConfig;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.papi.domain.Model;
import com.mdvns.mdvn.model.papi.domain.RetrieveModelListRequest;
import com.mdvns.mdvn.model.papi.domain.RetrieveModelListResponse;
import com.mdvns.mdvn.model.papi.service.ModelService;
import com.mdvns.mdvn.model.papi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private Model model;

    @Autowired
    private RestResponse restResponse;

    @Override
    public RestResponse retrieveModelList() throws Exception {
        RestResponse restResponse = new RestResponse();
        final String url = "http://localhost:10009/retrieveModelList";
        try {
            List<Model> models = restTemplate.postForObject(url, null, List.class);
            restResponse.setResponseCode("0");
            restResponse.setResponseMsg("");
            restResponse.setResponseBody(models);
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode("0");
            restResponse.setResponseMsg("[服务器] IO异常");
        }

        return restResponse;
    }

    /**
     * 调用SAPI获取Model列表
     *
     * @param retrieveModelListRequest
     * @return
     */
    public RestResponse rtrvModelList(RetrieveModelListRequest retrieveModelListRequest) {
        RetrieveModelListResponse retrieveModelListResponse = new RetrieveModelListResponse();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvModelListUrl();
        retrieveModelListResponse = this.restTemplate.postForObject(url, retrieveModelListRequest, RetrieveModelListResponse.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(retrieveModelListResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /*根据指定名称获取Model*/
    public ResponseEntity<?> findByName(String name) {
        String findByNameUrl = webConfig.getFindByNameUrl() + "/" + name;
        ResponseEntity<Model> responseEntity = this.restTemplate.postForEntity(findByNameUrl, name, Model.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        model = responseEntity.getBody();
        if (model == null) {
            throw new BusinessException(ExceptionEnum.Model_NOT_FOUND);
        }
        restResponse = RestResponseUtil.success(model);
        return ResponseEntity.ok(restResponse);
    }

    @Override
    public ResponseEntity<?> createModel(CreateModelRequest createModelRequest) {

        LogUtil.sreviceStartLog("createModel");
        String modelName = createModelRequest.getName();
        //根据name查询model是否存在
        String findByNameUrl = webConfig.getFindByNameUrl() + "/" + modelName;
        ResponseEntity<Model> responseEntity;
        responseEntity = this.restTemplate.postForEntity(findByNameUrl, modelName, Model.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        model = responseEntity.getBody();
        if (null != model) {
            throw new BusinessException(ExceptionEnum.Model_IS_CREATED);
        }
        LogUtil.errorLog("新建Model的名称为：" + modelName);
        model = new Model();
        model.setName(modelName);
        model.setModelType(createModelRequest.getModelType());
        model.setColor(createModelRequest.getColor());
        model.setCreatorId(createModelRequest.getCreatorId());
        String url = webConfig.getSaveModelUrl();
        //调用Sapi保存标签
        responseEntity = this.restTemplate.postForEntity(url, model, Model.class);
        RestResponse restResponse = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            restResponse = RestResponseUtil.success(responseEntity.getBody());
            return ResponseEntity.ok(restResponse);
        }
        throw new BusinessException(ExceptionEnum.UNKNOW_EXCEPTION);
    }

    /**
     * 跟新标签引用次数: 根据modelId给quoteCnt值 +1
     *
     * @param modelId
     * @return
     */
    public ResponseEntity<?> updateQuoteCnt(String modelId) {

        String url = webConfig.getUpdateQuoteCntUrl() + "/" + modelId;

        model = this.restTemplate.postForObject(url, modelId, Model.class);
        if (model == null) {
            throw new BusinessException(ExceptionEnum.Model_NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
}
