package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.TaskDelivery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDeliveryRepository extends JpaRepository<TaskDelivery,Integer> {

    List<TaskDelivery> findByModelIdAndIsDeleted(String modelId,Integer isDeleted);
}
