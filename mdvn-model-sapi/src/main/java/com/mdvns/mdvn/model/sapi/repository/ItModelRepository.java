package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.IterationModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItModelRepository extends JpaRepository<IterationModel,Integer>{

    List<IterationModel> findByModelId(String modelId);
}
