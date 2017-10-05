package com.mdvns.mdvn.model.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
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
    public RestDefaultResponse getModelList() throws Exception {
        List<Model> models = modelRepository.findAll();
        RestDefaultResponse restDefaultResponse = new RestDefaultResponse();
        restDefaultResponse.setResponseMsg("");
        restDefaultResponse.setResponseCode("0");
        restDefaultResponse.setResponseBody(models);
        return restDefaultResponse;
    }
}
