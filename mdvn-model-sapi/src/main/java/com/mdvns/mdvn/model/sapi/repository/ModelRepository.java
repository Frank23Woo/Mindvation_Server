package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    @Query(value="select * from model where model_type=?1", nativeQuery = true)
    List<Model> rtrvModelInfoListByModelType(String modelType);

    @Query(value="select * from model where creator_id=?1", nativeQuery = true)
    List<Model> rtrvModelInfoListByCreatorId(String creatorId);

    @Query(value="select * from model wheremodel_type=?1 AND creator_id=?2", nativeQuery = true)
    List<Model> rtrvModelInfoList(String modelType, String creatorId);
}
