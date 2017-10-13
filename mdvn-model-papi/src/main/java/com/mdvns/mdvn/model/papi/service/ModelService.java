package com.mdvns.mdvn.model.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.papi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.papi.domain.RetrieveModelListRequest;
import com.mdvns.mdvn.model.papi.domain.RtrvModelByIdRequest;
import org.springframework.http.ResponseEntity;

public interface ModelService {

    ResponseEntity<?> createModel(CreateModelRequest createModelRequest);

    RestResponse rtrvModelList(RetrieveModelListRequest request);

    ResponseEntity<?> updateQuoteCnt(String modelId);

    ResponseEntity<?> findByName(String name);

    RestResponse findById(RtrvModelByIdRequest request);
}
