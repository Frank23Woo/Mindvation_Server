package com.mdvns.mdvn.staff.sapi.repository;

import com.mdvns.mdvn.staff.sapi.domain.entity.StaffAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffAuthInfoRepository extends JpaRepository<StaffAuthInfo, Integer> {
    List<StaffAuthInfo> findByProjIdAndStaffIdAndHierarchyId(String projId, String staffId, String hierarchyId);
}
