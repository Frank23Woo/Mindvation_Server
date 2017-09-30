package com.mdvns.mdvn.model.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.model.papi.domain.Model;
import com.mdvns.mdvn.model.papi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public RestDefaultResponse retrieveModelList() throws Exception {
        RestDefaultResponse restDefaultResponse = new RestDefaultResponse();
        final String url = "http://localhost:10009/retrieveModelList";
        try {
            List<Model> models = restTemplate.postForObject(url, null, List.class);
            restDefaultResponse.setResponseCode("0");
            restDefaultResponse.setResponseMsg("");
            restDefaultResponse.setResponseBody(models);
        } catch (RestClientException e) {
            e.printStackTrace();
            restDefaultResponse.setResponseCode("0");
            restDefaultResponse.setResponseMsg("[服务器] IO异常");
        }

        return restDefaultResponse;
    }
}
