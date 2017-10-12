package com.mdvns.mdvn.reqmnt.sapi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.sapi.domain.*;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;


import java.sql.SQLException;
import java.util.List;

public interface ICreateReqmntService {

    //获取project整个列表
    ResponseEntity<?> rtrvReqmntList(RtrvReqmntListRequest request) throws SQLException;
    //创建project时保存project(基本信息)
    ResponseEntity<?> saveReqmnt(CreateReqmntRequest request);
    //创建requirement保存成员信息
    List<ReqmntMember> saveReqmntMembers(List<ReqmntMember> members);
    //创建project时保存标签信息
    List<ReqmntTag> saveRTags(List<ReqmntTag> request);
    //创建project时保存project任务（checkLists）
    List<ReqmntCheckList> saveCheckLists(SaveRCheckListRequest request);
    //创建project时保存附件信息
    List<ReqmntAttchUrl> saveRAttchUrl(List<ReqmntAttchUrl>  request);
    List<ReqmntCheckListDetail> getChecklistIdByUuId(UpdatePCheckListsRequest request);
    RequirementInfo getReqmnt(Integer uuId);
}
