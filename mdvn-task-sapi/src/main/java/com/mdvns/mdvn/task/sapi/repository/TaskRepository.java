package com.mdvns.mdvn.task.sapi.repository;

import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer>{

    Page<Task> findAllByStoryIdAndIsDeleted(String storyId, Integer isDeleted, Pageable pageable);

    Task findFirstByTaskIdAndIsDeleted(String taskId, Integer isDeleted);

    Task findByTaskId(String taskId);

    List<Task> findAllByProjIdAndCreatorIdAndProgressAndIsDeleted(String projId,String creatorId,Integer progress, Integer isDeleted);

    List<Task> findAllByProjIdAndCreatorIdAndProgressIsNotInAndProgressIsNotInAndIsDeleted(String projId,String creatorId,Integer press,Integer progress, Integer isDeleted);
}
