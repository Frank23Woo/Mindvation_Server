package com.mdvns.mdvn.reqmnt.sapi.service.impl;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateReqmntServiceImpl implements ICreateReqmntService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateReqmntServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
     * 获取requirment整个列表
     *
     * @return
     */
    public ResponseEntity<?> rtrvReqmntList(RtrvReqmntListRequest request) throws SQLException {
        RtrvReqmntListResponse rtrvReqmntListResponse = new RtrvReqmntListResponse();

        if (request.getPage() != null && request.getPageSize() != null) {
            if (request.getPage() < 1 || request.getPageSize() < 1) {
                throw new IllegalArgumentException("Illegal Arguments for Pagination");
            }

        }
        if (request.getPage() == null || request.getPageSize() == null) {
            List<RequirementInfo> list = this.reqmntRepository.findAllByProjIdAndIsDeletedOrderByUuIdAsc(request.getProjId(), 0);
            //计算storyPoint
            for (int i = 0; i < list.size(); i++) {
                String reqmntId = list.get(i).getReqmntId();
                Float sumStoryPoint = this.reqmntRepository.rtrvReqmntStoryPointCount(reqmntId);
                list.get(i).setTotalStoryPoint(sumStoryPoint);
            }
            this.reqmntRepository.save(list);
            //获取req列表重新计算project的进度
            RtrvAverageProjProgress pRequest = new RtrvAverageProjProgress();
            String projId = request.getProjId();
            pRequest.setProjId(projId);
            Float projProgress = this.averageProjProgress(pRequest);
            String sql = "UPDATE project SET progress = " + projProgress + " WHERE proj_id=" + "\"" + projId + "\"";
            this.jdbcTemplate.update(sql);
            //所有的reqmnt进度都为100时，project的状态变为done
            Float progresses = Float.valueOf(0);
            String status = this.reqmntRepository.rtrvStatus(projId);
            for (int i = 0; i < list.size(); i++) {
                progresses += list.get(i).getProgress();
            }
            if (progresses / list.size() == 100) {
                status = "done";
            }
            String statusSql = "UPDATE project SET status = " + "\""+ status+ "\"" + " WHERE proj_id=" + "\"" + projId + "\"";
            this.jdbcTemplate.update(statusSql);
            rtrvReqmntListResponse.setRequirementInfos(list);
            rtrvReqmntListResponse.setTotalElements(Long.valueOf(list.size()));
            return ResponseEntity.ok(rtrvReqmntListResponse);
        } else {
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            List<String> sortBy = request.getSortBy();
            Page<RequirementInfo> requirementInfos = null;
            PageRequest pageable = null;

            if (sortBy == null) {
                pageable = new PageRequest(page - 1, pageSize);
            } else {
                Sort.Order order = null;
                for (int i = 0; i < sortBy.size(); i++) {
                    order = new Sort.Order(Sort.Direction.ASC, sortBy.get(i));
                }
                Sort sort = new Sort(order);
                pageable = new PageRequest(page - 1, pageSize, sort);
            }

            requirementInfos = this.reqmntRepository.findAllByProjIdAndIsDeleted(request.getProjId(), 0, pageable);
            //计算storyPoint
            for (int i = 0; i < requirementInfos.getContent().size(); i++) {
                String reqmntId = requirementInfos.getContent().get(i).getReqmntId();
                Float sumStoryPoint = this.reqmntRepository.rtrvReqmntStoryPointCount(reqmntId);
                if(sumStoryPoint == null){
                    sumStoryPoint = Float.valueOf(0);
                }
                requirementInfos.getContent().get(i).setTotalStoryPoint(sumStoryPoint);
            }
            this.reqmntRepository.save(requirementInfos);
            rtrvReqmntListResponse.setRequirementInfos(requirementInfos.getContent());
            rtrvReqmntListResponse.setTotalElements(requirementInfos.getTotalElements());
            //获取req列表重新计算project的进度
            RtrvAverageProjProgress pRequest = new RtrvAverageProjProgress();
            String projId = request.getProjId();
            pRequest.setProjId(projId);
            Float projProgress = this.averageProjProgress(pRequest);
            String sql = "UPDATE project SET progress = " + projProgress + " WHERE proj_id=" + "\"" + projId + "\"";
            this.jdbcTemplate.update(sql);
            //所有的reqmnt进度都为100时，project的状态变为done
            Float progresses = Float.valueOf(0);
            String status = this.reqmntRepository.rtrvStatus(projId);
            for (int i = 0; i < requirementInfos.getContent().size(); i++) {
                progresses += requirementInfos.getContent().get(i).getProgress();
            }
            if (progresses / requirementInfos.getContent().size() == 100) {
                status = "done";
            }
            String statusSql = "UPDATE project SET status = " + "\""+ status+ "\"" + " WHERE proj_id=" + "\"" + projId + "\"";
            this.jdbcTemplate.update(statusSql);
            LOG.info("查询结果为：{}", rtrvReqmntListResponse);
            return ResponseEntity.ok(rtrvReqmntListResponse);
        }
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
        if (StringUtils.isEmpty(createReqmntRequest) || StringUtils.isEmpty(createReqmntRequest.getCreatorId()) || StringUtils.isEmpty(createReqmntRequest.getSummary()) || StringUtils.isEmpty(createReqmntRequest.getDescription()) || StringUtils.isEmpty(createReqmntRequest.getModelId())
                || StringUtils.isEmpty(createReqmntRequest.getProjId())) {
            throw new NullPointerException("Mandatory fields should not be empty for createReqmntRequest");
        }
        LOG.info("。。。。。保存需求开始。。。。。");
        requirementInfo.setProjId(createReqmntRequest.getProjId());
        requirementInfo.setSummary(createReqmntRequest.getSummary());
        requirementInfo.setDescription(createReqmntRequest.getDescription());
        requirementInfo.setCreatorId(createReqmntRequest.getCreatorId());
        requirementInfo.setModelId(createReqmntRequest.getModelId());
        if (!StringUtils.isEmpty(createReqmntRequest.getPriority())) {
            requirementInfo.setPriority(createReqmntRequest.getPriority());
        }
        if (!StringUtils.isEmpty(createReqmntRequest.getStartDate())) {
            requirementInfo.setStartDate(createReqmntRequest.getStartDate());
        }
        if (!StringUtils.isEmpty(createReqmntRequest.getEndDate())) {
            requirementInfo.setEndDate(createReqmntRequest.getEndDate());
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        requirementInfo.setCreateTime(currentTime);
        requirementInfo.setLastUpdateTime(currentTime);
        requirementInfo.setStatus("new");
        requirementInfo.setRagStatus("G");
        requirementInfo.setProgress((float) 0);
        requirementInfo.setTotalStoryPoint((float) 0);
        requirementInfo.setIsDeleted(0);
        requirementInfo.setFunctionLabelId(createReqmntRequest.getFunctionLabel().getLabelId());

        try {
            requirementInfo = reqmntRepository.saveAndFlush(requirementInfo);
            requirementInfo.setReqmntId("R" + requirementInfo.getUuId());
            //创建完reqmnt重新计算project的进度
            RtrvAverageProjProgress request = new RtrvAverageProjProgress();
            String projId = createReqmntRequest.getProjId();
            request.setProjId(projId);
            Float projProgress = this.averageProjProgress(request);
            String sql = "UPDATE project SET progress = " + projProgress + " WHERE proj_id=" + "\"" + projId + "\"";
            this.jdbcTemplate.update(sql);
            //保存reqmntId
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
        for (int i = 0; i < members.size(); i++) {
            members.get(i).setIsDeleted(0);
        }
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
            pChecklists.get(i).setCheckListId("RC" + pChecklists.get(i).getUuId());
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
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
            request.get(i).setUpdateTime(now);
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
            requirementInfo.setReqmntId((String) objs[1]);
        }
        return requirementInfo;

    }

    /**
     * Project的进度为所有Req的进度平均值（优先级设定优先级系数。低：0.2；中：0.3；高：0.5）
     * @param request
     * @return
     */
    @Override
    public Float averageProjProgress(RtrvAverageProjProgress request) {
        //获取一个proj下优先级种类
        List<Integer> prioritys = this.reqmntRepository.findPriority(request.getProjId());
        Float projProgressByPriority = Float.valueOf(0);
        for (int i = 0; i < prioritys.size(); i++) {
            Integer priority = prioritys.get(i);
            //获取一个proj下reqmnt的storypoint的总和(某个优先级下)
            Float storyPoints = this.reqmntRepository.rtrvStoryPointQty(request.getProjId(), priority);
            if (storyPoints == 0) {
                continue;
            }
            //获取某个proj下的某个优先级的reqmnt列表
            List<RequirementInfo> requirementInfos = this.reqmntRepository.findAllByProjIdAndIsDeletedAndPriority(request.getProjId(), 0, priority);
            //给各种优先级的情况分配优先级系数
            double flag = 0;
            /*高~中~低*/
            if (prioritys.size() == 3) {
                if (priority.equals(3)) {
                    flag = 0.5;
                }
                if (priority.equals(2)) {
                    flag = 0.3;
                } else {
                    flag = 0.2;
                }
            }
            /*高~中*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 5) {
                if (priority.equals(3)) {
                    flag = 0.6;
                } else {
                    flag = 0.4;
                }
            }
            /*高~低*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 4) {
                if (priority.equals(3)) {
                    flag = 0.7;
                } else {
                    flag = 0.3;
                }
            }
            /*中~低*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 3) {
                if (priority.equals(2)) {
                    flag = 0.6;
                } else {
                    flag = 0.4;
                }
            }
            if (prioritys.size() == 1) {
                flag = 1;
            }
            //计算某个优先级下的进度的平均值
            Float projAverageProgress = Float.valueOf(0);
            for (int j = 0; j < requirementInfos.size(); j++) {
                RequirementInfo requirementInfo = requirementInfos.get(j);
                Float progress = requirementInfo.getProgress();
                Float storyPoint = requirementInfo.getTotalStoryPoint();
                //某个story所占的比重
                Float spProportion = storyPoint / storyPoints;
                Float progressProportion = progress * spProportion;
                projAverageProgress += progressProportion;
            }
            Float projProgByPriority = ((float) (projAverageProgress * flag));
            projProgressByPriority += projProgByPriority;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        projProgressByPriority = Float.valueOf(df.format(projProgressByPriority));
        return projProgressByPriority;
    }

}
