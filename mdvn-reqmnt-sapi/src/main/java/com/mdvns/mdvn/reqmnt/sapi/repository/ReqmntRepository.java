package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.RequirementInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReqmntRepository extends JpaRepository<RequirementInfo, Integer> {

    Page<RequirementInfo> findAllByProjIdAndIsDeleted(String projId, Integer isDeleted, Pageable pageable);

    List<RequirementInfo> findAllByProjIdAndIsDeletedOrderByUuIdAsc(String projId, Integer isDeleted);

    RequirementInfo findByReqmntIdAndIsDeleted(String reqmntId, Integer isDelete);

    @Query(value = "SELECT DISTINCT model_id from requirement_info WHERE proj_id = ?1 AND creator_id = ?2 AND is_deleted = 0", nativeQuery = true)
    List<String> findModelId(String projId,String creatorId);

    List<RequirementInfo> findByProjIdAndCreatorIdAndModelIdAndIsDeleted(String projId,String creatorId,String modelId, Integer isDeleted);

//    List<String>

    //获取project列表总条数
    @Query(value = "  SELECT DISTINCT COUNT(*) FROM (SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement WHERE req_id IN (SELECT req_id FROM staff_req_map WHERE staff_id= ?1)) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = ?1) UNION SELECT * FROM project WHERE creator_id = ?1 ) t ", nativeQuery = true)
    Long getProjBaseInfoCount(String staffId);

    @Query(value = "SELECT * from project WHERE uu_id=?1", nativeQuery = true)
    Object rtrvProjBaseInfo(Integer uu_id);

    @Query(value = "SELECT proj_id FROM project WHERE uu_id = ?1", nativeQuery = true)
    String getProjId(Integer uuId);

    @Query(value = "SELECT * from project WHERE proj_id=?1", nativeQuery = true)
    RequirementInfo rtrvProjBaseInfo(String projId);
}
