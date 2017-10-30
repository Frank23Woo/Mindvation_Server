package com.mdvns.mdvn.department.sapi.repository;

import com.mdvns.mdvn.department.sapi.domain.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findFirstByIdAndIsDeleted(String departmentNo, Integer isDeleted);
    Page<Department> findAllByIsDeleted(Integer isDeleted, Pageable pageable);
}
