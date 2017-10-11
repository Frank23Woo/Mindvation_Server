package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.IUpdateProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class UpdateProjServiceImpl implements IUpdateProjService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateProjServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
     * 更改项目基本信息
     *
     * @param pro
     * @return
     */
    @Override
    public Project updateProjBaseInfo(Project pro) {
        LOG.info("start executing updateProjBaseInfo()方法.", this.CLASS);
        Project proj = this.projectRepository.findByProjId(pro.getProjId());
        if (proj == null) {
            throw new NullPointerException("project cannot be found");
        }
        if (!StringUtils.isEmpty(pro.getName()) && (!pro.getName().equals(proj.getName()))) {
            proj.setName(pro.getName());

        }
        if (!StringUtils.isEmpty(pro.getDescription()) && (!pro.getDescription().equals(proj.getDescription()))) {
            proj.setDescription(pro.getDescription());
        }
        if (!StringUtils.isEmpty(pro.getStartDate()) && (!pro.getStartDate().equals(proj.getStartDate()))) {
            proj.setStartDate(pro.getStartDate());

        }
        if (!StringUtils.isEmpty(pro.getEndDate()) && (!pro.getEndDate().equals(proj.getEndDate()))) {
            proj.setEndDate(pro.getEndDate());

        }
        if (!StringUtils.isEmpty(pro.getPriority()) && (!pro.getPriority().equals(proj.getPriority()))) {
            proj.setPriority(pro.getPriority());

        }
        if (!StringUtils.isEmpty(pro.getContingency()) && (!pro.getContingency().equals(proj.getContingency()))) {
            proj.setContingency(pro.getContingency());

        }
        if (!StringUtils.isEmpty(pro.getStatus()) && (!pro.getStatus().equals(proj.getStatus()))) {
            proj.setStatus(pro.getStatus());

        }
        //之后ragStatus需要后台计算以后传给前台
        if (!StringUtils.isEmpty(pro.getRagStatus()) && (!pro.getRagStatus().equals(proj.getRagStatus()))) {
            proj.setRagStatus(pro.getRagStatus());
        }
        Project project = projectRepository.save(proj);
        LOG.info("finish executing updateProjBaseInfo()方法.", this.CLASS);
        return project;
    }

    /**
     * 更改项目负责人信息
     *
     * @param list
     * @return
     */
    @Override
    public List<Staff> updateProjLeaders(UpdatePLeadersRequest list) {
        LOG.info("start executing updateProjLeaders()方法.", this.CLASS);
        if (list == null || list.getLeaders().size() <= 0) {
            throw new NullPointerException("ProjLeaders List is empty");
        }
        String projId = list.getProjId();
        List<String> staffIdList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getLeaders().size(); i++) {
            ProjLeaders projLeader = new ProjLeaders();
            staffIdList.add(list.getLeaders().get(i).getStaffId());
            projLeader = this.projLeadersRepository.findByProjIdAndStaffId(projId, list.getLeaders().get(i).getStaffId());
            //不存在的加上
            if (projLeader == null) {
                list.getLeaders().get(i).setProjId(projId);
                list.getLeaders().get(i).setIsDeleted(0);
                this.projLeadersRepository.saveAndFlush(list.getLeaders().get(i));
            } else {
                //之前是负责人后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (projLeader.getIsDeleted().equals(1)) {
                    String sql = "UPDATE staff_proj_map SET is_deleted= 0 WHERE proj_id=" + "\"" + projId + "\"" + "AND staff_id =" + "\"" + list.getLeaders().get(i).getStaffId() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的负责人信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer leaders = new StringBuffer();
        for (int i = 0; i < staffIdList.size(); i++) {
            leaders.append("\"" + staffIdList.get(i) + "\"");
            leaders.append(",");
        }
        String pLeaders = leaders.substring(0, leaders.length() - 1);
        String sql = "UPDATE staff_proj_map SET is_deleted= 1 WHERE proj_id= " + "\"" + projId + "\"" + " AND staff_id NOT IN (" + pLeaders + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的负责人
        List<ProjLeaders> projLeaders = this.projLeadersRepository.findPLeders(projId);
        List<Staff> staffList = new ArrayList<>();
        for (int i = 0; i < projLeaders.size(); i++) {
            String staffId = projLeaders.get(i).getStaffId();
            Staff staff = this.staffRepository.findByStaffId(staffId);
            if (null == staff) {
                LOG.error("员工在员工库中不存在.", staff);
                throw new BusinessException(staff + "标签在员工库中不存在.");
            } else {
                staffList.add(staff);
            }
        }
        LOG.info("finish executing updateProjLeaders()方法.", this.CLASS);
        return staffList;
    }

    /**
     * 更改项目附件信息
     *
     * @param list
     * @return
     */
    @Override
    public List<ProjAttchUrls> updateProjAttchUrls(UpdatePAttchUrlsRequest list) {
        LOG.info("start executing updateProjAttchUrls()方法.", this.CLASS);
        if (list == null || list.getAttchUrls().size() <= 0) {
            throw new NullPointerException("getAttchUrls List is empty");
        }
        String projId = list.getProjId();
        List<String> AttachmentNameList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getAttchUrls().size(); i++) {
            ProjAttchUrls projAttchUrls = new ProjAttchUrls();
            AttachmentNameList.add(list.getAttchUrls().get(i).getAttachmentName());
            projAttchUrls = this.projAttchUrlsRepository.findByProjIdAndAttachmentName(projId, list.getAttchUrls().get(i).getAttachmentName());
            //不存在的加上
            if (projAttchUrls == null) {
                list.getAttchUrls().get(i).setProjId(projId);
                list.getAttchUrls().get(i).setIsDeleted(0);
                this.projAttchUrlsRepository.saveAndFlush(list.getAttchUrls().get(i));
            } else {
                //之前是附件后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (projAttchUrls.getIsDeleted().equals(1)) {
                    String sql = "UPDATE attachment_proj SET is_deleted= 0 WHERE proj_id=" + "\"" + projId + "\"" + "AND attachment_name =" + "\"" + list.getAttchUrls().get(i).getAttachmentName() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的附件信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer attchUrls = new StringBuffer();
        for (int i = 0; i < AttachmentNameList.size(); i++) {
            attchUrls.append("\"" + AttachmentNameList.get(i) + "\"");
            attchUrls.append(",");
        }
        String pAttchUrls = attchUrls.substring(0, attchUrls.length() - 1);
        String sql = "UPDATE attachment_proj SET is_deleted= 1 WHERE proj_id= " + "\"" + projId + "\"" + " AND attachment_name NOT IN (" + pAttchUrls + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的附件
        List<ProjAttchUrls> projAttchUrls = this.projAttchUrlsRepository.findPAttchUrls(projId);
        LOG.info("finish executing updateProjAttchUrls()方法.", this.CLASS);
        return projAttchUrls;
    }

    /**
     * 更改项目标签信息
     *
     * @param list
     * @return
     */
    @Override
    public List<Tag> updateProjTags(UpdatePTagsRequest list) {
        LOG.info("start executing updateProjTags()方法.", this.CLASS);
        if (list == null || list.getTags().size() <= 0) {
            throw new NullPointerException("ProjTags List is empty");
        }
        String projId = list.getProjId();
        List<String> tagIdList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getTags().size(); i++) {
            ProjTags projTag = new ProjTags();
            tagIdList.add(list.getTags().get(i).getTagId());
            projTag = this.projTagsRepository.findAllByProjIdAndTagId(projId, list.getTags().get(i).getTagId());
            //不存在的加上
            if (projTag == null) {
                list.getTags().get(i).setProjId(projId);
                list.getTags().get(i).setIsDeleted(0);
                this.projTagsRepository.saveAndFlush(list.getTags().get(i));
            } else {
                //之前是标签后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (projTag.getIsDeleted().equals(1)) {
                    String sql = "UPDATE tag_proj_map SET is_deleted= 0 WHERE proj_id=" + "\"" + projId + "\"" + "AND tag_id =" + "\"" + list.getTags().get(i).getTagId() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的标签信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer tags = new StringBuffer();
        for (int i = 0; i < tagIdList.size(); i++) {
            tags.append("\"" + tagIdList.get(i) + "\"");
            tags.append(",");
        }
        String pTags = tags.substring(0, tags.length() - 1);
        String sql = "UPDATE tag_proj_map SET is_deleted= 1 WHERE proj_id= " + "\"" + projId + "\"" + " AND tag_id NOT IN (" + pTags + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的标签信息
        List<ProjTags> projTags = this.projTagsRepository.findPTags(projId);
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < projTags.size(); i++) {
            String tagId = projTags.get(i).getTagId();
            Tag tag = this.tagRepository.findByTagId(tagId);
            if (null == tag) {
                LOG.error("标签在标签库中不存在.", tag);
                throw new BusinessException(tag + "标签在标签库中不存在.");
            } else {
                tagList.add(tag);
            }
        }
        LOG.info("finish executing updateProjTags()方法.", this.CLASS);
        return tagList;
    }

    /**
     * 更改项目模型信息
     *
     * @param list
     * @return
     */
    @Override
    public List<Model> updateProjModels(UpdatePModelsRequest list) {
        LOG.info("start executing updateProjModels()方法.", this.CLASS);
        if (list == null || list.getModels().size() <= 0) {
            throw new NullPointerException("ProjModels List is empty");
        }
        String projId = list.getProjId();
        List<String> modelList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getModels().size(); i++) {
            ProjModels projModels = new ProjModels();
            modelList.add(list.getModels().get(i).getModelId());
            projModels = this.projModelsRepository.findByProjIdAndModelId(projId, list.getModels().get(i).getModelId());
            //不存在的加上
            if (projModels == null) {
                list.getModels().get(i).setProjId(projId);
                list.getModels().get(i).setIsDeleted(0);
                this.projModelsRepository.saveAndFlush(list.getModels().get(i));
            } else {
                //之前是项目模型后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (projModels.getIsDeleted().equals(1)) {
                    String sql = "UPDATE model_proj_map SET is_deleted= 0 WHERE proj_id=" + "\"" + projId + "\"" + "AND model_id =" + "\"" + list.getModels().get(i).getModelId() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的模型信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer models = new StringBuffer();
        for (int i = 0; i < modelList.size(); i++) {
            models.append("\"" + modelList.get(i) + "\"");
            models.append(",");
        }
        String pModels = models.substring(0, models.length() - 1);
        String sql = "UPDATE model_proj_map SET is_deleted= 1 WHERE proj_id= " + "\"" + projId + "\"" + " AND model_id NOT IN (" + pModels + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的模型
        List<ProjModels> projModels = this.projModelsRepository.findPModels(projId);
        List<Model> mList = new ArrayList<>();
        for (int i = 0; i < projModels.size(); i++) {
            String modelId = projModels.get(i).getModelId();
            Model model = this.modelRepository.findByModelId(modelId);
            if (null == model) {
                LOG.error("模型在模型库中不存在.", model);
                throw new BusinessException(model + "模型在模型库中不存在.");
            } else {
                mList.add(model);
            }
        }
        LOG.info("finish executing updateProjModels()方法.", this.CLASS);
        return mList;
    }

    /**
     * 更改项目checkList信息
     *
     * @param list
     * @return
     */
    @Override
    public List<ProjChecklists> updateProjChecklists(UpdatePCheckListsRequest list) {
        LOG.info("start executing updateProjChecklists()方法.", this.CLASS);
        if (list == null || list.getCheckLists().size() <= 0) {
            throw new NullPointerException("ProjChecklists List is empty");
        }
        List<ProjChecklists> returnList = new ArrayList<>();
        List uuIds = new ArrayList();
        for (int i = 0; i < list.getCheckLists().size(); i++) {
            //新建checkList
            if (StringUtils.isEmpty(list.getCheckLists().get(i).getCheckListId())) {
                list.getCheckLists().get(i).setProjId(list.getProjId());
                list.getCheckLists().get(i).setCreatorId(list.getStaffId());
                list.getCheckLists().get(i).setStatus("new");
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                list.getCheckLists().get(i).setCreateTime(currentTime);
//                    list.getCheckLists().get(i).setLastUpdateTime(currentTime);
                list.getCheckLists().get(i).setIsDeleted(0);
                ProjChecklists pChecklist = projChecklistsRepository.saveAndFlush(list.getCheckLists().get(i));
                pChecklist.setCheckListId("T"+pChecklist.getUuId());
                ProjChecklists pChecklists = projChecklistsRepository.saveAndFlush(pChecklist);
                uuIds.add(pChecklists.getUuId());
//                returnList.add(pChecklists);
            } else {
                //修改checkList
                ProjChecklists record = new ProjChecklists();
                record = this.projChecklistsRepository.findByCheckListId(list.getCheckLists().get(i).getCheckListId());
            /* indicate if this record has updated*/
                Boolean flag = false;
                if (record == null) {
                    throw new NullPointerException("Record cannot be found");
                }
                uuIds.add(record.getUuId());

            /* Determine if the attributes of this record need to be updated*/
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getCheckListDesc()) && (!list.getCheckLists().get(i).getCheckListDesc().equals(record.getCheckListDesc()))) {
                    record.setCheckListDesc(list.getCheckLists().get(i).getCheckListDesc());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getStartDate()) && (!list.getCheckLists().get(i).getStartDate().equals(record.getStartDate()))) {
                    record.setStartDate(list.getCheckLists().get(i).getStartDate());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getEndDate()) && (!list.getCheckLists().get(i).getEndDate().equals(record.getEndDate()))) {
                    record.setEndDate(list.getCheckLists().get(i).getEndDate());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getAssignerId()) && (!list.getCheckLists().get(i).getAssignerId().equals(record.getAssignerId()))) {
                    record.setAssignerId(list.getCheckLists().get(i).getAssignerId());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getExecutorId()) && (!list.getCheckLists().get(i).getExecutorId().equals(record.getExecutorId()))) {
                    record.setExecutorId(list.getCheckLists().get(i).getExecutorId());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getCheckLists().get(i).getStatus()) && (!list.getCheckLists().get(i).getStatus().equals(record.getStatus()))) {
                    record.setStatus(list.getCheckLists().get(i).getStatus());
                    flag = true;
                }
                if (record.getIsDeleted().equals(1)) {
                    record.setIsDeleted(0);
                    flag = true;
                }

                if (flag) {
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    record.setLastUpdateTime(currentTime);
                    if (!StringUtils.isEmpty(list.getCheckLists().get(i).getStatus()) && (list.getCheckLists().get(i).getStatus() == "close")) {
                        record.setCloseTime(currentTime);
                    }
                    record = this.projChecklistsRepository.saveAndFlush(record);
//                returnList.add(record);
                }
            }
        }
        //数组转化为字符串格式
        StringBuffer uuIdList = new StringBuffer();
        for (int j = 0; j < uuIds.size(); j++) {
            uuIdList.append(uuIds.get(j));
            uuIdList.append(",");
        }
        String pUuIdList = uuIdList.substring(0, uuIdList.length() - 1);
        String sql = "UPDATE checklist_proj SET is_deleted= 1 WHERE proj_id= " + "\"" + list.getProjId() + "\"" + " AND uu_id NOT IN (" + pUuIdList + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的项目checkList
        List<ProjChecklists> projChecklists = this.projChecklistsRepository.findPChecklists(list.getProjId());
        returnList.addAll(projChecklists);
        LOG.info("finish executing updateProjChecklists()方法.", this.CLASS);
        return returnList;
    }

}
