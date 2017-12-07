package com.mdvns.mdvn.task.sapi.repository;


import com.mdvns.mdvn.task.sapi.domain.entity.TaskHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TaskHistoryRepository extends JpaRepository<TaskHistory,Integer>{
    Page<TaskHistory> findAllByTaskIdAndIsDeleted(String taskId, Integer isDeleted, Pageable pageable);

    List<TaskHistory> findAllByTaskIdAndIsDeleted(String taskId, Integer isDeleted);
}
