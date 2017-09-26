package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table(name = "tag_proj_map", uniqueConstraints = {@UniqueConstraint(columnNames="uuId")})
public class ProjTags {

    @Id
    @GeneratedValue
    private Integer uuId;
    //标签Id
    private String tagId;
    //项目Id
    private String projId;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
