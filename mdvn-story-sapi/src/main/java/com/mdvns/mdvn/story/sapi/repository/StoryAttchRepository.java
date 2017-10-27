package com.mdvns.mdvn.story.sapi.repository;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryAttchUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryAttchRepository extends JpaRepository<StoryAttchUrl,Integer>{

    StoryAttchUrl findAllByStoryIdAndAttachmentId(String storyId, Integer attachmentId);

    @Query(value="SELECT * FROM attachment_story WHERE story_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<StoryAttchUrl> findSAttchUrls(String storyId);
}
