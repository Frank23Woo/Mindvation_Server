package com.mdvns.mdvn.model.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.papi.service.ModelService;
import com.mdvns.mdvn.model.papi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    /**
     * 新建模型
     *
     * @param createTagRequest
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @PostMapping(value = "/createTag")
    public ResponseEntity<?> createTag(@RequestBody @Validated CreateModelRequest createTagRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            LogUtil.errorLog("请求参数不正确");
            throw new BindException(bindingResult);
        }
        return this.modelService.createModel(createTagRequest);
    }

    @PostMapping("/retrieveModelList")
    private RestResponse retrieveModelList() throws Exception {
        return modelService.retrieveModelList();
    }
}
