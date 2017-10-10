package com.mdvns.mdvn.tag.papi.service;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.tag.papi.domain.*;
import org.springframework.http.ResponseEntity;

public interface TagService {


    ResponseEntity<?> createTag(CreateTagRequest request);

    RestResponse rtrvTagList(RetrieveTagListRequest request);

    ResponseEntity<?> updateQuoteCnt(UpdateQuoteCntRequest updateQuoteCntRequest);
}
