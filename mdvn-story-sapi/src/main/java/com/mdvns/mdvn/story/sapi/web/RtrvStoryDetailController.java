package com.mdvns.mdvn.story.sapi.web;

import com.mdvns.mdvn.story.sapi.domain.RtrvAverageReqmProgress;
import com.mdvns.mdvn.story.sapi.domain.RtrvMembersByRoleIdRequest;
import com.mdvns.mdvn.story.sapi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryAttchUrl;
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
    };


    /**
     * 通过角色Id获得该角色下的成员id信息（映射表）
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvMembersByRoleId")
    public List<StoryRoleMember> rtrvMembersByRoleId(@RequestBody RtrvMembersByRoleIdRequest request)throws SQLException {
        return rtrvStoryDetailService.rtrvMembersByRoleId(request);
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
     * 获得某个用户故事附件信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvStoryAttUrls")
    public List<StoryAttchUrl> rtrvStoryAttUrls(@RequestBody RtrvStoryDetailRequest request) throws SQLException {
        return rtrvStoryDetailService.rtrvStoryAttUrls(request);
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

    /**
     * 查询上一层reqmnt下的labelId
     * @param storyId
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/getLabelIdByStoryId")
    public String getLabelIdByStoryId(@RequestBody String storyId) throws SQLException {
        return rtrvStoryDetailService.getLabelIdByStoryId(storyId);
    }
    /**
     * 查询上一层reqmnt下的modelId
     * @param storyId
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/getModelIdByStoryId")
    public String getModelIdByStoryId(@RequestBody String storyId) throws SQLException {
        return rtrvStoryDetailService.getModelIdByStoryId(storyId);
    }
    /**
     * 查询上一层reqmnt下的reqmntId
     * @param storyId
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/getReqmntIdByStoryId")
    public String getReqmntIdByStoryId(@RequestBody String storyId) throws SQLException {
        return rtrvStoryDetailService.getReqmntIdByStoryId(storyId);
    }


}
