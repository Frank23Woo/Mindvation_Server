package com.mdvns.mdvn.model.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.papi.domain.Model;
import com.mdvns.mdvn.model.papi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public ResponseEntity<?> createModel(CreateModelRequest createTagRequest) {
        return null;
    }
}
