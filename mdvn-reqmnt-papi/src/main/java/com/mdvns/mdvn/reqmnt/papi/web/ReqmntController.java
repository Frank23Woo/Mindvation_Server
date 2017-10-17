package com.mdvns.mdvn.reqmnt.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.papi.domain.*;
import com.mdvns.mdvn.reqmnt.papi.service.IReqmntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class ReqmntController {

    @Autowired
    private IReqmntService iReqmntService;
    /**
     * 获取requirement整个列表
     * @return
     */
    @PostMapping(value="/rtrvReqmntList")
    public RestResponse rtrvReqmntList(@RequestBody RtrvReqmntListRequest rtrvReqmntListRequest){
        return this.iReqmntService.rtrvReqmntList(rtrvReqmntListRequest);
    }

    /**
     * Create Requirement
     * @param createReqmntRequest
     * @return
     */
    @PostMapping(value="/createReqmnt")
    public RestResponse createReqmnt(@RequestBody CreateReqmntRequest createReqmntRequest){
        return iReqmntService.createReqmnt(createReqmntRequest);
    }


    /**
     * 获取RequirementInfo
     * @return
     */
    @PostMapping(value="/rtrvReqmntInfo")
    public RestResponse rtrvReqmntInfo(@RequestBody RtrvReqmntInfoRequest request){
        return this.iReqmntService.rtrvReqmntInfo(request);
    }





    /**
     * 更改reqmnt
     * @param updateReqmntInfoRequest
     * @return
     */
    @PostMapping(value="/updateReqmntInfo")
    public RestResponse updateReqmntInfo(@RequestBody UpdateReqmntInfoRequest updateReqmntInfoRequest){
        return iReqmntService.updateReqmntInfo(updateReqmntInfoRequest);

    }

}
