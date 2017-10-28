package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReqmntAttchUrlsRepository extends JpaRepository<ReqmntAttchUrl,Integer>{

    List<ReqmntAttchUrl> findAllByReqmntIdAndIsDeleted(String reqmntId, Integer isDeleted);

}
