package com.mdvns.mdvn.story.sapi.service;

import com.mdvns.mdvn.story.sapi.domain.*;
import com.mdvns.mdvn.story.sapi.domain.entity.*;

import java.util.List;

public interface IUpdateStoryService {

    Story updateStoryBaseInfo(Story st);

    List<StoryRoleMember> updateStoryMembers(UpdateSMembersRequest list);

    List<StoryTag> updateStoryTags(UpdateSTagsRequest list);

    List<StoryTask> updateStoryTasks(UpdateSTasksRequest list);

    List<StoryAttchUrl> updateStoryAttchUrls(UpdateAttchUrlsRequest list);

    StoryNote updateStoryNote(UpdateSNoteRequest request);
}
