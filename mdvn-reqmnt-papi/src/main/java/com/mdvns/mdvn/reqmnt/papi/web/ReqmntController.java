package com.mdvns.mdvn.reqmnt.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.papi.domain.*;
import com.mdvns.mdvn.reqmnt.papi.service.IReqmntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class ReqmntController {

    @Autowired
    private IReqmntService iReqmntService;
//    /**
//     * 获取project整个列表
//     * @return
//     */
//    @PostMapping(value="/rtrvProjInfoList")
//    public RtrvReqmntResponse rtrvProjInfoList(@RequestBody RtrvReqmntRequest rtrvReqmntRequest){
//        return this.iReqmntService.rtrvProjInfoList(rtrvReqmntRequest);
//    }

    /**
     * Create Requirement
     * @param createReqmntRequest
     * @return
     */
    @PostMapping(value="/createReqmnt")
    public RestResponse createReqmnt(@RequestBody CreateReqmntRequest createReqmntRequest){
        return iReqmntService.createReqmnt(createReqmntRequest);
    }

//    /**
//     * 更改项目
//     * @param updateReqmntRequest
//     * @return
//     */
//    @PostMapping(value="/updateProject")
//    public UpdateProjectResponse updateProject(@RequestBody UpdateReqmntRequest updateReqmntRequest){
//        return projService.updateProject(updateReqmntRequest);
//    }

}
