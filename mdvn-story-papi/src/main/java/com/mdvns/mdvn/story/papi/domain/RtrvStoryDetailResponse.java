package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import org.springframework.stereotype.Component;

@Component
public class RtrvStoryDetailResponse {
    private StoryDetail storyDetail;
    private StaffAuthInfo staffAuthInfo;

    public StaffAuthInfo getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(StaffAuthInfo staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }

    public StoryDetail getStoryDetail() {
        return storyDetail;
    }

    public void setStoryDetail(StoryDetail storyDetail) {
        this.storyDetail = storyDetail;
    }
}
