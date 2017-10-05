package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjLeadersRepository extends JpaRepository<ProjLeaders,Integer>{

    ProjLeaders findByProjIdAndStaffId(String projId,String staffId);

    @Modifying
    @Query(value="UPDATE staff_proj_map SET is_deleted= 0 WHERE proj_id= ?1 AND staff_id = ?2",nativeQuery = true )
    List updateLeders(String projId,String staffId);

    @Query(value="SELECT * FROM staff_proj_map WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ProjLeaders> findPLeders(String projId);



}
