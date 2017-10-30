package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveModelListAndSortResponse {

//    private List<Model> models;

    private List<ModelAndSort> models;

    private Long totalNumber;

    private List<String> remarks;

    public List<ModelAndSort> getModels() {
        return models;
    }

    public void setModels(List<ModelAndSort> models) {
        this.models = models;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
