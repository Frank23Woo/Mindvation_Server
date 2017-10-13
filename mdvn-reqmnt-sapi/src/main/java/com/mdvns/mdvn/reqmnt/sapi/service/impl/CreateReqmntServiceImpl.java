package com.mdvns.mdvn.reqmnt.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.reqmnt.sapi.domain.*;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import com.mdvns.mdvn.reqmnt.sapi.repository.*;
import com.mdvns.mdvn.reqmnt.sapi.service.ICreateReqmntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateReqmntServiceImpl implements ICreateReqmntService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateReqmntServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private ReqmntRepository reqmntRepository;

    @Autowired
    private ReqmntMemberRepository reqmntMemberRepository;

    @Autowired
    private ReqmntTagRepository reqmntTagRepository;

    @Autowired
    private ReqmntCheckListRepository reqmntCheckListRepository;

    @Autowired
    private ReqmntAttchUrlsRepository reqmntAttchUrlsRepository;

    /**
     * 获取project整个列表
     *
     * @return
     */
    public ResponseEntity<?> rtrvReqmntList(RtrvReqmntListRequest request) throws SQLException {
        RtrvReqmntListResponse rtrvReqmntListResponse = new RtrvReqmntListResponse();

        if(request.getPage()!=null && request.getPageSize() !=null ){
            if(request.getPage() < 1 ||request.getPageSize() < 1){
                throw new IllegalArgumentException("Illegal Arguments for Pagination");
            }

        }

        if(request.getPage()==null || request.getPageSize() ==null){
            List<RequirementInfo> list = this.reqmntRepository.findAllByProjIdAndIsDeletedOrderByUuIdAsc(request.getProjId(),0);
            rtrvReqmntListResponse.setRequirementInfos(list);
            rtrvReqmntListResponse.setTotalElements(Long.valueOf(list.size()));
            return ResponseEntity.ok(rtrvReqmntListResponse);
        }else{
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            List<String> sortBy = request.getSortBy();
            Page<RequirementInfo> requirementInfos = null;
            PageRequest pageable = null;

            if(sortBy==null){
                 pageable = new PageRequest(page-1, pageSize);
            }else{
                Sort.Order order = null;
                for (int i = 0; i < sortBy.size(); i++) {
                    order = new Sort.Order(Sort.Direction.ASC,sortBy.get(i));
                }
                Sort sort = new Sort(order);
                 pageable = new PageRequest(page-1, pageSize,sort);
            }

            requirementInfos = this.reqmntRepository.findAllByProjIdAndIsDeleted(request.getProjId(),0,pageable);
            rtrvReqmntListResponse.setRequirementInfos(requirementInfos.getContent());
            rtrvReqmntListResponse.setTotalElements(requirementInfos.getTotalElements());

            LOG.info("查询结果为：{}", rtrvReqmntListResponse);
            return ResponseEntity.ok(rtrvReqmntListResponse);
        }


//        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", rtrvProjectListResponse);
    }

    /**
     * 创建project（基本信息）
     *
     * @param createReqmntRequest
     * @return
     */
    @Override
    public ResponseEntity<?> saveReqmnt(CreateReqmntRequest createReqmntRequest) {
        RequirementInfo requirementInfo = new RequirementInfo();
        //先保存项目基本信息
        if (StringUtils.isEmpty(createReqmntRequest) || StringUtils.isEmpty(createReqmntRequest.getCreatorId()) ||
                StringUtils.isEmpty(createReqmntRequest.getSummary()) || StringUtils.isEmpty(createReqmntRequest.getDescription()) ||
                StringUtils.isEmpty(createReqmntRequest.getStartDate()) || StringUtils.isEmpty(createReqmntRequest.getEndDate()) || StringUtils.isEmpty(createReqmntRequest.getProjId())) {
            throw new NullPointerException("Mandatory fields should not be empty for createReqmntRequest");
        }
        requirementInfo.setProjId(createReqmntRequest.getProjId());
        requirementInfo.setSummary(createReqmntRequest.getSummary());
        requirementInfo.setDescription(createReqmntRequest.getDescription());
        requirementInfo.setCreatorId(createReqmntRequest.getCreatorId());
        if (!StringUtils.isEmpty(createReqmntRequest.getPriority())) {
            requirementInfo.setPriority(createReqmntRequest.getPriority());
        }
        if (!StringUtils.isEmpty(createReqmntRequest.getStartDate())) {
            requirementInfo.setStartDate(createReqmntRequest.getStartDate());
        }
        if (!StringUtils.isEmpty(createReqmntRequest.getEndDate())) {
            requirementInfo.setEndDate(createReqmntRequest.getEndDate());
        }
        Long currentTime = System.currentTimeMillis();
        requirementInfo.setCreateTime(currentTime);
        requirementInfo.setStatus("new");
        requirementInfo.setRagStatus("G");
        requirementInfo.setProgress((double) 0);
        requirementInfo.setTotalStoryPoint(0);
        requirementInfo.setIsDeleted(0);
        requirementInfo.setFunctionLabelId(createReqmntRequest.getFunctionLabelId());

        try {
            requirementInfo = reqmntRepository.saveAndFlush(requirementInfo);
            requirementInfo.setRqmntId("R" + requirementInfo.getUuId());
            requirementInfo = reqmntRepository.saveAndFlush(requirementInfo);
            ResponseEntity<?> responseEntity = new ResponseEntity<Object>(requirementInfo, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            ResponseEntity<?> responseEntity = new ResponseEntity<Object>(requirementInfo, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    @Override
    public List<ReqmntMember> saveReqmntMembers(List<ReqmntMember> members) {
        List<ReqmntMember> result = reqmntMemberRepository.save(members);
        reqmntMemberRepository.flush();
        return result;
    }


    /**
     * 创建project时保存标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ReqmntTag> saveRTags(List<ReqmntTag> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        return reqmntTagRepository.save(request);
    }


    /**
     * 保存requirement多条任务（checkLists）
     *
     * @param request
     * @return
     */
    @Override
    public List<ReqmntCheckList> saveCheckLists(SaveRCheckListRequest request) {
        for (int i = 0; i < request.getCheckLists().size(); i++) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            request.getCheckLists().get(i).setCreateTime(currentTime);
            request.getCheckLists().get(i).setIsDeleted(0);
            request.getCheckLists().get(i).setStatus("new");
            request.getCheckLists().get(i).setCreatorId(request.getStaffId());
        }
        List<ReqmntCheckList> pChecklists = reqmntCheckListRepository.save(request.getCheckLists());

        for (int i = 0; i < pChecklists.size(); i++) {
            pChecklists.get(i).setCheckListId("RC"+pChecklists.get(i).getUuId());
        }
        return reqmntCheckListRepository.save(request.getCheckLists());
    }

    /**
     * 通过checklist的uuid查询它的checklistId(触发器的原因)(详细staff)
     *
     * @param request
     * @return
     */
    @Override
    public List<ReqmntCheckListDetail> getChecklistIdByUuId(UpdatePCheckListsRequest request) {
        for (int i = 0; i < request.getCheckLists().size(); i++) {
            Integer uuid = request.getCheckLists().get(i).getUuId();
            String checklistId = reqmntCheckListRepository.getPChecklistId(uuid);
            request.getCheckLists().get(i).setCheckListId(checklistId);
//            pChecklists.get(i).setIsDeleted(0);
        }
        List<ReqmntCheckList> projChecklists = this.reqmntCheckListRepository.findPChecklists(request.getProjId());
        List<ReqmntCheckListDetail> projChecklistsDetails = new ArrayList<>();
        for (int i = 0; i < projChecklists.size(); i++) {
            ReqmntCheckListDetail projChecklistsDetail = new ReqmntCheckListDetail();
            String creatorId = projChecklists.get(i).getCreatorId();
            String assignerId = projChecklists.get(i).getAssignerId();
            String executorId = projChecklists.get(i).getAssigneeId();
//            Staff creator = this.staffRepository.findByStaffId(creatorId);
//            Staff assigner = this.staffRepository.findByStaffId(assignerId);
//            Staff executor = this.staffRepository.findByStaffId(executorId);



//            if (null == creator) {
//                LOG.error("创建者在员工库中不存在.", creator);
//                throw new BusinessException(creator + "创建者在员工库中不存在.");
//            } else {
//                projChecklistsDetail.setCreatorInfo(creator);
//            }
//            if (null == assigner) {
//                LOG.error("设计者在员工库中不存在.", assigner);
//                throw new BusinessException(assigner + "设计者在员工库中不存在.");
//            } else {
//                projChecklistsDetail.setAssignerInfo(assigner);
//            }
//            if (null == executor) {
//                LOG.error("创建者在员工库中不存在.", executor);
//                throw new BusinessException(executor + "创建者在员工库中不存在.");
//            } else {
//                projChecklistsDetail.setExecutorInfo(executor);
//            }
            projChecklistsDetail.setReqmntCheckList(projChecklists.get(i));
            projChecklistsDetails.add(projChecklistsDetail);
        }
        return projChecklistsDetails;
    }

    /**
     * 创建project时保存附件信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ReqmntAttchUrl> saveRAttchUrl(List<ReqmntAttchUrl> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        return reqmntAttchUrlsRepository.save(request);
    }

    /**
     * 通过主键查询单条project数据
     *
     * @param uuId
     * @return
     */
    @Override
    public RequirementInfo getReqmnt(Integer uuId) {
        Object obj = reqmntRepository.rtrvProjBaseInfo(uuId);
        RequirementInfo requirementInfo = new RequirementInfo();
        Object[] objs = (Object[]) obj;
        if (obj.toString().length() > 0) {
            requirementInfo.setRqmntId((String) objs[1]);
        }
        return requirementInfo;

    }

}
