package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ReturnFormat;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.ICreateProjService;
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
public class CreateProjServiceImpl implements ICreateProjService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateProjServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjLeadersRepository projLeadersRepository;

    @Autowired
    private ProjTagsRepository projTagsRepository;

    @Autowired
    private ProjModelsRepository projModelsRepository;

    @Autowired
    private ProjChecklistsRepository projChecklistsRepository;

    @Autowired
    private ProjAttchUrlsRepository projAttchUrlsRepository;

    @Autowired
    private StaffRepository staffRepository;

    /**
     * 获取project整个列表
     *
     * @return
     */
    public RestResponse rtrvProjInfoList(RtrvProjectListRequest request) throws SQLException {
        RtrvProjectListResponse rtrvProjectListResponse = new RtrvProjectListResponse();
        //获取多张表数据联合查询然后分页
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        Integer m = page * pageSize;
        Integer n = pageSize;
//        List<Project> pageList = this.projectRepository.rtrvProjInfoList(request.getStaffId(), m, n);
        String sortBy = (request.getSortBy() == null) ? "uuId" : request.getSortBy();
        PageRequest pageable = new PageRequest(m, n, Sort.Direction.DESC, sortBy);
        Page<Project> pageList = this.projectRepository.findAll(pageable);
        //计算storyPoint
        for (int i = 0; i < pageList.getContent().size(); i++) {
            String projId = pageList.getContent().get(i).getProjId();
            Float sumStoryPoint = this.projectRepository.rtrvProjStoryPointCount(projId);
            pageList.getContent().get(i).setStoryPointQty(sumStoryPoint);
            //计算story
            Integer storyQty = this.projectRepository.rtrvProjStoryQty(projId);
            pageList.getContent().get(i).setStoryQty(storyQty);
            //计算checkListQty
            Integer checkListQty = this.projectRepository.rtrvChecklistQty(projId);
            pageList.getContent().get(i).setCheckListQty(checkListQty);
        }
        this.projectRepository.save(pageList.getContent());

//        Long totalElements = this.projectRepository.getProjBaseInfoCount(request.getStaffId());
        rtrvProjectListResponse.setProjects(pageList.getContent());
        rtrvProjectListResponse.setTotalElements(pageList.getTotalElements());

        LOG.info("查询结果为：{}", rtrvProjectListResponse);
        return RestResponseUtil.success(rtrvProjectListResponse);
//        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", rtrvProjectListResponse);
    }

    /**
     * 创建project（基本信息）
     *
     * @param createProjectRequest
     * @return
     */
    @Override
    public ResponseEntity<?> saveProject(CreateProjectRequest createProjectRequest) {
        Project proj = new Project();
        //先保存项目基本信息
        if (StringUtils.isEmpty(createProjectRequest) || StringUtils.isEmpty(createProjectRequest.getStaffId())||
                StringUtils.isEmpty(createProjectRequest.getName())|| StringUtils.isEmpty(createProjectRequest.getDescription()) ||
                StringUtils.isEmpty(createProjectRequest.getStartDate()) || StringUtils.isEmpty(createProjectRequest.getEndDate())) {
            throw new NullPointerException("createProjectRequest 或员工Id不能为空 或项目名称不能为空 或项目描述不能为空 或者项目开始结束时间不能为空");
        }
        proj.setName(createProjectRequest.getName());
        proj.setDescription(createProjectRequest.getDescription());
        proj.setCreatorId(createProjectRequest.getStaffId());
        proj.setIsDeleted(0);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        proj.setCreateTime(currentTime);
        proj.setStatus("new");
        proj.setRagStatus("G");
        proj.setProgress((float) 0);
        proj.setStoryPointQty((float) 0);
        proj.setStoryQty(0);
        proj.setCrStoryQty(0);
        proj.setCrStoryPointQty((float) 0);

        if (!StringUtils.isEmpty(createProjectRequest.getStartDate())) {
            proj.setStartDate(createProjectRequest.getStartDate());
        }
        if (!StringUtils.isEmpty(createProjectRequest.getEndDate())) {
            proj.setEndDate(createProjectRequest.getEndDate());
        }
        if (!StringUtils.isEmpty(createProjectRequest.getPriority())) {
            proj.setPriority(createProjectRequest.getPriority());
        }
        if (!StringUtils.isEmpty(createProjectRequest.getContingency())) {
            proj.setContingency(createProjectRequest.getContingency());
        }
        Project project = projectRepository.saveAndFlush(proj);
        project.setProjId("P"+project.getUuId());
        Project pro = projectRepository.saveAndFlush(project);
        ResponseEntity<?> responseEntity = new ResponseEntity<Object>(pro, HttpStatus.OK);
//        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", project);
        return responseEntity;
    }

    /**
     * 创建比赛时保存leaders信息
     *
     * @param leaders
     * @return
     */
    @Override
    public List<ProjLeaders> saveProjLeaders(List<ProjLeaders> leaders) {
        for (int i = 0; i < leaders.size(); i++) {
            leaders.get(i).setIsDeleted(0);
        }
        List<ProjLeaders> pleaders = projLeadersRepository.save(leaders);
        return pleaders;
    }

    /**
     * 创建project时保存标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjTags> savePTags(List<ProjTags> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<ProjTags> projTags = projTagsRepository.save(request);
        return projTags;
    }

    /**
     * 创建project时保存模型信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjModels> savePModels(List<ProjModels> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<ProjModels> projModels = projModelsRepository.save(request);
        return projModels;
    }

    /**
     * 保存project多条任务（checkLists）
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjChecklists> saveCheckLists(SavePCheckListsRequest request) {
        for (int i = 0; i < request.getCheckLists().size(); i++) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            request.getCheckLists().get(i).setCreateTime(currentTime);
            request.getCheckLists().get(i).setIsDeleted(0);
            request.getCheckLists().get(i).setStatus("new");
            request.getCheckLists().get(i).setCreatorId(request.getStaffId());
        }
        List<ProjChecklists> pChecklists = projChecklistsRepository.save(request.getCheckLists());
        for (int j = 0; j <pChecklists.size(); j++) {
            pChecklists.get(j).setCheckListId("T"+pChecklists.get(j).getUuId());
        }
        List<ProjChecklists> projChecklists = projChecklistsRepository.save(pChecklists);
        return projChecklists;
    }

    /**
     * 通过checklist的uuid查询它的checklistId(详细staff)（可与save方法合并）
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjChecklistsDetail> getChecklistIdByUuId(UpdatePCheckListsRequest request) {
        List<ProjChecklists> projChecklists = this.projChecklistsRepository.findPChecklists(request.getProjId());
        List<ProjChecklistsDetail> projChecklistsDetails = new ArrayList<>();
        for (int i = 0; i < projChecklists.size(); i++) {
            ProjChecklistsDetail projChecklistsDetail = new ProjChecklistsDetail();
            String creatorId = projChecklists.get(i).getCreatorId();
            String assignerId = projChecklists.get(i).getAssignerId();
            String executorId = projChecklists.get(i).getExecutorId();
            Staff creator = this.staffRepository.findByStaffId(creatorId);
            Staff assigner = this.staffRepository.findByStaffId(assignerId);
            Staff executor = this.staffRepository.findByStaffId(executorId);
            if (null == creator) {
                LOG.error("创建者在员工库中不存在.", creator);
                throw new BusinessException(creator + "创建者在员工库中不存在.");
            }else{
                projChecklistsDetail.setCreatorInfo(creator);
            }
            if (null == assigner) {
                LOG.error("设计者在员工库中不存在.", assigner);
                throw new BusinessException(assigner + "设计者在员工库中不存在.");
            }else{
                projChecklistsDetail.setAssignerInfo(assigner);
            }
            if (null == executor) {
                LOG.error("创建者在员工库中不存在.", executor);
                throw new BusinessException(executor + "创建者在员工库中不存在.");
            }else{
                projChecklistsDetail.setExecutorInfo(executor);
            }
            projChecklistsDetail.setProjChecklists(projChecklists.get(i));
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
    public List<ProjAttchUrls> savePAttchUrls(List<ProjAttchUrls> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<ProjAttchUrls> projAttchUrls = projAttchUrlsRepository.save(request);
        return projAttchUrls;
    }


    /**
     * 通过主键查询单条project数据
     *
     * @param uuId
     * @return
     */
    @Override
    public Project getProject(Integer uuId) {
        Object obj = projectRepository.rtrvProjBaseInfo(uuId);
        Project project = new Project();
        Object[] objs = (Object[]) obj;
        if (obj.toString().length() > 0) {
            project.setProjId((String) objs[1]);
        }
        return project;

    }

}
