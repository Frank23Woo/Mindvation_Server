package com.mdvns.mdvn.story.sapi.web;


import com.mdvns.mdvn.story.sapi.domain.UpdateSMembersRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSModelRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSTagsRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSTasksRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.service.IUpdateStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= {"/updateStory", "/v1.0/story"})
public class UpdateStoryController {
    private Logger LOG = LoggerFactory.getLogger(CreateStoryController.class);
    @Autowired
    private IUpdateStoryService updateStoryService;

    /**
     * 更改用户故事基本信息
     * @param story
     * @return
     */
    @PostMapping(value="/updateStoryBaseInfo")
    public Story updateStoryBaseInfo(@RequestBody Story story){
        Story st = this.updateStoryService.updateStoryBaseInfo(story);
        return st;
    }

    /**
     * 更改用户故事成员信息
     * @param members
     * @return
     */
    @PostMapping(value="/updateStoryMembers")
    public List<StoryRoleMember> updateStoryMembers(@RequestBody UpdateSMembersRequest members){
        return this.updateStoryService.updateStoryMembers(members);
    }
    

    /**
     * 更改用户故事模型信息
     * @param model
     * @return
     */
    @PostMapping(value="/updateStoryModel")
    public StoryModel updateStoryModels(@RequestBody UpdateSModelRequest model){
        return this.updateStoryService.updateStoryModel(model);
    }

    /**
     * 更改用户故事标签信息
     * @param tags
     * @return
     */
    @PostMapping(value="/updateStoryTags")
    public List<StoryTag> updateStoryTags(@RequestBody UpdateSTagsRequest tags){
        return this.updateStoryService.updateStoryTags(tags);
    }
    /**
     * 更改用户故事checklist信息
     * @param request
     * @return
     */
    @PostMapping(value="/updateStoryTasks")
    public List<StoryTask> updateStoryTasks(@RequestBody UpdateSTasksRequest request){
        return this.updateStoryService.updateStoryTasks(request);
    }

}
