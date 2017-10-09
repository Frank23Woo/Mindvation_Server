package com.mdvns.mdvn.model.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.sapi.entity.Model;
import com.mdvns.mdvn.model.sapi.repository.ModelRepository;
import com.mdvns.mdvn.model.sapi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public RestResponse getModelList() throws Exception {
        List<Model> models = modelRepository.findAll();
        RestResponse restResponse = new RestResponse();
        restResponse.setResponseMsg("");
        restResponse.setResponseCode("0");
        restResponse.setResponseBody(models);
        return restResponse;
    }
}
