package com.mdvns.mdvn.dashboard.sapi.repository;

import com.mdvns.mdvn.dashboard.sapi.domain.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard,Integer> {

    List<Dashboard> findByProjIdAndIsDeleted(String projId , Integer isDeleted);

    Dashboard findByProjIdAndModelIdAndIsDeleted(String projId ,String modelId, Integer isDeleted);
}
