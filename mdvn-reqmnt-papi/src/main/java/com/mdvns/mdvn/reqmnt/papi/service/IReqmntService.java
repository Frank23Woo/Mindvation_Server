package com.mdvns.mdvn.reqmnt.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.papi.domain.*;


public interface IReqmntService {
    /**
     * 获取requirement整个列表
     * @param rtrvReqmntListRequest
     * @return
     */
    RestResponse rtrvReqmntList(RtrvReqmntListRequest rtrvReqmntListRequest);

    /**
     * 创建project
     * @param createReqmntRequest
     * @return
     */
    RestResponse createReqmnt(CreateReqmntRequest createReqmntRequest);
    RestResponse rtrvReqmntInfo(RtrvReqmntInfoRequest request);

//    /**
//     * 更改project
//     * @param updateReqmntRequest
//     * @return
//     */
//    UpdateProjectResponse updateProject(UpdateReqmntRequest updateReqmntRequest);
}
