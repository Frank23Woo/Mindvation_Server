package com.mdvns.mdvn.model.papi.web;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.model.papi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping("/retrieveModelList")
    private RestDefaultResponse retrieveModelList() throws Exception {
        return modelService.retrieveModelList();
    }
}
