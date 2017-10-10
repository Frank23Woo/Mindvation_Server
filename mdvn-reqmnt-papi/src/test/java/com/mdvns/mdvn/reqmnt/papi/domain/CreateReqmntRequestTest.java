package com.mdvns.mdvn.reqmnt.papi.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreateReqmntRequestTest {
    public static void main(String[] args) {
        CreateReqmntRequest createReqmntRequest = new CreateReqmntRequest();
        createReqmntRequest.setCreatorId("staff123");
        createReqmntRequest.setSummary("Summary123");
        createReqmntRequest.setDescription("Desc123");
        createReqmntRequest.setPriority(2);
        createReqmntRequest.setFunctionLabelId("funcLabel");
        createReqmntRequest.setStartDate(Long.valueOf("1231321313"));
        createReqmntRequest.setEndDate(Long.valueOf("231313133"));
        createReqmntRequest.setLeaderId("staff333");
        ReqmntMember reqmntMember = new ReqmntMember();
        reqmntMember.setReqmntId("");
        reqmntMember.setRoleName("BA");
        reqmntMember.setStaffId("staff1");
        reqmntMember.setIsDeleted(0);
        reqmntMember.setLastUpdateTime(Long.valueOf("1231321313"));
        List<ReqmntMember> members = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            members.add(reqmntMember);
        }
        createReqmntRequest.setMembers(members);

        System.out.println(new Gson().toJson(createReqmntRequest));




    }

}
