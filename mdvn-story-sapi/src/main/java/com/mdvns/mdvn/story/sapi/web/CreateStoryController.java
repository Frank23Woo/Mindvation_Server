package com.mdvns.mdvn.story.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.story.sapi.domain.CreateStoryRequest;
import com.mdvns.mdvn.story.sapi.domain.RtrvStoryListRequest;
import com.mdvns.mdvn.story.sapi.domain.SaveSTasksRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.service.ICreateStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value= {"/story", "/v1.0/story"})
public class CreateStoryController {
    private Logger LOG = LoggerFactory.getLogger(CreateStoryController.class);

    @Autowired
    private ICreateStoryService storyService;


    /**
     * 获取story整个列表
     * @return
     */
    @PostMapping(value="/rtrvStoryInfoList")
    public RestResponse rtrvStoryInfoList(@RequestBody RtrvStoryListRequest request) throws SQLException{
        return storyService.rtrvStoryInfoList(request);
    }


    /**
     *保存story（基本信息）
     */
    @PostMapping(value="/saveStory")
    public ResponseEntity<?> saveStory(@RequestBody CreateStoryRequest request){
        return storyService.saveStory(request);
    }

    /**
     * 创建story时保存负责人信息
     * @param members
     * @return
     */
    @PostMapping(value="/savePLeaders")
    public List<StoryRoleMember> saveSRoleMembers(@RequestBody List<StoryRoleMember> members){
        List<StoryRoleMember> storyLeaders = storyService.saveSRoleMembers(members);
        return storyLeaders;
    }

    /**
     * 创建story时保存标签信息
     * @param request
     * @return
     */
    @PostMapping(value="/savePTags")
    public List<StoryTag> saveSTags(@RequestBody List<StoryTag>  request){
        List<StoryTag> storyTags = storyService.saveSTags(request);
        return storyTags;
    }

    /**
     * 创建story时保存模型信息
     * @param request
     * @return
     */
    @PostMapping(value="/savePModels")
    public StoryModel saveSModel(@RequestBody StoryModel request){
        StoryModel storyModel = storyService.saveSModel(request);
        return storyModel;
    }
    /**
     * 创建story时保存story任务（checkLists）(多了一个保存创建者信息的动作)
     * @param request
     * @return
     */
    @PostMapping(value="/savePCheckLists")
    public List<StoryTask> saveSTasks(@RequestBody SaveSTasksRequest request){
        List<StoryTask> storyTasks = storyService.saveSTasks(request);
        return storyTasks;
    }


    /**
     * 通过主键查询单条story数据
     * @param request
     * @return
     */
    @PostMapping(value="/getStory")
    public Story getStory(@RequestBody Story request){
        Story story = storyService.getStory(request.getUuId());
        return story;
    }

}
