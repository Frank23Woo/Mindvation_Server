package com.mdvns.mdvn.staff.sapi.repository;

import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Page<Staff> findAll(Pageable pageable);

    Staff findByStaffId(String staffId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM staff ", nativeQuery = true)
    Long getStaffCount();
}
