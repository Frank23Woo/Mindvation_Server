package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjTagsRepository extends JpaRepository<ProjTags,Integer>{

    ProjTags findAllByProjIdAndTagId(String projId,String tagId);

    @Query(value="SELECT * FROM tag_proj_map WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ProjTags> findPTags(String projId);
}
