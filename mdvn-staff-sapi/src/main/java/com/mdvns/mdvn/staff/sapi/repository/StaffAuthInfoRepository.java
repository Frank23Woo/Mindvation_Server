package com.mdvns.mdvn.staff.sapi.repository;

import com.mdvns.mdvn.staff.sapi.domain.entity.StaffAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffAuthInfoRepository extends JpaRepository<StaffAuthInfo, Integer> {
    StaffAuthInfo findByProjIdAndStaffIdAndHierarchyId(String projId, String staffId, String hierarchyId);
}
