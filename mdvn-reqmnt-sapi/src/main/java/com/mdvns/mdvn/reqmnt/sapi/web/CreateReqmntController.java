package com.mdvns.mdvn.reqmnt.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.reqmnt.sapi.domain.*;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import com.mdvns.mdvn.reqmnt.sapi.service.ICreateReqmntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class CreateReqmntController {
    private Logger LOG = LoggerFactory.getLogger(CreateReqmntController.class);

    @Autowired
    private ICreateReqmntService reqmntService;

    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvReqmntInfoList")
    public RestResponse rtrvProjInfoList(@RequestBody RtrvProjectListRequest request) throws SQLException{
        return reqmntService.rtrvProjInfoList(request);
    }

    /**
     *保存project（基本信息）
     */
    @PostMapping(value="/saveReqmnt")
    public ResponseEntity<?> saveProject(@RequestBody CreateReqmntRequest request){
        return reqmntService.saveReqmnt(request);
    }

    /**
     * 创建project时保存成员信息
     * @param members
     * @return
     */
    @PostMapping(value="/saveRMembers")
    public List<ReqmntMember> saveReqmntMembers(@RequestBody List<ReqmntMember> members){
        return reqmntService.saveReqmntMembers(members);
    }

    /**
     * 创建project时保存标签信息
     * @param request
     * @return
     */
    @PostMapping(value="/saveRTags")
    public List<ReqmntTag> savePTags(@RequestBody List<ReqmntTag>  request){
        List<ReqmntTag> projTags = reqmntService.saveRTags(request);
        return projTags;
    }

    /**
     * 创建requirement时保存任务（checkLists）(多了一个保存创建者信息的动作)
     * @param request
     * @return
     */
    @PostMapping(value="/saveRCheckLists")
    public List<ReqmntCheckList> saveCheckLists(@RequestBody SaveRCheckListRequest request){
        List<ReqmntCheckList> projChecklists = reqmntService.saveCheckLists(request);
        return projChecklists;
    }

    /**
     * 通过checklist的uuid查询它的checklistId(详细staff)
     * @param request
     * @return
     */
    @PostMapping(value="/checklistsListByUuId")
    public List<ReqmntCheckListDetail> getChecklistsListByUuId(@RequestBody UpdatePCheckListsRequest request){
        return this.reqmntService.getChecklistIdByUuId(request);

    }

    /**
     * 创建project时保存附件信息
     * @param request
     * @return
     */
    @PostMapping(value="/saveRAttchUrls")
    public List<ReqmntAttchUrl> savePAttchUrls(@RequestBody List<ReqmntAttchUrl> request){
        List<ReqmntAttchUrl> reqmntAttchUrls = reqmntService.saveRAttchUrl(request);
        return reqmntAttchUrls;
    }

    /**
     * 通过主键查询单条project数据
     * @param request
     * @return
     */
    @PostMapping(value="/getProject")
    public RequirementInfo getProject(@RequestBody RequirementInfo request){
        RequirementInfo requirementInfo = reqmntService.getReqmnt(request.getUuId());
        return requirementInfo;
    }

}
