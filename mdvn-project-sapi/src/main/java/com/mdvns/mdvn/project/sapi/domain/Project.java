package com.mdvns.mdvn.project.sapi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Project {
    //表自增长生成的Id
    private Integer uuId;
    //项目编号
    private String projId;
    //项目名称
    private String name;
    //项目描述
    private String description;
    //项目优先级
    private String priority;
    //项目开始日期
    private Timestamp startDate;
    //项目结束时期
    private Timestamp endDate;
    //项目状态
    private Integer status;
    private Double efficiency;
    private Double progress;
    private Double cost;
    private Double crCost;
    private Integer storyQty;
    private Integer storyPointQty;
    private Integer crStoryQty;
    private Integer crStoryPointQty;
    private Integer taskQty;
    private Double crRate;
    private String remarks;
}
