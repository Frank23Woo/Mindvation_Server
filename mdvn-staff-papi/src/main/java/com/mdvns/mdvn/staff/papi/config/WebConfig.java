package com.mdvns.mdvn.staff.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {


    /*获取staff列表Url*/
    private String rtrvStaffListUrl;
    /*通过staffId的list获取staff对象的列表信息*/
    private String rtrvStaffListByStaffIdListUrl;
    /*通过staffId获取staff对象信息*/
    private String rtrvStaffInfoUrl;
    /*模糊查询*/
    private String rtrvStaffListByNameUrl;

<<<<<<< HEAD
    /*分配权限Url*/
    private String assignAuthUrl;

    /*获取员工权限信息Url*/
    private String rtrvAuthUrl;

    private String findByIdUrl;
=======
    private String createStaffUrl;

    public String getRtrvStaffDetailUrl() {
        return rtrvStaffDetailUrl;
    }

    public void setRtrvStaffDetailUrl(String rtrvStaffDetailUrl) {
        this.rtrvStaffDetailUrl = rtrvStaffDetailUrl;
    }

    private String rtrvStaffDetailUrl;
    private String rtrvTagsUrl;
    private String rtrvStaffTagListUrl;
    private String updateStaffDetailUrl;
    private String deleteStaffUrl;

    public String getDeleteStaffUrl() {
        return deleteStaffUrl;
    }

    public void setDeleteStaffUrl(String deleteStaffUrl) {
        this.deleteStaffUrl = deleteStaffUrl;
    }

    public String getUpdateStaffDetailUrl() {
        return updateStaffDetailUrl;
    }

    public void setUpdateStaffDetailUrl(String updateStaffDetailUrl) {
        this.updateStaffDetailUrl = updateStaffDetailUrl;
    }

    public String getRtrvStaffTagListUrl() {
        return rtrvStaffTagListUrl;
    }

    public void setRtrvStaffTagListUrl(String rtrvStaffTagListUrl) {
        this.rtrvStaffTagListUrl = rtrvStaffTagListUrl;
    }

    public String getRtrvTagsUrl() {
        return rtrvTagsUrl;
    }

    public void setRtrvTagsUrl(String rtrvTagsUrl) {
        this.rtrvTagsUrl = rtrvTagsUrl;
    }

    public String getCreateStaffUrl() {
        return createStaffUrl;
    }

    public void setCreateStaffUrl(String createStaffUrl) {
        this.createStaffUrl = createStaffUrl;
    }
>>>>>>> 02032cc46be60f31b668c8b41786eea6d8917a4a

    public String getRtrvStaffListUrl() {
        return rtrvStaffListUrl;
    }
    public void setRtrvStaffListUrl(String rtrvStaffListUrl) {
        this.rtrvStaffListUrl = rtrvStaffListUrl;
    }

    public String getRtrvStaffListByStaffIdListUrl() {
        return rtrvStaffListByStaffIdListUrl;
    }

    public void setRtrvStaffListByStaffIdListUrl(String rtrvStaffListByStaffIdListUrl) {
        this.rtrvStaffListByStaffIdListUrl = rtrvStaffListByStaffIdListUrl;
    }
    public String getRtrvStaffInfoUrl() {
        return rtrvStaffInfoUrl;
    }

    public void setRtrvStaffInfoUrl(String rtrvStaffInfoUrl) {
        this.rtrvStaffInfoUrl = rtrvStaffInfoUrl;
    }

    public String getRtrvStaffListByNameUrl() {
        return rtrvStaffListByNameUrl;
    }

    public void setRtrvStaffListByNameUrl(String rtrvStaffListByNameUrl) {
        this.rtrvStaffListByNameUrl = rtrvStaffListByNameUrl;
    }

    public String getAssignAuthUrl() {
        return assignAuthUrl;
    }

    public void setAssignAuthUrl(String assignAuthUrl) {
        this.assignAuthUrl = assignAuthUrl;
    }

    public String getRtrvAuthUrl() {
        return rtrvAuthUrl;
    }

    public void setRtrvAuthUrl(String rtrvAuthUrl) {
        this.rtrvAuthUrl = rtrvAuthUrl;
    }

    public String getFindByIdUrl() {
        return findByIdUrl;
    }

    public void setFindByIdUrl(String findByIdUrl) {
        this.findByIdUrl = findByIdUrl;
    }
}
