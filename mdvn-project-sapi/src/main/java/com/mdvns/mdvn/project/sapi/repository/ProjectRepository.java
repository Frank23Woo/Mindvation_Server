package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer>{
    //按userId查询
    @Query(value="SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement WHERE req_id IN (SELECT req_id FROM staff_req_map WHERE staff_id= (SELECT staff_id FROM USER WHERE user_id = ?1))) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = (SELECT staff_id FROM USER WHERE user_id = ?1)) LIMIT ?2,?3", nativeQuery = true)
    List<Project> rtrvProjInfoList(Integer userId,Integer m,Integer n);

    //按staffId查询
    @Query(value="SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement WHERE req_id IN (SELECT req_id FROM staff_req_map WHERE staff_id= ?1)) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = ?1) ORDER BY priority LIMIT ?2,?3", nativeQuery = true)
    List<Project> rtrvProjInfoList(String staffId,Integer m,Integer n);
    //获取project列表总条数
    @Query(value="  SELECT COUNT(*) FROM (SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement WHERE req_id IN (SELECT req_id FROM staff_req_map WHERE staff_id= ?1)) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = ?1) ORDER BY priority ) t ", nativeQuery = true)
    Long getProjBaseInfoCount (String staffId);


    @Query(value="SELECT * from project WHERE uu_id=?1", nativeQuery = true)
    Object rtrvProjBaseInfo(Integer uu_id);

    @Query(value="SELECT proj_id FROM project WHERE uu_id = ?1", nativeQuery = true)
    String getProjId(Integer uuId);

    Project findByProjId(String projId);
}
