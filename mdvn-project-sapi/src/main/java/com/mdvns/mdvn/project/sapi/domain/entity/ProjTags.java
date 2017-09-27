package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Timestamp;

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
    //有效标志
    @Column(columnDefinition = "varchar(5) default 'Y' ")
    private String yxbz;
    //更改时间
    @Column(columnDefinition = "timestamp default System.currentTimeMillis()", nullable = false)
    private Timestamp updateTime;

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

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
