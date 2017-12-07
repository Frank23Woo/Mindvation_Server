package com.mdvns.mdvn.task.sapi.repository;

import com.mdvns.mdvn.task.sapi.domain.entity.TaskDeliver;
import com.mdvns.mdvn.task.sapi.domain.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDeliverRepository extends JpaRepository<TaskDeliver, Integer> {

}
