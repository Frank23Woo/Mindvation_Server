package com.mdvns.mdvn.task.sapi.repository;

import com.mdvns.mdvn.task.sapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer>{
}
