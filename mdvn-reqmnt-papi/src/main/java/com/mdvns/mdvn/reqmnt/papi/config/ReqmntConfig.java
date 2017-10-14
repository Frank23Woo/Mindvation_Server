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
    private String rtrvReqmntInfoUrl;
    private String rtrvReqmntTagsUrl;
    private String rtrvReqmntMembersUrl;
    private String rtrvReqmntCheckListUrl;
    private String rtrvReqmntAttchsUrl;
    private String rtrvTagsByIdsUrl;
    private String rtrvFuncLabelUrl;

    //staff sapi url
    private String rtrvStaffsByIdsUrl;

    public String getRtrvReqmntListUrl() {
        return rtrvReqmntListUrl;
    }

    public void setRtrvReqmntListUrl(String rtrvReqmntListUrl) {
        this.rtrvReqmntListUrl = rtrvReqmntListUrl;
    }

    private String rtrvReqmntListUrl;

    public String getRtrvReqmntInfoUrl() {
        return rtrvReqmntInfoUrl;
    }

    public void setRtrvReqmntInfoUrl(String rtrvReqmntInfoUrl) {
        this.rtrvReqmntInfoUrl = rtrvReqmntInfoUrl;
    }

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

    public String getRtrvReqmntTagsUrl() {
        return rtrvReqmntTagsUrl;
    }

    public void setRtrvReqmntTagsUrl(String rtrvReqmntTagsUrl) {
        this.rtrvReqmntTagsUrl = rtrvReqmntTagsUrl;
    }

    public String getRtrvReqmntMembersUrl() {
        return rtrvReqmntMembersUrl;
    }

    public void setRtrvReqmntMembersUrl(String rtrvReqmntMembersUrl) {
        this.rtrvReqmntMembersUrl = rtrvReqmntMembersUrl;
    }

    public String getRtrvStaffsByIdsUrl() {
        return rtrvStaffsByIdsUrl;
    }

    public void setRtrvStaffsByIdsUrl(String rtrvStaffsByIdsUrl) {
        this.rtrvStaffsByIdsUrl = rtrvStaffsByIdsUrl;
    }

    public String getRtrvReqmntCheckListUrl() {
        return rtrvReqmntCheckListUrl;
    }

    public void setRtrvReqmntCheckListUrl(String rtrvReqmntCheckListUrl) {
        this.rtrvReqmntCheckListUrl = rtrvReqmntCheckListUrl;
    }

    public String getRtrvReqmntAttchsUrl() {
        return rtrvReqmntAttchsUrl;
    }

    public void setRtrvReqmntAttchsUrl(String rtrvReqmntAttchsUrl) {
        this.rtrvReqmntAttchsUrl = rtrvReqmntAttchsUrl;
    }

    public String getRtrvTagsByIdsUrl() {
        return rtrvTagsByIdsUrl;
    }

    public void setRtrvTagsByIdsUrl(String rtrvTagsByIdsUrl) {
        this.rtrvTagsByIdsUrl = rtrvTagsByIdsUrl;
    }

    public String getRtrvFuncLabelUrl() {
        return rtrvFuncLabelUrl;
    }

    public void setRtrvFuncLabelUrl(String rtrvFuncLabelUrl) {
        this.rtrvFuncLabelUrl = rtrvFuncLabelUrl;
    }
}
