package com.mdvns.mdvn.reqmnt.sapi.service.impl;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.ReqmntCheckListDetail;
import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import com.mdvns.mdvn.reqmnt.sapi.repository.*;
import com.mdvns.mdvn.reqmnt.sapi.service.IRtrvReqmntDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RtrvReqmntDetailServiceImpl implements IRtrvReqmntDetailService {

    @Autowired
    private ReqmntRepository reqmntRepository;

    @Autowired
    private ReqmntAttchUrlsRepository reqmntAttchUrlsRepository;

    @Autowired
    private ReqmntCheckListRepository reqmntCheckListRepository;

    @Autowired
    private ReqmntTagRepository reqmntTagRepository;

    @Autowired
    private ReqmntMemberRepository reqmntMemberRepository;

    @Override
    public ResponseEntity<?> rtrvReqmntInfo(RtrvReqmntInfoRequest request) {
        final String reqmntId = request.getReqmntId();

        if (StringUtils.isEmpty(request)) {
            throw new IllegalArgumentException("reqmntId should not be null");
        }
        ResponseEntity<RequirementInfo> responseEntity = null;
        RequirementInfo requirementInfo = reqmntRepository.findByReqmntIdAndIsDeleted(reqmntId, 0);
        if (requirementInfo == null) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<>(requirementInfo, HttpStatus.OK);
        }
        return responseEntity;
    }

    @Override
    public List<ReqmntTag> rtrvReqmntTags(String reqmntId) {
        return reqmntTagRepository.findAllByReqmntIdAndIsDeleted(reqmntId,0);
    }



    @Override
    public List<ReqmntCheckListDetail> rtrvReqmntCheckLists(RtrvReqmntInfoRequest request) {
        return null;
    }

    @Override
    public List<ReqmntAttchUrl> rtrvReqmntAttUrls(String reqmntId) {
        return reqmntAttchUrlsRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
    }

    @Override
    public List<ReqmntMember> rtrvReqmntMember(String requmntId) {
        return reqmntMemberRepository.findByReqmntIdAndIsDeleted(requmntId, 0);
    }

    @Override
    public List<ReqmntCheckList> rtrvReqmntCheckList(String reqmntId) {
        return reqmntCheckListRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
    }
}
