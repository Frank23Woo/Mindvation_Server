package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjChecklistsRepository extends JpaRepository<ProjChecklists,Integer>{

    @Query(value="SELECT checklist_id FROM checklist_proj WHERE uu_id = ?1", nativeQuery = true)
    String getPChecklistId(Integer uuId);
}
