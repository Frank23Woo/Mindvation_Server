package com.mdvns.mdvn.dashboard.sapi.service.impl;


import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import com.mdvns.mdvn.dashboard.sapi.repository.SprintInfoRepository;
import com.mdvns.mdvn.dashboard.sapi.service.DashboardService;
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
    public List<SprintInfo> updateDashboard(UpdateDashboardRequest request) {
        LOG.info("开始执行{} updateDashboard()方法.", this.CLASS);
        List<SprintInfo> sprintInfos = new ArrayList<>();
        for (int i = 0; i <request.getSprintInfos().size() ; i++) {
            Timestamp updateTime = new Timestamp(System.currentTimeMillis());
            sprintInfo = request.getSprintInfos().get(i);
            sprintInfo.setUpdateTime(updateTime);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
            sprintInfos.add(sprintInfo);
        }
//        SprintInfo dashboard = this.dashboardRepository.findBySubjectIdAndModelIdAndIsDeleted(request.getProjId(),request.getModelId(),0);
//        String productBacklogs = dashboard.getProductBacklogs();
//        String currentSprint = dashboard.getCurrentSprint();
//        String nextSprint = dashboard.getNextSprint();
//        List<String> productStoryIdList = new ArrayList<>();
//        List<String> currentStoryIdList = new ArrayList<>();
//        List<String> nextStoryIdList = new ArrayList<>();
//        List productArrList = new ArrayList();
//        List currentArrList = new ArrayList();
//        List nextArrList = new ArrayList();
//        if (productBacklogs !=null){
//            String[] productStoryIds = productBacklogs.split(",");
//            productStoryIdList = Arrays.asList(productStoryIds);
//            productArrList = new ArrayList(productStoryIdList);
//        }
//        if (currentSprint !=null){
//            String[] currentStoryIds = currentSprint.split(",");
//            currentStoryIdList = Arrays.asList(currentStoryIds);
//            currentArrList = new ArrayList(currentStoryIdList);
//        }
//        if (nextSprint !=null){
//            String[] nextStoryIds = nextSprint.split(",");
//            nextStoryIdList = Arrays.asList(nextStoryIds);
//            nextArrList = new ArrayList(nextStoryIdList);
//        }
//        String storyId = request.getStoryId();
//        Integer beforeMoving = request.getBeforeMoving();
//        Integer afterMoving = request.getAfterMoving();
//        if(beforeMoving.equals(0)){
//            productArrList.remove(storyId);
//        }
//        if(beforeMoving.equals(1)){
//            currentArrList.remove(storyId);
//        }
//        if(beforeMoving.equals(2)){
//            nextArrList.remove(storyId);
//        }
//        if(afterMoving.equals(0)){
//            productArrList.add(storyId);
//        }
//        if(afterMoving.equals(1)){
//            currentArrList.add(storyId);
//        }
//        if(afterMoving.equals(2)){
//            nextArrList.add(storyId);
//        }
//        String pStoryIds = StringUtils.join(productArrList, ",");
//        String cStoryIds = StringUtils.join(currentArrList, ",");
//        String nStoryIds = StringUtils.join(nextArrList, ",");
//        dashboard.setProductBacklogs(pStoryIds);
//        dashboard.setCurrentSprint(cStoryIds);
//        dashboard.setNextSprint(nStoryIds);
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        dashboard.setUpdateTime(createTime);
//        dashboard = this.dashboardRepository.saveAndFlush(dashboard);
        LOG.info("执行结束{} updateDashboard()方法.", this.CLASS);
        return sprintInfos;
    }


}
