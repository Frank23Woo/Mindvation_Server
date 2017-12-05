package com.mdvns.mdvn.story.sapi.service.impl;


import com.mdvns.mdvn.story.sapi.domain.*;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.repository.*;
import com.mdvns.mdvn.story.sapi.service.IRtrvStoryDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RtrvStoryDetailServiceImpl implements IRtrvStoryDetailService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateStoryServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryRoleMemberRepository storyRoleMemberRepository;

    @Autowired
    private StoryTagRepository storyTagRepository;

    @Autowired
    private StoryTaskRepository storyTaskRepository;

    @Autowired
    private StoryAttchRepository storyAttchRepository;

    @Autowired
    private StoryNoteRepository storyNoteRepository;

    /**
     * 获得某个用户故事基础信息
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvStoryBaseInfo(RtrvStoryDetailRequest request) {
        LOG.info("start executing rtrvStoryBaseInfo()方法.", this.CLASS);
        Story story = this.storyRepository.rtrvStoryBaseInfo(request.getStoryId());
        //保存预期进度
        Timestamp startDate = story.getStartDate();
        Timestamp endDate = story.getEndDate();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
        int nowDays = (int) ((currentTime.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
        Float expectProgress = Float.valueOf(nowDays * 100 / days);
        DecimalFormat df = new DecimalFormat("#.00");
        expectProgress = Float.valueOf(df.format(expectProgress));
        //保存ragStatus
        if (expectProgress !=0){
            Float percent = story.getProgress() / expectProgress;
            if (percent >= 1) {
                story.setRagStatus("G");
            }
            if (percent < 1 && percent >= 0.5) {
                story.setRagStatus("A");
            }
            if (percent<0.5){
                story.setRagStatus("R");
            }
        }
        if(expectProgress<0){
            expectProgress = Float.valueOf(0);
        }
        if (expectProgress>100){
            expectProgress = Float.valueOf(100);
        }
        story.setExpectProgress(expectProgress);
        Story sto = this.storyRepository.saveAndFlush(story);
        LOG.info("finish executing rtrvStoryBaseInfo()方法.", this.CLASS);
        return ResponseEntity.ok().body(sto);
    }

    /**
     * 获得某个用户故事角色成员信息（映射表）
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryRoleMember> rtrvSRoleMembers(RtrvStoryDetailRequest request) {
        LOG.info("start executing rtrvSRoleMembers()方法.", this.CLASS);
        List<StoryRoleMember> storyRoleMembers = this.storyRoleMemberRepository.findSRoleMembers(request.getStoryId());
        LOG.info("finish executing rtrvSRoleMembers()方法.", this.CLASS);
        return storyRoleMembers;
    }

    /**
     * 通过角色Id获得该角色下的成员id信息（映射表）
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryRoleMember> rtrvMembersByRoleId(RtrvMembersByRoleIdRequest request) {
        LOG.info("start executing rtrvMembersByRoleId()方法.", this.CLASS);
        List<StoryRoleMember> storyRoleMembers = this.storyRoleMemberRepository.rtrvMembersByRoleId(request.getStoryId(), request.getRoleId());
        LOG.info("finish executing rtrvMembersByRoleId()方法.", this.CLASS);
        return storyRoleMembers;
    }


    /**
     * 获得某个用户故事标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTag> rtrvStoryTags(RtrvStoryDetailRequest request) {
        LOG.info("start executing rtrvStoryTags()方法.", this.CLASS);
        List<StoryTag> storyTags = this.storyTagRepository.findSTags(request.getStoryId());
        LOG.info("finish executing rtrvStoryTags()方法.", this.CLASS);
        return storyTags;
    }

    /**
     * 获得某个用户故事附件信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryAttchUrl> rtrvStoryAttUrls(RtrvStoryDetailRequest request) {
        LOG.info("start executing rtrvStoryAttUrls()方法.", this.CLASS);
        List<StoryAttchUrl> storyAttUrls = this.storyAttchRepository.findSAttchUrls(request.getStoryId());
        LOG.info("finish executing rtrvStoryAttUrls()方法.", this.CLASS);
        return storyAttUrls;
    }

    /**
     * 获得某个用户故事checkList信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTask> rtrvStoryTasks(RtrvStoryDetailRequest request) {
        LOG.info("start executing rtrvStoryTasks()方法.", this.CLASS);
        List<StoryTask> storyTasks = this.storyTaskRepository.findSTasks(request.getStoryId());
        LOG.info("finish executing rtrvStoryTasks()方法.", this.CLASS);
        return storyTasks;
    }

    /**
     * 查询上一层reqmnt下的labelId
     *
     * @param storyId
     * @return
     */
    @Override
    public String getLabelIdByStoryId(String storyId) {
        LOG.info("start executing getLabelIdByStoryId()方法.", this.CLASS);
        String labelId = this.storyRepository.getLabelIdByStoryId(storyId);
        LOG.info("finish executing getLabelIdByStoryId()方法.", this.CLASS);
        return labelId;
    }

    /**
     * 查询上一层reqmnt下的modelId
     *
     * @param storyId
     * @return
     */
    @Override
    public String getModelIdByStoryId(String storyId) {
        LOG.info("start executing getModelIdByStoryId()方法.", this.CLASS);
        String modelId = this.storyRepository.getModelIdByStoryId(storyId);
        LOG.info("finish executing getModelIdByStoryId()方法.", this.CLASS);
        return modelId;
    }

    /**
     * 查询上一层reqmnt下的reqmntId
     *
     * @param storyId
     * @return
     */
    @Override
    public String getReqmntIdByStoryId(String storyId) {
        LOG.info("start executing getReqmntIdByStoryId()方法.", this.CLASS);
        String reqmntId = this.storyRepository.getReqmntIdByStoryId(storyId);
        LOG.info("finish executing getReqmntIdByStoryId()方法.", this.CLASS);
        return reqmntId;
    }

    @Override
    public StoryNote rtrvStoryNote(String storyId) {
        LOG.info("start executing rtrvStoryNote()方法.", this.CLASS);
        StoryNote storyNote = this.storyNoteRepository.findByStoryId(storyId);
        LOG.info("finish executing rtrvStoryNote()方法.", this.CLASS);
        return storyNote;
    }

}
