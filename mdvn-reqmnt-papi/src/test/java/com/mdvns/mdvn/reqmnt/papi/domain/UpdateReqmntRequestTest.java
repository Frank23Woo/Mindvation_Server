package com.mdvns.mdvn.reqmnt.papi.domain;

import com.google.gson.Gson;
import com.mdvns.mdvn.common.beans.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateReqmntRequestTest {
    public static void main(String[] args) {
        UpdateReqmntInfoRequest request = new UpdateReqmntInfoRequest();
        request.setStaffId("m3");

        RequirementInfo reqInfo = new RequirementInfo();
        reqInfo.setReqmntId("R2");
        reqInfo.setDescription("desc_change");
        reqInfo.setModelId("M2");
        reqInfo.setPriority(1);
        request.setReqmntInfo(reqInfo);

        RoleMember roleMember = new RoleMember();
        roleMember.setMemberIds(Arrays.asList(new String[]{"m7"}));
        roleMember.setRoleId("R2");
        List<RoleMember> members = new ArrayList<>();

        members.add(roleMember);
        request.setMembers(members);

        Tag tag = new Tag();
        tag.setTagId("T2");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        request.setTags(tags);




        System.out.println(new Gson().toJson(request));


    }

}
