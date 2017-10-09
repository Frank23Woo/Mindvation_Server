package com.mdvns.mdvn.model.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

/* 新建模型response
 */
@Component
public class CreateModelResponse {

    /*模型对象*/
    private Model model;

    private List<String> remarks;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }


}