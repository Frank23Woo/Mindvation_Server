package com.mdvns.mdvn.story.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.story.sapi.domain.CreateStoryRequest;
import com.mdvns.mdvn.story.sapi.domain.RtrvStoryListRequest;
import com.mdvns.mdvn.story.sapi.domain.RtrvStoryListResponse;
import com.mdvns.mdvn.story.sapi.domain.SaveSTasksRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.repository.*;
import com.mdvns.mdvn.story.sapi.service.ICreateStoryService;
import com.mdvns.mdvn.story.sapi.service.ICreateStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateStoryServiceImpl implements ICreateStoryService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateStoryServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private Story story;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryRoleMemberRepository storyRoleMemberRepository;

    @Autowired
    private StoryTagRepository storyTagRepository;


    @Autowired
    private StoryTaskRepository storyTaskRepository;

    /**
     * 获取story整个列表
     *
     * @return
     */
    public RestResponse rtrvStoryInfoList(RtrvStoryListRequest request) throws SQLException {
        RtrvStoryListResponse rtrvStoryListResponse = new RtrvStoryListResponse();
        //获取多张表数据联合查询然后分页
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        Integer m = page * pageSize;
        Integer n = pageSize;
        List<Story> pageList = this.storyRepository.rtrvStoryInfoList(request.getReqmntId(), m, n);
        Long totalElements = this.storyRepository.getStoryBaseInfoCount(request.getReqmntId());
        rtrvStoryListResponse.setStories(pageList);
        rtrvStoryListResponse.setTotalElements(totalElements);

        LOG.info("查询结果为：{}", rtrvStoryListResponse);
        return RestResponseUtil.success(rtrvStoryListResponse);
//        return ReturnFormat.retParam(HttpStatus.OK.toString(), "000", rtrvStoryListResponse);
    }

    /**
     * 创建story（基本信息）
     *
     * @param createStoryRequest
     * @return
     */
    @Override
    public ResponseEntity<?> saveStory(CreateStoryRequest createStoryRequest) {
        //先保存项目基本信息
        if (StringUtils.isEmpty(createStoryRequest) || StringUtils.isEmpty(createStoryRequest.getStoryInfo().getSummary())||
                StringUtils.isEmpty(createStoryRequest.getCreatorId()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getSubLabel()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getDescription()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getStartDate()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getEndDate())) {
            throw new NullPointerException("createStoryRequest不能为空 或创建者Id不能为空 或过程方法子模块 或用户故事概要不能为空 或用户故事描述不能为空 或者用户故事开始结束时间不能为空");
        }
        story.setSummary(createStoryRequest.getStoryInfo().getSummary());
        story.setDescription(createStoryRequest.getStoryInfo().getDescription());
        story.setCreatorId(createStoryRequest.getCreatorId());
        story.setSubLabel(createStoryRequest.getStoryInfo().getSubLabel());
        story.setIsDeleted(0);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        story.setCreateTime(currentTime);
        story.setStatus("new");
        story.setRagStatus("G");
        story.setProgress((double) 0);

        if (!StringUtils.isEmpty(createStoryRequest.getStoryInfo().getStartDate())) {
            story.setStartDate(createStoryRequest.getStoryInfo().getStartDate());
        }
        if (!StringUtils.isEmpty(createStoryRequest.getStoryInfo().getEndDate())) {
            story.setEndDate(createStoryRequest.getStoryInfo().getEndDate());
        }
        if (!StringUtils.isEmpty(createStoryRequest.getStoryInfo().getPriority())) {
            story.setPriority(createStoryRequest.getStoryInfo().getPriority());
        }

        story = storyRepository.saveAndFlush(story);
        story.setStoryId("S"+story.getUuId());
        Story st = storyRepository.saveAndFlush(story);
        ResponseEntity<?> responseEntity = new ResponseEntity<Object>(st, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 创建用户故事时保存members信息
     *
     * @param members
     * @return
     */
    @Override
    public List<StoryRoleMember> saveSRoleMembers(List<StoryRoleMember> members) {
        for (int i = 0; i < members.size(); i++) {
            members.get(i).setIsDeleted(0);
        }
        List<StoryRoleMember> pleaders = storyRoleMemberRepository.save(members);
        return pleaders;
    }

    /**
     * 创建story时保存标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTag> saveSTags(List<StoryTag> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<StoryTag> storyTags = storyTagRepository.save(request);
        return storyTags;
    }

    /**
     * 保存story多条任务（TASKS）
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTask> saveSTasks(SaveSTasksRequest request) {
        for (int i = 0; i < request.getSTasks().size(); i++) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            request.getSTasks().get(i).setCreateTime(currentTime);
            request.getSTasks().get(i).setIsDeleted(0);
            request.getSTasks().get(i).setStatus("new");
            request.getSTasks().get(i).setCreatorId(request.getStaffId());
        }
        List<StoryTask> storyTaskList = storyTaskRepository.save(request.getSTasks());
        for (int j = 0; j <storyTaskList.size(); j++) {
            storyTaskList.get(j).setTaskId("T"+storyTaskList.get(j).getUuId());
        }
        List<StoryTask> storyTasks = storyTaskRepository.save(storyTaskList);
        return storyTasks;
    }


    /**
     * 通过主键查询单条story数据
     *
     * @param uuId
     * @return
     */
    @Override
    public Story getStory(Integer uuId) {
        Object obj = storyRepository.rtrvStoryBaseInfo(uuId);
        Story story = new Story();
        Object[] objs = (Object[]) obj;
        if (obj.toString().length() > 0) {
            story.setStoryId((String) objs[1]);
        }
        return story;

    }

}
