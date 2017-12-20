package com.mdvns.mdvn.task.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="config")
@Component
public class UrlConfig {

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

    private String updateReqmntInfoUrl;

    //staff sapi url
    private String rtrvStaffsByIdsUrl;
    private String rtrvStaffInfoUrl;

    //Model sapi url
    private String rtrvModelRoleByModelIdUrl;


    // task sapi
    private String saveTaskUrl;
    private String rtrvTaskListUrl;
    private String updateTaskUrl;
    private String addAttachForTaskUrl;
    private String rtrvTaskInfoUrl;

    // file sapi
    private String getAttachmentListByIdsUrl;

    private String deleteAttachForTaskUrl;

    private String rtrvStoryBaseInfoUrl;

    private String updateStoryBaseInfoUrl;
    private String rtrvTaskHistoryInfoUrl;
    private String retrieveUrl;

    private String rtrvSRoleMembersUrl;

    private String sendMessageUrl;

    public String getSendMessageUrl() {
        return sendMessageUrl;
    }

    public void setSendMessageUrl(String sendMessageUrl) {
        this.sendMessageUrl = sendMessageUrl;
    }

    public String getRtrvSRoleMembersUrl() {
        return rtrvSRoleMembersUrl;
    }

    public void setRtrvSRoleMembersUrl(String rtrvSRoleMembersUrl) {
        this.rtrvSRoleMembersUrl = rtrvSRoleMembersUrl;
    }

    public String getRetrieveUrl() {
        return retrieveUrl;
    }

    public void setRetrieveUrl(String retrieveUrl) {
        this.retrieveUrl = retrieveUrl;
    }

    public String getRtrvTaskHistoryInfoUrl() {
        return rtrvTaskHistoryInfoUrl;
    }

    public void setRtrvTaskHistoryInfoUrl(String rtrvTaskHistoryInfoUrl) {
        this.rtrvTaskHistoryInfoUrl = rtrvTaskHistoryInfoUrl;
    }

    public String getUpdateStoryBaseInfoUrl() {
        return updateStoryBaseInfoUrl;
    }

    public void setUpdateStoryBaseInfoUrl(String updateStoryBaseInfoUrl) {
        this.updateStoryBaseInfoUrl = updateStoryBaseInfoUrl;
    }

    public String getRtrvStoryBaseInfoUrl() {
        return rtrvStoryBaseInfoUrl;
    }

    public void setRtrvStoryBaseInfoUrl(String rtrvStoryBaseInfoUrl) {
        this.rtrvStoryBaseInfoUrl = rtrvStoryBaseInfoUrl;
    }

    public String getRtrvTaskInfoUrl() {
        return rtrvTaskInfoUrl;
    }

    public void setRtrvTaskInfoUrl(String rtrvTaskInfoUrl) {
        this.rtrvTaskInfoUrl = rtrvTaskInfoUrl;
    }

    public String getDeleteAttachForTaskUrl() {
        return deleteAttachForTaskUrl;
    }

    public void setDeleteAttachForTaskUrl(String deleteAttachForTaskUrl) {
        this.deleteAttachForTaskUrl = deleteAttachForTaskUrl;
    }

    public String getRtrvModelRoleByModelIdUrl() {
        return rtrvModelRoleByModelIdUrl;
    }

    public void setRtrvModelRoleByModelIdUrl(String rtrvModelRoleByModelIdUrl) {
        this.rtrvModelRoleByModelIdUrl = rtrvModelRoleByModelIdUrl;
    }

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

    public String getUpdateReqmntInfoUrl() {
        return updateReqmntInfoUrl;
    }

    public void setUpdateReqmntInfoUrl(String updateReqmntInfoUrl) {
        this.updateReqmntInfoUrl = updateReqmntInfoUrl;
    }

    public String getSaveTaskUrl() {
        return saveTaskUrl;
    }

    public void setSaveTaskUrl(String saveTaskUrl) {
        this.saveTaskUrl = saveTaskUrl;
    }

    public String getRtrvTaskListUrl() {
        return rtrvTaskListUrl;
    }

    public void setRtrvTaskListUrl(String rtrvTaskListUrl) {
        this.rtrvTaskListUrl = rtrvTaskListUrl;
    }

    public String getUpdateTaskUrl() {
        return updateTaskUrl;
    }

    public void setUpdateTaskUrl(String updateTaskUrl) {
        this.updateTaskUrl = updateTaskUrl;
    }

    public String getRtrvStaffInfoUrl() {
        return rtrvStaffInfoUrl;
    }

    public void setRtrvStaffInfoUrl(String rtrvStaffInfoUrl) {
        this.rtrvStaffInfoUrl = rtrvStaffInfoUrl;
    }

    public String getGetAttachmentListByIdsUrl() {
        return getAttachmentListByIdsUrl;
    }

    public void setGetAttachmentListByIdsUrl(String getAttachmentListByIdsUrl) {
        this.getAttachmentListByIdsUrl = getAttachmentListByIdsUrl;
    }

    public String getAddAttachForTaskUrl() {
        return addAttachForTaskUrl;
    }

    public void setAddAttachForTaskUrl(String addAttachForTaskUrl) {
        this.addAttachForTaskUrl = addAttachForTaskUrl;
    }
}
