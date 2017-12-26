package com.mdvns.mdvn.reqmnt.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.papi.domain.*;
import org.springframework.http.ResponseEntity;


public interface IReqmntService {
    ResponseEntity<?> rtrvRequirementList(RtrvReqmntListRequest rtrvReqmntListRequest);

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

    /**
     * update reqmnt
     * @param request
     * @return
     */
    RestResponse updateReqmntInfo(UpdateReqmntInfoRequest request);
}
