package com.mdvns.mdvn.department.sapi.domain;

import com.mdvns.mdvn.department.sapi.domain.entity.Department;
import com.mdvns.mdvn.department.sapi.domain.entity.Position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentDetail extends Department{

    private List<Position> positions;


    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
