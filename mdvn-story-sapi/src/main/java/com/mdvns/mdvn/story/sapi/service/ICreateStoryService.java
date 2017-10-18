package com.mdvns.mdvn.story.sapi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.story.sapi.domain.CreateStoryRequest;
import com.mdvns.mdvn.story.sapi.domain.RtrvStoryListRequest;
import com.mdvns.mdvn.story.sapi.domain.SaveSTasksRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface ICreateStoryService {

    //获取story整个列表
    RestResponse rtrvStoryInfoList(RtrvStoryListRequest request) throws SQLException;
    //创建story时保存story(基本信息)
    ResponseEntity<?> saveStory(CreateStoryRequest request);
//    Story saveStory(CreateStoryRequest createStoryRequest);
    //创建story时保存leaders信息
    List<StoryRoleMember> saveSRoleMembers(List<StoryRoleMember> members);
    //创建story时保存标签信息
    List<StoryTag> saveSTags(List<StoryTag> request);
    //创建story时保存story任务（checkLists）
    List<StoryTask> saveSTasks(SaveSTasksRequest request);

    Story getStory(Integer uuId);
}
