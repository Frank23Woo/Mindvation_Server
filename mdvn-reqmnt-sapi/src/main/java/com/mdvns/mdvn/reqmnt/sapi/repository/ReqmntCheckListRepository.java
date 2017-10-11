package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReqmntCheckListRepository extends JpaRepository<ReqmntCheckList,Integer>{

    @Query(value="SELECT check_list_id FROM checklist_proj WHERE uu_id = ?1", nativeQuery = true)
    String getPChecklistId(Integer uuId);


    @Query(value="SELECT * FROM checklist_proj WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ReqmntCheckList> findPChecklists(String projId);
}
