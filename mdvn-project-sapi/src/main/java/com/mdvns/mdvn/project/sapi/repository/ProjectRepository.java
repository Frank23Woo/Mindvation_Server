package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, String>{
    @Query(value="SELECT * from project", nativeQuery = true)
    List<Project> rtrvProjBaseInfoList();
}
