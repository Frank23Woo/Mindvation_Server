package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class ModelAndSort {
    private Model model;
    private Integer sort;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
