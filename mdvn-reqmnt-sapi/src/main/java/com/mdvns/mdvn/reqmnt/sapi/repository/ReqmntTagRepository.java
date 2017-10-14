package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReqmntTagRepository extends JpaRepository<ReqmntTag,Integer>{

    @Query(value="SELECT * FROM tag_proj_map WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ReqmntTag> findPTags(String projId);


    List<ReqmntTag> findAllByReqmntIdAndIsDeleted(String reqmntId, Integer isDeleted);


}
