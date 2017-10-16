package com.mdvns.mdvn.story.sapi.service;

import com.mdvns.mdvn.story.sapi.domain.UpdateSMembersRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSModelRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSTagsRequest;
import com.mdvns.mdvn.story.sapi.domain.UpdateSTasksRequest;
import com.mdvns.mdvn.story.sapi.domain.entity.*;

import java.util.List;

public interface IUpdateStoryService {

    Story updateStoryBaseInfo(Story st);

    List<StoryRoleMember> updateStoryMembers(UpdateSMembersRequest list);

    List<StoryTag> updateStoryTags(UpdateSTagsRequest list);

    StoryModel updateStoryModel(UpdateSModelRequest request);

    List<StoryTask> updateStoryTasks(UpdateSTasksRequest list);
}
