package com.mdvns.mdvn.story.sapi.service;

import com.mdvns.mdvn.story.sapi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTask;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRtrvStoryDetailService {

    ResponseEntity<?> rtrvStoryBaseInfo(RtrvStoryDetailRequest request);

    List<StoryRoleMember> rtrvSRoleMembers(RtrvStoryDetailRequest request);

    List<StoryTag> rtrvStoryTags(RtrvStoryDetailRequest request);

    List<StoryTask> rtrvStoryTasks(RtrvStoryDetailRequest request);
}
