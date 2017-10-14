package com.mdvns.mdvn.reqmnt.sapi.service;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.ReqmntCheckListDetail;
import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRtrvReqmntDetailService {

    ResponseEntity<?> rtrvReqmntInfo(RtrvReqmntInfoRequest request);

    List<Tag> rtrvReqmntTags(RtrvReqmntInfoRequest request);

    List<ReqmntCheckListDetail> rtrvReqmntCheckLists(RtrvReqmntInfoRequest request);

    List<ReqmntAttchUrl> rtrvReqmntAttUrls(String reqmntId);

    List<ReqmntMember> rtrvReqmntMember(String requmntId);

    List<ReqmntCheckList> rtrvReqmntCheckList(String reqmntId);

}
