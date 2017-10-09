package com.mdvns.mdvn.model.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.sapi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private ModelService modelService;


    @PostMapping("/retrieveModelList")
    private RestResponse retrieveModelList() throws Exception {
        return modelService.getModelList();
    }



}
