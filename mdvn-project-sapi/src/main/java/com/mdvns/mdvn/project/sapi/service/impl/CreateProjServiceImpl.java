package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.common.beans.exception.ReturnFormat;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.SavePCheckListsRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.ICreateProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
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

    /**
     * 获取project整个列表
     *
     * @return
     */
    public RestDefaultResponse rtrvProjInfoList(RtrvProjectRequest request) throws SQLException {
        RtrvProjectResponse rtrvProjectResponse = new RtrvProjectResponse();
        //获取多张表数据联合查询然后分页
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        Integer m = page * pageSize;
        Integer n = pageSize;
        List<Project> pageList = this.projectRepository.rtrvProjInfoList(request.getStaffId(), m, n);
        Long totalElements = this.projectRepository.getProjBaseInfoCount(request.getStaffId());
        rtrvProjectResponse.setProjects(pageList);
        rtrvProjectResponse.setTotalElements(totalElements);

        LOG.info("查询结果为：{}", rtrvProjectResponse);

        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", rtrvProjectResponse);
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
        if (createProjectRequest == null || createProjectRequest.getStaffId() == null ||
                createProjectRequest.getName() == null || createProjectRequest.getDescription() == null ||
                createProjectRequest.getStartDate() ==null || createProjectRequest.getEndDate() ==null) {
            throw new NullPointerException("createProjectRequest 或员工Id不能为空 或项目名称不能为空 或项目描述不能为空 或者项目开始结束时间不能为空");
        }
        proj.setName(createProjectRequest.getName());
        proj.setDescription(createProjectRequest.getDescription());
        proj.setCreatorId(createProjectRequest.getStaffId());
        proj.setIsDeleted(0);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        proj.setCreateTime(currentTime);
        proj.setStatus("new");

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
        Project project = projectRepository.save(proj);
        ResponseEntity<?> responseEntity = new ResponseEntity<Object>(project, HttpStatus.OK);
//        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", project);
        return responseEntity;
    }


    /**
     * 通过uuId获取项目的projId(触发器引发的问题)
     *
     * @param proj
     * @return
     */
    @Override
    public Project getProjIdByUuId(Project proj) {
        Integer uuId = proj.getUuId();
        String projId = projectRepository.getProjId(uuId);
        proj.setProjId(projId);
        return proj;
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
        return pChecklists;
    }

    /**
     * 通过checklist的uuid查询它的checklistId(触发器的原因)
     *
     * @param pChecklists
     * @return
     */
    @Override
    public List<ProjChecklists> getChecklistIdByUuId(List<ProjChecklists> pChecklists) {
        for (int i = 0; i < pChecklists.size(); i++) {
            Integer uuid = pChecklists.get(i).getUu_id();
            String checklistId = projChecklistsRepository.getPChecklistId(uuid);
            pChecklists.get(i).setCheckListId(checklistId);
            pChecklists.get(i).setIsDeleted(0);
        }
        return pChecklists;
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
