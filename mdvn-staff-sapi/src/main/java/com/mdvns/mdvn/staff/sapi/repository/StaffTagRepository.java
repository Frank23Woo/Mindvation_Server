package com.mdvns.mdvn.staff.sapi.repository;

import com.mdvns.mdvn.staff.sapi.domain.entity.StaffTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffTagRepository extends JpaRepository<StaffTag, Integer> {

    List<StaffTag> findByStaffIdAndIsDeleted (String staffId, Integer idDeleted);

}
