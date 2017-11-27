package com.mdvns.mdvn.story.sapi.repository;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryNoteRepository extends JpaRepository<StoryNote,Integer> {
    StoryNote findByStoryId(String storyId);
}
