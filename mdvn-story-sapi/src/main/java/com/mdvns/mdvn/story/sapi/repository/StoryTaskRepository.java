package com.mdvns.mdvn.story.sapi.repository;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryTaskRepository extends JpaRepository<StoryTask,Integer>{

    @Query(value="SELECT task_id FROM task_story WHERE uu_id = ?1", nativeQuery = true)
    String getSTaskId(Integer uuId);

    StoryTask findByTaskId(String taskId);

    @Query(value="SELECT * FROM task_story WHERE task_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<StoryTask> findSTasks(String taskId);
}
