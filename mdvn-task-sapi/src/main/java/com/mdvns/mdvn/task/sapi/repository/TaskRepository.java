package com.mdvns.mdvn.task.sapi.repository;

import com.mdvns.mdvn.common.beans.Story;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer>{

    Page<Task> findAllByStoryIdAndIsDeleted(String storyId, Integer isDeleted, Pageable pageable);

    Task findFirstByTaskIdAndIsDeleted(String taskId, Integer isDeleted);

    Task findByTaskId(String taskId);

    List<Task> findAllByProjIdAndCreatorIdAndStatusAndIsDeleted(String projId,String creatorId,String status, Integer isDeleted);

    List<Task> findAllByProjIdAndCreatorIdAndStatusIsNotInAndStatusIsNotInAndIsDeleted(String projId,String creatorId,String state,String status, Integer isDeleted);

    //获取一个story下的task列表
    List<Task> findAllByStoryIdAndIsDeleted(String storyId, Integer isDeleted);
    //获取一个story下task用时的总和
    @Query(value = "SELECT SUM(used_time) FROM task WHERE story_id = ?1 AND is_deleted = 0", nativeQuery = true)
    Float rtrvTaskUsedTimeQty(String taskId);
    //获取story对象信息
    @Query(value = "SELECT story_point FROM story WHERE story_id = ?1", nativeQuery = true)
    Float rtrvStoryPoint(String storyId);

}
