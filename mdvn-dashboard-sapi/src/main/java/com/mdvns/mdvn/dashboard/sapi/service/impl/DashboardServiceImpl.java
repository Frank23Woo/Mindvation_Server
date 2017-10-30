package com.mdvns.mdvn.dashboard.sapi.service.impl;


import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import com.mdvns.mdvn.dashboard.sapi.repository.SprintInfoRepository;
import com.mdvns.mdvn.dashboard.sapi.service.DashboardService;
import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Migan Wang
 * @Description: Dashboard sapi业务处理
 * @Date:
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);

    private final String CLASS = this.getClass().getName();
    /*Dashboard Repository*/
    @Autowired
    private SprintInfoRepository sprintInfoRepository;


    @Autowired
    private SprintInfo sprintInfo;


    @Override
    public List<SprintInfo> findDashboardInfoById(String projId) {
        LOG.info("开始执行{} findDashboardInfoById()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndIsDeletedAndSprintIndex(projId,0,0);
        LOG.info("执行结束{} findDashboardInfoById()方法.", this.CLASS);
        return dashboards;
    }

    @Override
    public List<SprintInfo> findDashboardInfoByIds(RtrvDashboardRequest request) {
        LOG.info("开始执行{} findDashboardInfoByIds()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndModelIdAndIsDeleted(request.getProjId(),request.getModleId(),0);
        LOG.info("执行结束{} findDashboardInfoByIds()方法.", this.CLASS);
        return dashboards;
    }

    /**
     * 创建看板
     * @param request
     * @return
     */
    @Override
    public SprintInfo createSprintInfo(CreateSprintInfoRequest request) {
        LOG.info("开始执行{} createSprintInfo()方法.", this.CLASS);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        SprintInfo sprintInfo = new SprintInfo();
        sprintInfo.setCreateTime(createTime);
        sprintInfo.setCreatorId(request.getCreatorId());
        sprintInfo.setSubjectId(request.getProjId());
        sprintInfo.setModelId(request.getModelId());
        sprintInfo.setLabelIds(request.getLabelIds());
        sprintInfo.setName(request.getName());
        sprintInfo.setIsDeleted(0);
        sprintInfo.setSprintIndex(request.getSprintIndex());
        sprintInfo.setItemIds(request.getItemIds());
        sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        LOG.info("执行结束{} createSprintInfo()方法.", this.CLASS);
        return sprintInfo;
    }

    /**
     * 更改dashboard
     * @param request
     * @return
     */
    @Override
    public List<SprintInfo> updateDashboard(UpdateSprintRequest request) {
        LOG.info("开始执行{} updateDashboard()方法.", this.CLASS);
        List<SprintInfo> sprintInfos = new ArrayList<>();
        for (int i = 0; i <request.getSprintInfos().size() ; i++) {
            Timestamp updateTime = new Timestamp(System.currentTimeMillis());
            sprintInfo = request.getSprintInfos().get(i);
            sprintInfo.setUpdateTime(updateTime);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
            sprintInfos.add(sprintInfo);
        }
        LOG.info("执行结束{} updateDashboard()方法.", this.CLASS);
        return sprintInfos;
    }

    @Override
    public SprintInfo updateSprintInfo(UpdateSprintInfoRequest request) {
        Integer uuId = request.getUuId();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        String storyIds = StringUtils.join(request.getStories(), ",");
        sprintInfo.setItemIds(storyIds);
        sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        return sprintInfo;
    }

}
