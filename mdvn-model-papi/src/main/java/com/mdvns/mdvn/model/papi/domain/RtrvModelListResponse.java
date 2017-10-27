package com.mdvns.mdvn.model.papi.domain;

import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelListResponse {


    private List<ModelAndStaff> models;

    private Long totalNumber;

    private List<String> remarks;

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ModelAndStaff> getModels() {
        return models;
    }

    public void setModels(List<ModelAndStaff> models) {
        this.models = models;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
