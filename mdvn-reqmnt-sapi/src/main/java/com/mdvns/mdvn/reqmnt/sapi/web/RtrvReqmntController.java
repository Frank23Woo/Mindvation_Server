package com.mdvns.mdvn.reqmnt.sapi.web;

import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import com.mdvns.mdvn.reqmnt.sapi.service.impl.RtrvReqmntDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RtrvReqmntController {

    @Autowired
    private RtrvReqmntDetailService service;

    @PostMapping(value="/rtrvReqmntInfo")
    private ResponseEntity<?> rtrvReqmntInfo(@RequestBody RtrvReqmntInfoRequest request){
        return service.rtrvReqmntInfo(request);
    }

    @PostMapping(value = "rtrvReqmntMembers")
    private List<ReqmntMember> rtrvReqmntMember(@RequestBody String requmntId){
        return service.rtrvReqmntMember(requmntId);
    }

    @PostMapping(value = "rtrvReqmntCheckList")
    private List<ReqmntCheckList> rtrvReqmntCheckList(@RequestBody String requmntId){
        return service.rtrvReqmntCheckList(requmntId);
    }

    @PostMapping(value = "rtrvReqmntAttUrls")
    private List<ReqmntAttchUrl> rtrvReqmntAttUrls(@RequestBody String requmntId){
        return service.rtrvReqmntAttUrls(requmntId);
    }

}