package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.project.sapi.domain.ProjChecklistsDetail;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectDetailRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.IRtrvProjDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RtrvProjDetailServiceImpl implements IRtrvProjDetailService {
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
    private TagRepository tagRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelRepository modelRepository;

    /**
     * 获得某个项目基础信息
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvProjBaseInfo(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjBaseInfo()方法.", this.CLASS);
        Project project = this.projectRepository.rtrvProjBaseInfo(request.getProjId());
//        if (null == project) {
//            LOG.error("项目不存在.", project);
//            throw new NullPointerException(project + "项目不存在.");
//        }
        LOG.info("finish executing rtrvProjBaseInfo()方法.", this.CLASS);
        return ResponseEntity.ok().body(project);
    }

    /**
     * 获得某个项目负责人信息
     *
     * @param request
     * @return
     */
    @Override
    public List<Staff> rtrvProjLeders(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjLeders()方法.", this.CLASS);
        List<ProjLeaders> projLeaders = this.projLeadersRepository.findPLeders(request.getProjId());
        List<Staff> staffList = new ArrayList<>();
        for (int i = 0; i < projLeaders.size(); i++) {
            String staffId = projLeaders.get(i).getStaffId();
            Staff staff = this.staffRepository.findByStaffId(staffId);
            if (null == staff) {
                LOG.error("员工在员工库中不存在.", staff);
                throw new BusinessException(staff + "标签在员工库中不存在.");
            }else{
                staffList.add(staff);
            }
        }
        LOG.info("finish executing rtrvProjLeders()方法.", this.CLASS);
        return staffList;
    }

    /**
     * 获得某个项目标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<Tag> rtrvProjTags(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjTags()方法.", this.CLASS);
        List<ProjTags> projTags = this.projTagsRepository.findPTags(request.getProjId());
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < projTags.size(); i++) {
            String tagId = projTags.get(i).getTagId();
            Tag tag = this.tagRepository.findByTagId(tagId);
            if (null == tag) {
                LOG.error("标签在标签库中不存在.", tag);
                throw new BusinessException(tag + "标签在标签库中不存在.");
            }else{
                tagList.add(tag);
            }
        }
        LOG.info("finish executing rtrvProjTags()方法.", this.CLASS);
        return tagList;
    }

    /**
     * 获得某个项目模型信息
     *
     * @param request
     * @return
     */
    @Override
    public List<Model> rtrvProjModels(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjModels()方法.", this.CLASS);
        List<ProjModels> projModels = this.projModelsRepository.findPModels(request.getProjId());
        List<Model> modelList = new ArrayList<>();
        for (int i = 0; i < projModels.size(); i++) {
            String modelId = projModels.get(i).getModelId();
            Model model = this.modelRepository.findByModelId(modelId);
            if (null == model) {
                LOG.error("模型在模型库中不存在.", model);
                throw new BusinessException(model + "模型在模型库中不存在.");
            }else{
                modelList.add(model);
            }
        }
        LOG.info("finish executing rtrvProjModels()方法.", this.CLASS);
        return modelList;
    }

    /**
     * 获得某个项目checkList信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjChecklistsDetail> rtrvProjCheckLists(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjCheckLists()方法.", this.CLASS);
        List<ProjChecklists> projChecklists = this.projChecklistsRepository.findPChecklists(request.getProjId());
        List<ProjChecklistsDetail> projChecklistsDetails = new ArrayList<>();
        for (int i = 0; i < projChecklists.size(); i++) {
            ProjChecklistsDetail projChecklistsDetail = new ProjChecklistsDetail();
            String creatorId = projChecklists.get(i).getCreatorId();
            String assignerId = projChecklists.get(i).getAssignerId();
            String executorId = projChecklists.get(i).getExecutorId();
            if(!StringUtils.isEmpty(executorId)){
                Staff executor = this.staffRepository.findByStaffId(executorId);
                if (null == executor) {
                    LOG.error("执行者在员工库中不存在.", executor);
                    throw new BusinessException(executor + "执行者在员工库中不存在.");
                }else{
                    projChecklistsDetail.setExecutorInfo(executor);
                }
            }
            Staff creator = this.staffRepository.findByStaffId(creatorId);
            Staff assigner = this.staffRepository.findByStaffId(assignerId);
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
            projChecklistsDetail.setProjChecklists(projChecklists.get(i));
            projChecklistsDetails.add(projChecklistsDetail);
        }
        LOG.info("finish executing rtrvProjCheckLists()方法.", this.CLASS);
        return projChecklistsDetails;
    }

    /**
     * 获得某个项目附件信息
     *
     * @param request
     * @return
     */
    @Override
    public List<ProjAttchUrls> rtrvProjAttUrls(RtrvProjectDetailRequest request) {
        LOG.info("start executing rtrvProjAttUrls()方法.", this.CLASS);
        List<ProjAttchUrls> projAttchUrls = this.projAttchUrlsRepository.findPAttchUrls(request.getProjId());
        LOG.info("finish executing rtrvProjAttUrls()方法.", this.CLASS);
        return projAttchUrls;
    }
}
