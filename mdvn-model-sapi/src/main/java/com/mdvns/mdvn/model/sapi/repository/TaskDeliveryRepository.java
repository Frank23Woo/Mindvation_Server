package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.TaskDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDeliveryRepository extends JpaRepository<TaskDelivery,Integer> {
}
