package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjChecklistsRepository extends JpaRepository<ProjChecklists,Integer>{

    @Query(value="SELECT check_list_id FROM checklist_proj WHERE uu_id = ?1", nativeQuery = true)
    String getPChecklistId(Integer uuId);

    ProjChecklists findByCheckListId(String checkListId);

    @Query(value="SELECT * FROM checklist_proj WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ProjChecklists> findPChecklists(String projId);
}
