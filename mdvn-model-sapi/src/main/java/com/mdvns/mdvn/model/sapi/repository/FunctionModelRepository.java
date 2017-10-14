package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.FunctionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionModelRepository extends JpaRepository<FunctionModel,Integer>{
    FunctionModel findByName(String name);

    Page<FunctionModel> findAll(Pageable pageable);

    List<FunctionModel> findByParentId(String modelId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM function_model ", nativeQuery = true)
    Long getModelCount();
}
