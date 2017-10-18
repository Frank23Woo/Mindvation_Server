package com.mdvns.mdvn.story.sapi.service.impl;


import com.mdvns.mdvn.story.sapi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.repository.*;
import com.mdvns.mdvn.story.sapi.service.IRtrvStoryDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
//        if (null == story) {
//            LOG.error("用户故事不存在.", story);
//            throw new NullPointerException(story + "用户故事不存在.");
//        }
        LOG.info("finish executing rtrvStoryBaseInfo()方法.", this.CLASS);
        return ResponseEntity.ok().body(story);
    }

    /**
     * 获得某个用户故事成员信息
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

}
