package com.mdvns.mdvn.project.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="config")
@Component
public class ProjConfig {

    //获取project列表信息url
    private String rtrvProjInfoListUrl;
    //创建projecturl
    private String createProjectUrl;
    //创建project时保存learders信息
    private String saveProjLeadersUrl;
    //创建project时保存project基础信息
    private String saveProjectUrl;

    public String getSaveProjectUrl() {
        return saveProjectUrl;
    }

    public void setSaveProjectUrl(String saveProjectUrl) {
        this.saveProjectUrl = saveProjectUrl;
    }

    public String getRtrvProjInfoListUrl() {
        return rtrvProjInfoListUrl;
    }

    public void setRtrvProjInfoListUrl(String rtrvProjInfoListUrl) {
        this.rtrvProjInfoListUrl = rtrvProjInfoListUrl;
    }

    public String getSaveProjLeadersUrl() {
        return saveProjLeadersUrl;
    }

    public void setSaveProjLeadersUrl(String saveProjLeadersUrl) {
        this.saveProjLeadersUrl = saveProjLeadersUrl;
    }

    public String getCreateProjectUrl() {
        return createProjectUrl;
    }

    public void setCreateProjectUrl(String createProjectUrl) {
        this.createProjectUrl = createProjectUrl;
    }
}
