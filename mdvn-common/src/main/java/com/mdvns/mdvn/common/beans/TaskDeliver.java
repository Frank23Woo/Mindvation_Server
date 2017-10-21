package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

@Component
public class TaskDeliver {

    private Integer id;

    /*模版id*/
    private String modelId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskDeliver{" +
                "id=" + id +
                ", modelId='" + modelId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
