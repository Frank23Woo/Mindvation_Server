package com.mdvns.mdvn.dashboard.sapi.service.impl;


import com.mdvns.mdvn.dashboard.sapi.domain.*;

import com.mdvns.mdvn.dashboard.sapi.domain.entity.Dashboard;
import com.mdvns.mdvn.dashboard.sapi.repository.DashboardRepository;
import com.mdvns.mdvn.dashboard.sapi.service.DashboardService;
import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
//    @Autowired
//    private SprintInfoRepository sprintInfoRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

//    @Autowired
//    private SprintInfo sprintInfo;

    @Autowired
    private Dashboard dashboard;


    @Override
    public List<Dashboard> findDashboardInfoById(String projId) {
        LOG.info("开始执行{} findDashboardInfoById()方法.", this.CLASS);
        List<Dashboard> dashboards = this.dashboardRepository.findByProjIdAndIsDeleted(projId,0);
        LOG.info("执行结束{} findDashboardInfoById()方法.", this.CLASS);
        return dashboards;
    }

    @Override
    public Dashboard findDashboardInfoByIds(RtrvDashboardRequest request) {
        LOG.info("开始执行{} findDashboardInfoByIds()方法.", this.CLASS);
        Dashboard dashboard = this.dashboardRepository.findByProjIdAndModelIdAndIsDeleted(request.getProjId(),request.getModleId(),0);
        LOG.info("执行结束{} findDashboardInfoByIds()方法.", this.CLASS);
        return dashboard;
    }

    /**
     * 创建看板
     * @param request
     * @return
     */
    @Override
    public Dashboard createDashboard(CreateDashboardRequest request) {
        LOG.info("开始执行{} createDashboard()方法.", this.CLASS);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Dashboard dashboard = new Dashboard();
        dashboard.setCreateTime(createTime);
        dashboard.setCreatorId(request.getCreatorId());
        dashboard.setProjId(request.getProjId());
        dashboard.setModelId(request.getModelId());
        dashboard.setIsDeleted(0);
        dashboard.setProductBacklogs(request.getProductBacklogs());
        dashboard = this.dashboardRepository.saveAndFlush(dashboard);
        dashboard.setDashboardId("D"+dashboard.getUuId());
        dashboard = this.dashboardRepository.saveAndFlush(dashboard);
        LOG.info("执行结束{} createDashboard()方法.", this.CLASS);
        return dashboard;
    }

    /**
     * 更改dashboard
     * @param request
     * @return
     */
    @Override
    public Dashboard updateDashboard(UpdateDashboardRequest request) {
        LOG.info("开始执行{} updateDashboard()方法.", this.CLASS);
        Dashboard dashboard = this.dashboardRepository.findByProjIdAndModelIdAndIsDeleted(request.getProjId(),request.getModelId(),0);
        String productBacklogs = dashboard.getProductBacklogs();
        String currentSprint = dashboard.getCurrentSprint();
        String nextSprint = dashboard.getNextSprint();
        List<String> productStoryIdList = new ArrayList<>();
        List<String> currentStoryIdList = new ArrayList<>();
        List<String> nextStoryIdList = new ArrayList<>();
        List productArrList = new ArrayList();
        List currentArrList = new ArrayList();
        List nextArrList = new ArrayList();
        if (productBacklogs !=null){
            String[] productStoryIds = productBacklogs.split(",");
            productStoryIdList = Arrays.asList(productStoryIds);
            productArrList = new ArrayList(productStoryIdList);
        }
        if (currentSprint !=null){
            String[] currentStoryIds = currentSprint.split(",");
            currentStoryIdList = Arrays.asList(currentStoryIds);
            currentArrList = new ArrayList(currentStoryIdList);
        }
        if (nextSprint !=null){
            String[] nextStoryIds = nextSprint.split(",");
            nextStoryIdList = Arrays.asList(nextStoryIds);
            nextArrList = new ArrayList(nextStoryIdList);
        }
        String storyId = request.getStoryId();
        Integer beforeMoving = request.getBeforeMoving();
        Integer afterMoving = request.getAfterMoving();
        if(beforeMoving.equals(0)){
            productArrList.remove(storyId);
        }
        if(beforeMoving.equals(1)){
            currentArrList.remove(storyId);
        }
        if(beforeMoving.equals(2)){
            nextArrList.remove(storyId);
        }
        if(afterMoving.equals(0)){
            productArrList.add(storyId);
        }
        if(afterMoving.equals(1)){
            currentArrList.add(storyId);
        }
        if(afterMoving.equals(2)){
            nextArrList.add(storyId);
        }
        String pStoryIds = StringUtils.join(productArrList, ",");
        String cStoryIds = StringUtils.join(currentArrList, ",");
        String nStoryIds = StringUtils.join(nextArrList, ",");
        dashboard.setProductBacklogs(pStoryIds);
        dashboard.setCurrentSprint(cStoryIds);
        dashboard.setNextSprint(nStoryIds);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        dashboard.setUpdateTime(createTime);
        dashboard = this.dashboardRepository.saveAndFlush(dashboard);
        LOG.info("执行结束{} updateDashboard()方法.", this.CLASS);
        return dashboard;
    }

//    /**
//     * @param request
//     * @return Dashboard
//     * @desc: 获取sprint
//     */
//    @Override
//    public ResponseEntity<?> rtrvSprint(RtrvSprintRequest request) throws SQLException {
//        LOG.info("开始执行{} rtrvSprint()方法.", this.CLASS);
//        Timestamp createTime = new Timestamp(System.currentTimeMillis());
//        //先判断sprint是否是初始化，先创建sprint
//        this.sprintInfoRepository.
//
//        if (StringUtils.isEmpty(request.getSprintId())){
//            //CurrentSprint
//            SprintInfo currentSprint = new SprintInfo();
//            currentSprint.setCreateTime(createTime);
//            currentSprint.setCreatorId(request.getCreatorId());
//            currentSprint.setIsDeleted(0);
//            currentSprint.setSpQty(0);
//            currentSprint.setSprintIndex(0);
//            currentSprint.setSubjectId(request.getReqmntId());
//            currentSprint = this.sprintInfoRepository.saveAndFlush(currentSprint);
//            currentSprint.setSprintId("SP"+currentSprint.getUuId());
//            currentSprint = this.sprintInfoRepository.saveAndFlush(currentSprint);
//            //NextSprint
//            SprintInfo nextSprint = new SprintInfo();
//            nextSprint.setCreateTime(createTime);
//            nextSprint.setCreatorId(request.getCreatorId());
//            nextSprint.setIsDeleted(0);
//            nextSprint.setSpQty(0);
//            nextSprint.setSprintIndex(1);
//            nextSprint.setSubjectId(request.getReqmntId());
//            nextSprint = this.sprintInfoRepository.saveAndFlush(nextSprint);
//            nextSprint.setSprintId("SP"+nextSprint.getUuId());
//            nextSprint = this.sprintInfoRepository.saveAndFlush(nextSprint);
//        }
//
//
//        sprintInfo.setCreateTime(createTime);
//        sprintInfo.set
//        tg.setCreateTime(createTime);
//        tg.setQuoteCnt(0);
//        //数据保存后dashboardId没有生成
//        sprintInfo = this.sprintInfoRepository.save(tg);
////        dashboard = this.dashboardRepository.findOne(dashboard.getUuid());
//        sprintInfo.setDashboardId("T" + sprintInfo.getUuId());
//        sprintInfo = this.sprintInfoRepository.save(sprintInfo);
//        LOG.info("执行结束{} rtrvSprint()方法.", this.CLASS);
//
//        return ResponseEntity.ok(sprintInfo);
//    }



    /**
     * 获取制定Id的任务列表
     *
     * @param dashboardId
     * @return
     */
//    public ResponseEntity<?> findById(String dashboardId) {
//        sprintInfo = this.sprintInfoRepository.findByDashboardId(dashboardId);
//        return ResponseEntity.ok(sprintInfo);
//    }

}
