package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UpdateRMembersRequest {

    private String reqmntId;
    private List<ReqmntMember> members;

    public String getReqmntId() {
        return reqmntId;
    }

    public List<ReqmntMember> getMembers() {
        return members;
    }

}
