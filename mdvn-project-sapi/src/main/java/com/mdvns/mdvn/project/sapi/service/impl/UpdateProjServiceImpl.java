package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.project.sapi.domain.UpdatePAttchUrlsRequest;
import com.mdvns.mdvn.project.sapi.domain.UpdatePLeadersRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjAttchUrls;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import com.mdvns.mdvn.project.sapi.repository.*;
import com.mdvns.mdvn.project.sapi.service.IUpdateProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.validation.constraints.Null;
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
    public List<ProjLeaders> updateProjLeaders(UpdatePLeadersRequest list) {
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
        LOG.info("finish executing updateProjLeaders()方法.", this.CLASS);
        return projLeaders;
    }

    /**
     * 更改项目附件信息
     *
     * @param list
     * @return
     */
    @Override
    public List<ProjAttchUrls> updateProjAttchUrls(UpdatePAttchUrlsRequest list) {
//        LOG.info("start executing updateProjAttchUrls()方法.", this.CLASS);
//        if (list == null || list.getAttchUrls().size() <= 0) {
//            throw new NullPointerException("getAttchUrls List is empty");
//        }
//        String projId = list.getProjId();
//        List<String> AttachmentNameList = new ArrayList();
//        //将数据库中没有的插入
//        for (int i = 0; i < list.getAttchUrls().size(); i++) {
//            ProjAttchUrls projAttchUrls = new ProjAttchUrls();
//            AttachmentNameList.add(list.getAttchUrls().get(i).getAttachmentName());
//            projAttchUrls = this.projLeadersRepository.findByProjIdAndStaffId(projId, list.getLeaders().get(i).getStaffId());
//            //不存在的加上
//            if (projAttchUrls == null) {
//                list.getLeaders().get(i).setProjId(projId);
//                list.getLeaders().get(i).setIsDeleted(0);
//                this.projLeadersRepository.saveAndFlush(list.getLeaders().get(i));
//            } else {
//                //之前是负责人后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
//                if (projLeader.getIsDeleted().equals(1)) {
//                    String sql = "UPDATE staff_proj_map SET is_deleted= 0 WHERE proj_id=" + "\"" + projId + "\"" + "AND staff_id =" + "\"" + list.getLeaders().get(i).getStaffId() + "\"" + "";
//                    this.jdbcTemplate.update(sql);
//                }
//            }
//        }
//        //将数据库中将要删除的负责人信息修改is_deleted状态
//        //数组转化为字符串格式
//        StringBuffer leaders = new StringBuffer();
//        for (int i = 0; i < AttachmentIdList.size(); i++) {
//            leaders.append("\"" + AttachmentIdList.get(i) + "\"");
//            leaders.append(",");
//        }
//        String pLeaders = leaders.substring(0, leaders.length() - 1);
//        String sql = "UPDATE staff_proj_map SET is_deleted= 1 WHERE proj_id= " + "\"" + projId + "\"" + " AND staff_id NOT IN (" + pLeaders + ")";
//        this.jdbcTemplate.update(sql);
//        //查询数据库中有效的负责人
//        List<ProjLeaders> projLeaders = this.projLeadersRepository.findPLeders(projId);
//        LOG.info("finish executing updateProjAttchUrls()方法.", this.CLASS);
        return null;
    }
}
