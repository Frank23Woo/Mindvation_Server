package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReqmntMemberRepository extends JpaRepository<ReqmntMember,Integer> {

    List<ReqmntMember> findByReqmntIdAndIsDeleted(String reqmntId, Integer isDelete);

    @Query(value="SELECT * FROM staff_reqmnt_map WHERE reqmnt_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ReqmntMember> findPLeders(String reqmnt);
}
