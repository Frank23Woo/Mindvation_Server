package com.mdvns.mdvn.department.sapi.domain;

import com.mdvns.mdvn.department.sapi.domain.entity.Position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrUpdateDepartmentRequest {

    private String id;

    private String name;

    private List<Position> positions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
