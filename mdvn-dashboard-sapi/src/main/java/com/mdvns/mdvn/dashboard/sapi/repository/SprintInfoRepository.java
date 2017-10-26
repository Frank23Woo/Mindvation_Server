/*
package com.mdvns.mdvn.dashboard.sapi.repository;

import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintInfoRepository extends JpaRepository<SprintInfo, Integer> {


    SprintInfo findByName(String name);

    Page<SprintInfo> findAll(Pageable pageable);

    SprintInfo findByDashboardId(String dashboardId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM dashboard ", nativeQuery = true)
    Long getDashboardCount();

    List<SprintInfo> findByDashboardIdIn(List<String> dashboardIds);


//    Page<Dashboard> findAllOrderByQuoteCnt(Pageable pageable);
}
*/
