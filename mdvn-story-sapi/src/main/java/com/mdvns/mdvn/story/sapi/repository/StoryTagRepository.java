package com.mdvns.mdvn.story.sapi.repository;


import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoryTagRepository extends JpaRepository<StoryTag,Integer> {

    StoryTag findAllByStoryIdAndTagId(String storyId, String tagId);

    @Query(value="SELECT * FROM tag_story_map WHERE story_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<StoryTag> findSTags(String storyId);
}
