package com.mdvns.mdvn.model.papi.domain;

import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class ModelAndStaff {

    private Staff creatorInfo;

    private Model model;

    public Staff getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Staff creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
