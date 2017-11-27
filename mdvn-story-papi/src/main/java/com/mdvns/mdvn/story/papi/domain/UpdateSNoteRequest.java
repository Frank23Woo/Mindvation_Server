package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class UpdateSNoteRequest {
    private String noteDesc;
    private String storyId;
    private String creatorId;

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
