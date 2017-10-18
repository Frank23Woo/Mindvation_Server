package com.mdvns.mdvn.project.sapi.repository;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer>{

    //按staffId查询(先按状态排序，再按时间排序，最后按优先级排序)
    @Query(value="SELECT t.* ,(CASE WHEN STATUS=\"new\" THEN 1 WHEN STATUS=\"going\" THEN 2 WHEN STATUS=\"reopen\" THEN 3 WHEN STATUS=\"finished\" THEN 4 WHEN STATUS=\"pause\" THEN 5 ELSE 6 END) state FROM   (SELECT DISTINCT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement_info WHERE reqmnt_id IN (SELECT reqmnt_id FROM staff_reqmnt_map WHERE staff_id= ?1)) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = ?1) UNION SELECT * FROM project WHERE creator_id = ?1) t ORDER BY state ,create_time DESC , priority DESC LIMIT ?2,?3", nativeQuery = true)
    List<Project> rtrvProjInfoList(String staffId,Integer m,Integer n);
    //获取project列表总条数
    @Query(value="  SELECT DISTINCT COUNT(*) FROM (SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM requirement_info WHERE reqmnt_id IN (SELECT reqmnt_id FROM staff_reqmnt_map WHERE staff_id= ?1)) UNION SELECT * FROM project WHERE proj_id IN (SELECT proj_id FROM staff_proj_map WHERE staff_id = ?1) UNION SELECT * FROM project WHERE creator_id = ?1 ) t ", nativeQuery = true)
    Long getProjBaseInfoCount (String staffId);

    @Query(value="SELECT * from project WHERE uu_id=?1", nativeQuery = true)
    Object rtrvProjBaseInfo(Integer uu_id);

    @Query(value="SELECT proj_id FROM project WHERE uu_id = ?1", nativeQuery = true)
    String getProjId(Integer uuId);

    Project findByProjId(String projId);

    @Query(value="SELECT * from project WHERE proj_id=?1", nativeQuery = true)
    Project rtrvProjBaseInfo(String projId);
}
