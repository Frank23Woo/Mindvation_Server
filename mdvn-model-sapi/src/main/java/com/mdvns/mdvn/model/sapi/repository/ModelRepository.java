package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    Model findByName(String name);

    Page<Model> findAll(Pageable pageable);

    Page<Model> findAllByModelType(String modelType,Pageable pageable);

    Page<Model> findAllByCreatorId(String creatorId,Pageable pageable);

    Page<Model> findAllByCreatorIdAndModelType(String creatorId,String modelType,Pageable pageable);
    Model findByModelId(String modelId);

    Model findAllByModelId(String modelId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM model ", nativeQuery = true)
    Long getModelCount();
}
