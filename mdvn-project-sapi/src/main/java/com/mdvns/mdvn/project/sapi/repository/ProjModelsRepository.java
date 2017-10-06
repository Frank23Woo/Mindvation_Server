package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjModelsRepository extends JpaRepository<ProjModels,Integer>{
    ProjModels findByProjIdAndModelId(String projId,String modelId);

    @Query(value="SELECT * FROM model_proj_map WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ProjModels> findPModels(String projId);
}
