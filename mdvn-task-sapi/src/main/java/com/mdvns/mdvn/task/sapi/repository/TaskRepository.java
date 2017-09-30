package com.mdvns.mdvn.task.sapi.repository;

import com.mdvns.mdvn.task.sapi.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{

    Page<Task> findAllByStoryId(String storyId, Pageable pageable);

    Task findOneByStoryId(String storyId);

}
