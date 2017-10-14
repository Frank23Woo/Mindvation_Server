package com.mdvns.mdvn.story.sapi.web;

import com.mdvns.mdvn.story.sapi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryModel;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTask;
import com.mdvns.mdvn.story.sapi.service.IRtrvStoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value= {"/storyDetail", "/v1.0/story"})
public class RtrvStoryDetailController {

    @Autowired
    private IRtrvStoryDetailService rtrvStoryDetailService;

    /**
     * 获得某个用户故事基础信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvStoryBaseInfo")
    public ResponseEntity<?> rtrvStoryBaseInfo(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvStoryBaseInfo(request);
    }

    /**
     * 获得某个用户故事成员信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvSRoleMembers")
    public List<StoryRoleMember> rtrvSRoleMembers(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvSRoleMembers(request);
    }

    /**
     * 获得某个用户故事标签信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvStoryTags")
    public List<StoryTag> rtrvStoryTags(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvStoryTags(request);
    }

    /**
     * 获得某个用户故事模型信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvStoryModel")
    public StoryModel rtrvStoryModel(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvStoryModel(request);
    }

    /**
     * 获得某个用户故事checkList信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvStoryTasks")
    public List<StoryTask> rtrvStoryTasks(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvStoryTasks(request);
    }
    
}
