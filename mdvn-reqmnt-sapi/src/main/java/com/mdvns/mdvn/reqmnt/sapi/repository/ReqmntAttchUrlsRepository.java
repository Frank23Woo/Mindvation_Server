package com.mdvns.mdvn.reqmnt.sapi.repository;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReqmntAttchUrlsRepository extends JpaRepository<ReqmntAttchUrl,Integer>{


    @Query(value="SELECT * FROM attachment_proj WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ReqmntAttchUrl> findPAttchUrls(String projId);
}
