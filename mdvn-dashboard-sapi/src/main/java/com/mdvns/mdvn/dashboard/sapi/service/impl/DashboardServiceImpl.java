package com.mdvns.mdvn.dashboard.sapi.service.impl;


import com.mdvns.mdvn.common.utils.MdvnStringUtil;
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
    @Autowired
    private SprintInfoRepository sprintInfoRepository;


    @Autowired
    private SprintInfo sprintInfo;


    @Override
    public List<SprintInfo> findDashboardInfoById(RtrvAllStoryListRequest request) {
        LOG.info("开始执行{} findDashboardInfoById()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndIsDeletedAndSprintIndexAndStatusIsNot(request.getProjId(),request.getCreatorId(),0, 0,"close");
        LOG.info("执行结束{} findDashboardInfoById()方法.", this.CLASS);
        return dashboards;
    }

    @Override
    public List<SprintInfo> findDashboardInfoByIds(RtrvDashboardRequest request) {
        LOG.info("开始执行{} findDashboardInfoByIds()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndModelIdAndCreatorIdAndIsDeletedAndStatusIsNot(request.getProjId(), request.getModleId(),request.getCreatorId(), 0,"close");
        LOG.info("执行结束{} findDashboardInfoByIds()方法.", this.CLASS);
        return dashboards;
    }

    /**
     * 创建看板
     *
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
        sprintInfo.setStatus("notStart");
        sprintInfo.setSprintIndex(request.getSprintIndex());
        sprintInfo.setItemIds(request.getItemIds());
        sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        LOG.info("执行结束{} createSprintInfo()方法.", this.CLASS);
        return sprintInfo;
    }

    /**
     * 更改List<SprintInfo>
     *
     * @param request
     * @return
     */
    @Override
    public List<SprintInfo> updateDashboard(UpdateSprintRequest request) {
        LOG.info("开始执行{} updateDashboard()方法.", this.CLASS);
        List<SprintInfo> sprintInfos = new ArrayList<>();
        for (int i = 0; i < request.getSprintInfos().size(); i++) {
            Timestamp updateTime = new Timestamp(System.currentTimeMillis());
            sprintInfo = request.getSprintInfos().get(i);
            sprintInfo.setUpdateTime(updateTime);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
            sprintInfos.add(sprintInfo);
        }
        LOG.info("执行结束{} updateDashboard()方法.", this.CLASS);
        return sprintInfos;
    }

    /**
     * 通过uuId更改sprint
     *
     * @param request uuId
     * @return
     */
    @Override
    public SprintInfo updateSprintInfo(UpdateSprintInfoRequest request) {
        Integer uuId = request.getUuId();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        String storyIds = MdvnStringUtil.join(request.getStories(), ",");
        sprintInfo.setItemIds(storyIds);
        sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        return sprintInfo;
    }

    @Override
    public SprintInfo addStory(AddStoryRequest request) {
        String subjectId = request.getProjId();
        String storyId = request.getStoryId();

        sprintInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndNameAndIsDeleted(subjectId, request.getCreatorId(),"Product Backlogs", 0);
        if (sprintInfo != null){
            String storyIds = sprintInfo.getItemIds();
            storyIds = storyIds + "," + storyId;
            sprintInfo.setItemIds(storyIds);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        }
        return sprintInfo;
    }

    @Override
    public SprintInfo updateSprintStartStatus(UpdateSprintStartStatusRequest request) {
        Integer uuId = request.getUuId();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        sprintInfo.setStatus("start");
        sprintInfo.setIterationCycle(request.getIterationCycle());
        sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        return sprintInfo;
    }

    @Override
    public SprintInfo updateSprintCloseStatus(UpdateSprintCloseStatusRequest request) {
        if (request.getStories().size() > 0) {
            String storyIds = MdvnStringUtil.join(request.getStories(), ",");
            String[] sprintStoryIds = storyIds.split(",");
            List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);

            //移动之前的sprint
            Integer beforUuId = request.getBeforUuId();
            SprintInfo beforSprintInfo = this.sprintInfoRepository.findOne(beforUuId);
            beforSprintInfo.setStatus("close");
            String beforeStoryIds = beforSprintInfo.getItemIds();
            String[] beforesprintStoryIds = beforeStoryIds.split(",");
            List<String> beforesprintStoryIdList = Arrays.asList(beforesprintStoryIds);
            List arrayList = new ArrayList(beforesprintStoryIdList);
            for (int j = 0; j < sprintStoryIdList.size(); j++) {
                arrayList.remove(sprintStoryIdList.get(j));
            }
            beforeStoryIds = MdvnStringUtil.join(arrayList, ",");
            beforSprintInfo.setItemIds(beforeStoryIds);
            beforSprintInfo = this.sprintInfoRepository.saveAndFlush(beforSprintInfo);

            //移动之后的sprint
            Integer afterUuId = request.getAfterUuId();
            sprintInfo = this.sprintInfoRepository.findOne(afterUuId);
            String oldStoryIds = sprintInfo.getItemIds();
            sprintInfo.setItemIds(oldStoryIds + "," + storyIds);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        }else{
            Integer beforUuId = request.getBeforUuId();
            SprintInfo beforSprintInfo = this.sprintInfoRepository.findOne(beforUuId);
            beforSprintInfo.setStatus("close");
            beforSprintInfo = this.sprintInfoRepository.saveAndFlush(beforSprintInfo);
            Integer afterUuId = request.getAfterUuId();
            sprintInfo = this.sprintInfoRepository.findOne(afterUuId);
        }
        return sprintInfo;
    }

    @Override
    public List<SprintInfo> itSprint(Integer uuId) {
        List<SprintInfo> sprintInfos = new ArrayList<>();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        Integer sprintIndex = sprintInfo.getSprintIndex();
        String subjectId = sprintInfo.getSubjectId();
        String creatorId = sprintInfo.getCreatorId();
        SprintInfo sInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndSprintIndex(subjectId,creatorId,sprintIndex+1);
        SprintInfo spInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndSprintIndex(subjectId,creatorId,sprintIndex+2);
        sprintInfos.add(sInfo);
        if (spInfo != null) {
            sprintInfos.add(spInfo);
        }
        return sprintInfos;
    }

}
