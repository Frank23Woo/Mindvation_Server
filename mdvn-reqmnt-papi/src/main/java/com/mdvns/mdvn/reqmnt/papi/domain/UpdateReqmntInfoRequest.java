package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateReqmntInfoRequest {
    private String staffId;
    private RequirementInfo requirementInfo;
    private List<Tag> tags;

 }
