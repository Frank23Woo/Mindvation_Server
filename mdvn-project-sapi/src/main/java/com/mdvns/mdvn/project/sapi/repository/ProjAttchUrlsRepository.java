package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjAttchUrls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjAttchUrlsRepository extends JpaRepository<ProjAttchUrls,Integer>{

    List<ProjAttchUrls> findByProjId(String projId);
}
