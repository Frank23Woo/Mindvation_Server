package com.mdvns.mdvn.model.sapi.domain;


import org.springframework.stereotype.Component;

@Component
public class RtrvSubFunctionLabelById {

    private String labelId;

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
