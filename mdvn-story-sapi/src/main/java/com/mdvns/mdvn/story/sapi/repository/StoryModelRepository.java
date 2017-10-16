package com.mdvns.mdvn.story.sapi.repository;


import com.mdvns.mdvn.story.sapi.domain.entity.StoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryModelRepository extends JpaRepository<StoryModel,Integer>{
    StoryModel findByStoryIdAndModelId(String storyId, String modelId);

    @Query(value="SELECT * FROM model_story_map WHERE storyId =?1 AND is_deleted = 0;",nativeQuery = true )
    StoryModel findSModel(String storyId);
}
