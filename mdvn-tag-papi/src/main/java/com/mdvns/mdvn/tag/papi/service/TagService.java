package com.mdvns.mdvn.tag.papi.service;


import com.mdvns.mdvn.tag.papi.domain.*;
import org.springframework.http.ResponseEntity;

public interface TagService {


    ResponseEntity<?> createTag(CreateTagRequest request);

    ResponseEntity<?> rtrvTagList(RetrieveTagListRequest request);

    ResponseEntity<?> updateQuoteCnt(String tagId);

    ResponseEntity<?> findByName(String name);

    ResponseEntity<?> findById(String tagId);
}
