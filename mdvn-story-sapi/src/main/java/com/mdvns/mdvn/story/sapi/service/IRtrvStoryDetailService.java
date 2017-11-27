package com.mdvns.mdvn.story.sapi.service;

import com.mdvns.mdvn.story.sapi.domain.*;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRtrvStoryDetailService {

    ResponseEntity<?> rtrvStoryBaseInfo(RtrvStoryDetailRequest request);

    List<StoryRoleMember> rtrvSRoleMembers(RtrvStoryDetailRequest request);

    List<StoryRoleMember> rtrvMembersByRoleId(RtrvMembersByRoleIdRequest request);

    List<StoryTag> rtrvStoryTags(RtrvStoryDetailRequest request);

    List<StoryAttchUrl> rtrvStoryAttUrls(RtrvStoryDetailRequest request);

    List<StoryTask> rtrvStoryTasks(RtrvStoryDetailRequest request);

    String getLabelIdByStoryId(String storyId);
    String getModelIdByStoryId(String storyId);
    String getReqmntIdByStoryId(String storyId);
    StoryNote rtrvStoryNote(String storyId);
}
