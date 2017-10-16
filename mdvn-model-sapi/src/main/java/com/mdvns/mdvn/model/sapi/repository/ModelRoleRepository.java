package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRoleRepository extends JpaRepository<ModelRole,Integer>{

    ModelRole findByName(String name);

    Page<ModelRole> findAll(Pageable pageable);

    List<ModelRole> findByModelId(String modelId);

    ModelRole findByRoleId(String roleId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM model_role", nativeQuery = true)
    Long getModelCount();
}
