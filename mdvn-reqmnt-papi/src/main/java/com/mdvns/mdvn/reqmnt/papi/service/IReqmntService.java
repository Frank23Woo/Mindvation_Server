package com.mdvns.mdvn.reqmnt.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.papi.domain.*;


public interface IReqmntService {
//    /**
//     * 获取project整个列表
//     * @param rtrvReqmntRequest
//     * @return
//     */
//    RtrvReqmntResponse rtrvProjInfoList(RtrvReqmntRequest rtrvReqmntRequest);

    /**
     * 创建project
     * @param createReqmntRequest
     * @return
     */
    RestResponse createReqmnt(CreateReqmntRequest createReqmntRequest);

//    /**
//     * 更改project
//     * @param updateReqmntRequest
//     * @return
//     */
//    UpdateProjectResponse updateProject(UpdateReqmntRequest updateReqmntRequest);
}
