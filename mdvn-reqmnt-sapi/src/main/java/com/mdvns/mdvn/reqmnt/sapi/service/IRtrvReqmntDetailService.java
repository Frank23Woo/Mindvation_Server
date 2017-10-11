package com.mdvns.mdvn.reqmnt.sapi.service;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.ReqmntCheckListDetail;
import com.mdvns.mdvn.reqmnt.sapi.domain.RtrvProjectDetailRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRtrvReqmntDetailService {

    ResponseEntity<?> rtrvReqmntBaseInfo(RtrvProjectDetailRequest request);

    List<Tag> rtrvReqmntTags(RtrvProjectDetailRequest request);

    List<ReqmntCheckListDetail> rtrvReqmntCheckLists(RtrvProjectDetailRequest request);

    List<ReqmntAttchUrl> rtrvReqmntAttUrls(RtrvProjectDetailRequest request);
}
