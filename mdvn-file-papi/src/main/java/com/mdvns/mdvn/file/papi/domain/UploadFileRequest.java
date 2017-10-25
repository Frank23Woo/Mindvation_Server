package com.mdvns.mdvn.file.papi.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UploadFileRequest {

    /*文件上传者的Id*/
    private String creatorId;

    /*需要上传的文件*/
    private List<MultipartFile> files;

    private List<String> remarks;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadFileRequest)) return false;

        UploadFileRequest that = (UploadFileRequest) o;

        if (getCreatorId() != null ? !getCreatorId().equals(that.getCreatorId()) : that.getCreatorId() != null)
            return false;
        if (getFiles() != null ? !getFiles().equals(that.getFiles()) : that.getFiles() != null) return false;
        return getRemarks() != null ? getRemarks().equals(that.getRemarks()) : that.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getCreatorId() != null ? getCreatorId().hashCode() : 0;
        result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UploadFileRequest{" +
                "creatorId='" + creatorId + '\'' +
                ", files=" + files +
                '}';
    }
}
