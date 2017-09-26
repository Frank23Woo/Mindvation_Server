package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.IProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ProjServiceImpl implements IProjService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(ProjServiceImpl.class);

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
     * @return
     */
    public List<Project> rtrvProjInfoList(RtrvProjectRequest request) throws SQLException{

        Integer page = (request.getPage()==null)?0:request.getPage();
        Integer size = request.getPageSize();
        Integer pageSize = (null == request.getPageSize()) ? 6 : request.getPageSize();
        String sortBy = (request.getSortBy()==null)?"priority":request.getSortBy();
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Project> projPage = null;
        projPage = this.projectRepository.findAll(pageable);

        LOG.info("查询结果为：{}",projPage.getContent().toString());
        return projPage.getContent();

    }

    /**
     * 创建project(整个信息)
     * @param createProjectRequest
     * @return
     */
    @Override
    public Project createProject(CreateProjectRequest createProjectRequest) {
        CreateProjectResponse createProject = new CreateProjectResponse();

        //先查询之前创建的项目基本信息（主要是projId）
        Project pro = this.saveProject(createProjectRequest);
        Integer uuId = pro.getUuId();
        String projId = projectRepository.getProjId(uuId);
        pro.setProjId(projId);
        //返回项目基本信息
        createProject.setProjId(projId);
        createProject.setName(pro.getName());
        createProject.setDescription(pro.getDescription());
        createProject.setPriority(pro.getPriority());
        createProject.setStartDate(pro.getStartDate());
        createProject.setEndDate(pro.getEndDate());
        createProject.setContingency(pro.getContingency());
        //保存project负责人信息
        List<ProjLeaders> projLeaders = createProjectRequest.getLeaders();
        for (int i = 0; i <projLeaders.size() ; i++) {
            projLeaders.get(i).setProjId(projId);
        }
        projLeaders = projLeadersRepository.save(projLeaders);
        createProject.setLeaders(projLeaders);
        //保存project标签
        ProjTags projTag = new ProjTags();
        projTag.setProjId(projId);
        List<ProjTags> projTags = createProjectRequest.getTags();
        for (int i = 0; i <projTags.size() ; i++) {
            projTags.get(i).setProjId(projId);
        }
        projTags = projTagsRepository.save(projTags);
        createProject.setTags(projTags);
        //保存project项目模型
        List<ProjModels> projModels = createProjectRequest.getModels();
        for (int i = 0; i <projModels.size() ; i++) {
            projModels.get(i).setProjId(projId);
        }
        projModels = projModelsRepository.save(projModels);
        createProject.setModels(projModels);
        //保存project任务（checklist）
        List<ProjChecklists> projChecklists = createProjectRequest.getCheckLists();
        for (int i = 0; i <projChecklists.size() ; i++) {
            projChecklists.get(i).setProjId(projId);
            projChecklists.get(i).setAssignerId(createProjectRequest.getStaffId());
            Date date = new Date();
            projChecklists.get(i).setCreateTime(new Timestamp(date.getTime()));// new Date()为获取当前系统时间
        }
        List<ProjChecklists> PChecklists = this.saveCheckLists(projChecklists);
        //(先保存checkList) 再循环查询并返回checkList的id
        for (int i = 0; i <PChecklists.size() ; i++) {
            Integer uuid = PChecklists.get(i).getUu_id();
            String checklistId = projChecklistsRepository.getPChecklistId(uuid);
            PChecklists.get(i).setChecklistId(checklistId);
        }
        createProject.setCheckLists(PChecklists);
        //保存附件
        List<ProjAttchUrls> projAttchUrls = createProjectRequest.getAttchUrls();
        for (int i = 0; i <projAttchUrls.size() ; i++) {
            projAttchUrls.get(i).setProjId(projId);
        }
        projAttchUrls = projAttchUrlsRepository.save(projAttchUrls);
        createProject.setAttchUrls(projAttchUrls);
        return pro;
    }


    /**
     * 创建project（基本信息）
     * @param createProjectRequest
     * @return
     */
    @Override
    public Project saveProject(CreateProjectRequest createProjectRequest) {
        Project proj = new Project();
        //先保存项目基本信息
        if (createProjectRequest == null || createProjectRequest.getStaffId() == null ||
                createProjectRequest.getName() == null || createProjectRequest.getDescription() == null) {
            throw new NullPointerException("createProjectRequest 或员工Id不能为空 或项目名称不能为空 或项目描述不能为空");
        }
        proj.setName(createProjectRequest.getName());
        proj.setDescription(createProjectRequest.getDescription());
        proj.setCreatorId(createProjectRequest.getStaffId());

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
        return project;
    }

    /**
     * 保存project多条任务（checkLists）
     * @param projChecklists
     * @return
     */
    @Override
    public List<ProjChecklists> saveCheckLists(List<ProjChecklists> projChecklists) {
        projChecklists = projChecklistsRepository.save(projChecklists);
        return projChecklists;
    }


    /**
     * 通过主键查询单条project数据
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

    /**
     * 创建比赛时保存leaders信息
     * @param leaders
     * @return
     */
    @Override
    public List<ProjLeaders> saveProjLeaders(List<ProjLeaders> leaders) {
        List<ProjLeaders> projLeaders = projLeadersRepository.save(leaders);
        return projLeaders;
    }


}
