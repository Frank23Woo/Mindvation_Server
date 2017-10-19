package com.mdvns.mdvn.task.sapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class TaskDeliver {

    @Id
    @GeneratedValue
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
