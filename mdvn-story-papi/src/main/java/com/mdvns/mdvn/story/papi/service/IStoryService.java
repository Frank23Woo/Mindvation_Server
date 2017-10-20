package com.mdvns.mdvn.story.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.story.papi.domain.CreateStoryRequest;
import com.mdvns.mdvn.story.papi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.papi.domain.RtrvStoryListRequest;
import com.mdvns.mdvn.story.papi.domain.UpdateStoryDetailRequest;
import org.springframework.http.ResponseEntity;


public interface IStoryService {
    /**
     * 获取story整个列表
     * @param rtrvStoryListRequest
     * @return
     */
    RestResponse rtrvStoryInfoList(RtrvStoryListRequest rtrvStoryListRequest);

    /**
     * 创建story
     * @param createStoryRequest
     * @return
     */
    RestResponse createStory(CreateStoryRequest createStoryRequest);

    /**
     * 更改story
     * @param updateStoryDetailRequest
     * @return
     */
    RestResponse updateStory(UpdateStoryDetailRequest updateStoryDetailRequest);

    /**
     * 获取某个用户故事详细信息
     * @param rtrvStoryDetailRequest
     * @return
     */
    RestResponse rtrvStoryInfo(RtrvStoryDetailRequest rtrvStoryDetailRequest);
}
