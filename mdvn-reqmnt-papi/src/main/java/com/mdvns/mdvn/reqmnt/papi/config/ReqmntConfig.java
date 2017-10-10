package com.mdvns.mdvn.reqmnt.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="config")
@Component
public class ReqmntConfig {

    //获取project列表信息url
    private String saveReqmntUrl;

    private String saveRMembersUrl;
    private String saveRTagsUrl;
    private String saveRCheckListUrl;
    private String saveRAttchUrl;

    public String getSaveRAttchUrl() {
        return saveRAttchUrl;
    }

    public void setSaveRAttchUrl(String saveRAttchUrl) {
        this.saveRAttchUrl = saveRAttchUrl;
    }

    public String getSaveRCheckListUrl() {
        return saveRCheckListUrl;
    }

    public void setSaveRCheckListUrl(String saveRCheckListUrl) {
        this.saveRCheckListUrl = saveRCheckListUrl;
    }

    public String getSaveRTagsUrl() {
        return saveRTagsUrl;
    }

    public void setSaveRTagsUrl(String saveRTagsUrl) {
        this.saveRTagsUrl = saveRTagsUrl;
    }

    public String getSaveRMembersUrl() {
        return saveRMembersUrl;
    }

    public void setSaveRMembersUrl(String saveRMembersUrl) {
        this.saveRMembersUrl = saveRMembersUrl;
    }

    public String getSaveReqmntUrl() {
        return saveReqmntUrl;
    }

    public void setSaveReqmntUrl(String saveReqmntUrl) {
        this.saveReqmntUrl = saveReqmntUrl;
    }
}
