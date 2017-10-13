package com.mdvns.mdvn.model.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.papi.domain.RetrieveModelListRequest;
import com.mdvns.mdvn.model.papi.domain.RetrieveModelRequest;
import com.mdvns.mdvn.model.papi.domain.UpdateQuoteCntRequest;
import com.mdvns.mdvn.model.papi.service.ModelService;
import com.mdvns.mdvn.model.papi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value= {"/model", "/v1.0/model"})
public class ModelController {

    @Autowired
    private ModelService modelService;

    /**
     * 新建模型
     *
     * @param createModelRequest
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @PostMapping(value = "/createModel")
    public ResponseEntity<?> createModel(@RequestBody @Validated CreateModelRequest createModelRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            LogUtil.errorLog("请求参数不正确");
            throw new BindException(bindingResult);
        }
        return this.modelService.createModel(createModelRequest);
    }

    /**
     * 更新Model引用次数
     *
     * @param updateQuoteCntRequest
     * @return
     */
    @PostMapping(value = "/updateQupteCnt")
    public ResponseEntity<?> updateQuoteCnt(@RequestBody UpdateQuoteCntRequest updateQuoteCntRequest) {
        String modelId = updateQuoteCntRequest.getModelId();
        if (StringUtils.isEmpty(modelId)) {
            LogUtil.errorLog("modelId is empty.");
            throw new NullPointerException("modelId 不能为空.");
        }

        return this.modelService.updateQuoteCnt(modelId);
    }


    @PostMapping(value = "/findByName")
    public ResponseEntity<?> findByName(@RequestBody RetrieveModelRequest retrieveModelRequest) {

        return this.modelService.findByName(retrieveModelRequest.getName());
    }


    @PostMapping(value = "/rtrvModelList")
    public RestResponse rtrvModelList(@RequestBody RetrieveModelListRequest retrieveModelListRequest) {
        return this.modelService.rtrvModelList(retrieveModelListRequest);
    }


    /**
     * 根据Id删除Model
     *
     * @param modelId
     * @return
     */
    @PostMapping(value = "/deleteModel")
    public Integer deleteModel(String modelId) {

        return null;
    }
}
