package com.mdvns.mdvn.model.sapi.domain;

import com.mdvns.mdvn.model.sapi.domain.entity.IterationModel;
import com.mdvns.mdvn.model.sapi.domain.entity.SubFunctionLabel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateItModelResponse {
    private IterationModel iterationModel;
    //包含的 过程/方法模块 对象
    private List<SubFunctionLabel> functionLabels;

    public IterationModel getIterationModel() {
        return iterationModel;
    }

    public void setIterationModel(IterationModel iterationModel) {
        this.iterationModel = iterationModel;
    }

    public List<SubFunctionLabel> getFunctionLabels() {
        return functionLabels;
    }

    public void setFunctionLabels(List<SubFunctionLabel> functionLabels) {
        this.functionLabels = functionLabels;
    }
}
