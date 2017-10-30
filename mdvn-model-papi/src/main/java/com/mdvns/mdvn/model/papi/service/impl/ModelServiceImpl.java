package com.mdvns.mdvn.model.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.model.papi.config.WebConfig;
import com.mdvns.mdvn.model.papi.domain.*;
import com.mdvns.mdvn.model.papi.service.ModelService;
import com.mdvns.mdvn.model.papi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private Model model;

    @Autowired
    private SubFunctionLabel subFunctionLabel;

    @Autowired
    private RestResponse restResponse;


    /**
     * 调用SAPI获取Model列表
     *
     * @param retrieveModelListRequest
     * @return
     */
    public RestResponse rtrvModelList(RetrieveModelListByTypeRequest retrieveModelListRequest) {
//        RetrieveModelListResponse retrieveModelListResponse = new RetrieveModelListResponse();
        RetrieveModelListAndSortResponse retrieveModelListResponse = new RetrieveModelListAndSortResponse();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvModelListUrl();
        retrieveModelListResponse = this.restTemplate.postForObject(url, retrieveModelListRequest, RetrieveModelListAndSortResponse.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        RtrvModelListResponse rtrvModelListResponse = new RtrvModelListResponse();
        List<ModelAndStaff> models = new ArrayList<>();
        for (int i = 0; i < retrieveModelListResponse.getModels().size() ; i++) {
            ModelAndStaff modelAndStaff = new ModelAndStaff();
            String creatorId = retrieveModelListResponse.getModels().get(i).getModel().getCreatorId();
            Staff staff = this.restTemplate.postForObject(webConfig.getRtrvStaffInfoUrl(),creatorId,Staff.class);
            modelAndStaff.setCreatorInfo(staff);
            modelAndStaff.setModel(retrieveModelListResponse.getModels().get(i).getModel());
            modelAndStaff.setSort(retrieveModelListResponse.getModels().get(i).getSort());
            models.add(modelAndStaff);
        }
        rtrvModelListResponse.setModels(models);
        rtrvModelListResponse.setTotalNumber(retrieveModelListResponse.getTotalNumber());
        rtrvModelListResponse.setRemarks(retrieveModelListResponse.getRemarks());
        restResponse.setResponseBody(rtrvModelListResponse);
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

    /**
     * 通过Id查询model信息（只到reqmnt下的过程方法模块和角色信息）
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse findById(RtrvModelByIdRequest request) {
        String findByIdUrl = webConfig.getFindByIdUrl();
        RtrvModelByIdResponse rtrvModelByIdResponse = new RtrvModelByIdResponse();
        List<SubFunctionLabel> subFunctionLabels = new ArrayList<>();
        try {
            rtrvModelByIdResponse = this.restTemplate.postForObject(findByIdUrl, request, RtrvModelByIdResponse.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        if (rtrvModelByIdResponse.getFunctionLabels().size() <= 0) {
            throw new BusinessException(ExceptionEnum.functionModel_NOT_FOUND);
        }
        restResponse.setResponseBody(rtrvModelByIdResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 通过Id查询model全部详细信息
     * @param request
     * @return
     */
    @Override
    public RestResponse findModelDetailById(RtrvModelByIdRequest request) {
        String findByIdUrl = webConfig.getFindModelDetailByIdUrl();
        CreateModelResponse createModelResponse = new CreateModelResponse();
        RtrvModelDetailInfoResponse rtrvModelDetailInfoResponse = new RtrvModelDetailInfoResponse();
        try {
            createModelResponse = this.restTemplate.postForObject(findByIdUrl, request, CreateModelResponse.class);
            String creatorId = createModelResponse.getModel().getCreatorId();
            Staff staff = this.restTemplate.postForObject(webConfig.getRtrvStaffInfoUrl(),creatorId,Staff.class);
            rtrvModelDetailInfoResponse.setCreatorInfo(staff);
            rtrvModelDetailInfoResponse.setFunctionLabels(createModelResponse.getFunctionLabels());
            rtrvModelDetailInfoResponse.setIterationModels(createModelResponse.getIterationModels());
            rtrvModelDetailInfoResponse.setModel(createModelResponse.getModel());
            rtrvModelDetailInfoResponse.setRoles(createModelResponse.getRoles());
            rtrvModelDetailInfoResponse.setTaskDeliveries(createModelResponse.getTaskDeliveries());
            rtrvModelDetailInfoResponse.setRemarks(createModelResponse.getRemarks());
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        restResponse.setResponseBody(rtrvModelDetailInfoResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
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
        //开始新建model
        String url = webConfig.getSaveModelUrl();
        //调用Sapi保存模块
        ResponseEntity<CreateModelResponse> respEntity = this.restTemplate.postForEntity(url, createModelRequest, CreateModelResponse.class);
        RestResponse restResponse = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            restResponse = RestResponseUtil.success(respEntity.getBody());
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
