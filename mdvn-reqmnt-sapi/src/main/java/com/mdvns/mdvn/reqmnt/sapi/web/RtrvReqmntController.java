package com.mdvns.mdvn.reqmnt.sapi.web;

import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoByModelIdResponse;
import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoByModelRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import com.mdvns.mdvn.reqmnt.sapi.service.impl.RtrvReqmntDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RtrvReqmntController {

    @Autowired
    private RtrvReqmntDetailServiceImpl service;

    @PostMapping(value="/rtrvReqmntInfo")
    private ResponseEntity<?> rtrvReqmntInfo(@RequestBody RtrvReqmntInfoRequest request){
        return service.rtrvReqmntInfo(request);
    }

    @PostMapping(value = "/rtrvReqmntMembers")
    private List<ReqmntMember> rtrvReqmntMember(@RequestBody String requmntId){
        return service.rtrvReqmntMember(requmntId);
    }

    @PostMapping(value = "/rtrvReqmntCheckList")
    private List<ReqmntCheckList> rtrvReqmntCheckList(@RequestBody String requmntId){
        return service.rtrvReqmntCheckList(requmntId);
    }

    @PostMapping(value = "/rtrvReqmntAttUrls")
    private List<ReqmntAttchUrl> rtrvReqmntAttUrls(@RequestBody String requmntId){
        return service.rtrvReqmntAttUrls(requmntId);
    }

    @PostMapping(value = "/rtrvReqmntTags")
    private List<ReqmntTag> rtrvReqmntTags(@RequestBody String requmntId){
        return service.rtrvReqmntTags(requmntId);
    }


    /**
     * 看板那里，获取model下的所有reqmnt信息
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvReqmntInfoBymodelId")
    private List<RtrvReqmntInfoByModelIdResponse> rtrvReqmntInfoBymodelId(@RequestBody RtrvReqmntInfoByModelRequest request){
        return service.rtrvReqmntInfoBymodelId(request);
    }



}
