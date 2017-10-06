package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.RtrvProjectDetailRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRtrvProjDetailService {

    ResponseEntity<?> rtrvProjBaseInfo(RtrvProjectDetailRequest request);

    List<Staff> rtrvProjLeders(RtrvProjectDetailRequest request);

    List<Tag> rtrvProjTags(RtrvProjectDetailRequest request);

    List<Model> rtrvProjModels(RtrvProjectDetailRequest request);

    List<ProjChecklists> rtrvProjCheckLists(RtrvProjectDetailRequest request);

    List<ProjAttchUrls> rtrvProjAttUrls(RtrvProjectDetailRequest request);
}
