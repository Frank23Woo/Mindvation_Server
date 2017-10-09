package com.mdvns.mdvn.model.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import org.springframework.http.ResponseEntity;

public interface ModelService {
    RestResponse retrieveModelList() throws Exception;

    ResponseEntity<?> createModel(CreateModelRequest createTagRequest);
}
