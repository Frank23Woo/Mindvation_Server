package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjAttchUrls;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjAttchUrlsRepository extends JpaRepository<ProjAttchUrls,Integer>{

    List<ProjAttchUrls> findByProjId(String projId);
    ProjAttchUrls findByProjIdAndAttachmentName(String projId,String AttachmentName);

    @Query(value="SELECT * FROM attachment_proj WHERE proj_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<ProjAttchUrls> findPAttchUrls(String projId);
}
