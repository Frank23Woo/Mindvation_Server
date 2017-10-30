package com.mdvns.mdvn.department.sapi.repository;

import com.mdvns.mdvn.department.sapi.domain.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findAllByDepartmentIdAndIsDeleted(String departmentId, Integer isDeleted);
}
