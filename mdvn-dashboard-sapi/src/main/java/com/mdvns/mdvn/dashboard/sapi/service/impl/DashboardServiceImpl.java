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
import org.springframework.util.StringUtils;

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
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndIsDeletedAndSprintIndexAndStatusIsNot(request.getProjId(), request.getCreatorId(), 0, 0, "close");
        LOG.info("执行结束{} findDashboardInfoById()方法.", this.CLASS);
        return dashboards;
    }

    @Override
    public List<SprintInfo> findDashboardInfoByIds(RtrvDashboardRequest request) {
        LOG.info("开始执行{} findDashboardInfoByIds()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndModelIdAndCreatorIdAndIsDeletedAndStatusIsNot(request.getProjId(), request.getModleId(), request.getCreatorId(), 0, "close");
        LOG.info("执行结束{} findDashboardInfoByIds()方法.", this.CLASS);
        return dashboards;
    }

    /**
     * 查询所有dashboard信息
     *
     * @param request
     * @return
     */
    @Override
    public List<SprintInfo> findAllDashboardById(RtrvAllStoryListRequest request) {
        LOG.info("开始执行{} findDashboardInfoById()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndIsDeletedAndSprintIndexAndStatusIsNot(request.getProjId(), 0, 0, "close");
        LOG.info("执行结束{} findDashboardInfoById()方法.", this.CLASS);
        return dashboards;
    }

    /**
     * 更改dashboard时查询具体uuId
     *
     * @param request
     * @return
     */
    @Override
    public Integer findUuIdByIds(FindUuIdRequest request) {
        LOG.info("开始执行{} findUuIdByIds()方法.", this.CLASS);
        String subjectId = request.getProjId();
        String creatorId = request.getCreatorId();
        String modelId = request.getModelId();
        String name = request.getName();
        SprintInfo sprintInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndModelIdAndNameAndIsDeleted(subjectId, creatorId, modelId, name, 0);
        Integer uuId = sprintInfo.getUuId();
        LOG.info("执行结束{} findUuIdByIds()方法.", this.CLASS);
        return uuId;
    }

    @Override
    public List<SprintInfo> findAllDashboardInfoByIds(RtrvDashboardRequest request) {
        LOG.info("开始执行{} findAllDashboardInfoByIds()方法.", this.CLASS);
        List<SprintInfo> dashboards = this.sprintInfoRepository.findBySubjectIdAndModelIdAndIsDeletedAndStatusIsNot(request.getProjId(), request.getModleId(), 0, "close");
        LOG.info("执行结束{} findAllDashboardInfoByIds()方法.", this.CLASS);
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
        String modelId = request.getModelId();
        sprintInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndNameAndIsDeletedAndModelId(subjectId, request.getCreatorId(), "Product Backlogs", 0, modelId);
        if (sprintInfo != null) {
            String storyIds = sprintInfo.getItemIds();
            if (StringUtils.isEmpty(storyIds)) {
                storyIds = storyId;
            } else {
                storyIds = storyIds + "," + storyId;
            }
            sprintInfo.setItemIds(storyIds);
            sprintInfo = this.sprintInfoRepository.saveAndFlush(sprintInfo);
        }
        return sprintInfo;
    }

    /**
     * 开启迭代（同时开启一个项目相同模型下name相同的sprint）
     *
     * @param request
     * @return
     */
    @Override
    public SprintInfo updateSprintStartStatus(UpdateSprintStartStatusRequest request) {
        String creatorId = request.getCreatorId();
        String name = request.getName();
        String subjectId = request.getProjId();
        String modelId = request.getModelId();
        SprintInfo spInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndModelIdAndNameAndIsDeleted(subjectId, creatorId, modelId, name, 0);
        Integer uuId = spInfo.getUuId();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        //开启一个项目相同模型下name相同的sprint
        Integer sprintIndex = sprintInfo.getSprintIndex();
        List<SprintInfo> sprintInfos = this.sprintInfoRepository.findBySubjectIdAndModelIdAndNameAndSprintIndex(subjectId, modelId, name, sprintIndex);
        for (int i = 0; i < sprintInfos.size(); i++) {
            SprintInfo sprintInfo = sprintInfos.get(i);
            sprintInfo.setStatus("start");
            sprintInfo.setIterationCycle(request.getIterationCycle());
        }
        List<SprintInfo> sprintInfoList = this.sprintInfoRepository.save(sprintInfos);
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        return sprintInfo;
    }

    /**
     * 关闭迭代（同时关闭一个项目相同模型下name相同的sprint）(工作流不完善，未实现)
     *
     * @param request
     * @return
     */
    @Override
    public SprintInfo updateSprintCloseStatus(UpdateSprintCloseStatusRequest request) {
        String beforeName = request.getBeforeName();
        String afterName = request.getAfterName();
        String subjectId = request.getProjId();
        String modelId = request.getModelId();
        //查询要关闭的所有sprint
        List<SprintInfo> sprintInfos = this.sprintInfoRepository.findBySubjectIdAndModelIdAndNameAndIsDeleted(subjectId, modelId, beforeName, 0);
        //查询登录者移动之后的sprint
        SprintInfo spInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndModelIdAndNameAndIsDeleted(subjectId,request.getCreatorId(), modelId, afterName, 0);
        if (request.getStories().size() > 0) {
            String storyIds = MdvnStringUtil.join(request.getStories(), ",");
            String[] sprintStoryIds = storyIds.split(",");
            List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
            for (int i = 0; i < sprintInfos.size(); i++) {
                SprintInfo sInfo = sprintInfos.get(i);
                Integer beforUuId = sInfo.getUuId();
                String creatorId = sInfo.getCreatorId();
                //移动之前的sprint
                SprintInfo beforSprintInfo = this.sprintInfoRepository.findOne(beforUuId);
                //移动之后的sprint
                SprintInfo afterSprintInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndModelIdAndNameAndIsDeleted(subjectId, creatorId, modelId, afterName, 0);
                beforSprintInfo.setStatus("close");
                //移动之前的stories
                String beforeStoryIds = beforSprintInfo.getItemIds();
                String[] beforesprintStoryIds = beforeStoryIds.split(",");
                List<String> beforesprintStoryIdList = Arrays.asList(beforesprintStoryIds);
                List arrayList = new ArrayList(beforesprintStoryIdList);
                //移动之后的stories
                String afterStoryIds = afterSprintInfo.getItemIds();
                String[] aftersprintStoryIds = afterStoryIds.split(",");
                List<String> aftersprintStoryIdList = Arrays.asList(aftersprintStoryIds);
                List arrList = new ArrayList(aftersprintStoryIdList);
                for (int j = 0; j < sprintStoryIdList.size(); j++) {
                    for (int k = 0; k < arrayList.size(); k++) {
                        if (arrayList.get(k).equals(sprintStoryIdList.get(j))) {
                            arrayList.remove(sprintStoryIdList.get(j));
                            //往移动之后的sprint中添加
                            arrList.add(sprintStoryIdList.get(j));
                        }
                    }
                }
                beforeStoryIds = MdvnStringUtil.join(arrayList, ",");
                beforSprintInfo.setItemIds(beforeStoryIds);
                afterStoryIds = MdvnStringUtil.join(arrList, ",");
                afterSprintInfo.setItemIds(afterStoryIds);
                beforSprintInfo = this.sprintInfoRepository.saveAndFlush(beforSprintInfo);
                afterSprintInfo = this.sprintInfoRepository.saveAndFlush(afterSprintInfo);
            }
        } else {
            for (int i = 0; i < sprintInfos.size(); i++) {
                sprintInfos.get(i).setStatus("close");
            }
            sprintInfos = this.sprintInfoRepository.save(sprintInfos);
        }
        return spInfo;
    }

    @Override
    public List<SprintInfo> itSprint(Integer uuId) {
        List<SprintInfo> sprintInfos = new ArrayList<>();
        sprintInfo = this.sprintInfoRepository.findOne(uuId);
        Integer sprintIndex = sprintInfo.getSprintIndex();
        String subjectId = sprintInfo.getSubjectId();
        String creatorId = sprintInfo.getCreatorId();
        String modelId = sprintInfo.getModelId();
        SprintInfo sInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndSprintIndexAndModelId(subjectId, creatorId, sprintIndex + 1, modelId);
        SprintInfo spInfo = this.sprintInfoRepository.findBySubjectIdAndCreatorIdAndSprintIndexAndModelId(subjectId, creatorId, sprintIndex + 2, modelId);
        sprintInfos.add(sInfo);
        if (spInfo != null) {
            sprintInfos.add(spInfo);
        }
        return sprintInfos;
    }


}
