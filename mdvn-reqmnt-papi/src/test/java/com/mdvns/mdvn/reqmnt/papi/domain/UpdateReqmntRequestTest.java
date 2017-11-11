package com.mdvns.mdvn.reqmnt.papi.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mdvns.mdvn.common.beans.Tag;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateReqmntRequestTest {
    public static void main(String[] args) {
        UpdateReqmntInfoRequest request = new UpdateReqmntInfoRequest();
        request.setStaffId("m3");

        RequirementInfo reqInfo = new RequirementInfo();
        reqInfo.setProjId("P");
        reqInfo.setReqmntId("R2");
        reqInfo.setDescription("desc_change");
        reqInfo.setModelId("M2");
        reqInfo.setPriority(1);
        reqInfo.setSummary("summary");
        reqInfo.setCreatorId("staff");

        reqInfo.setFunctionLabelId("fid");
        reqInfo.setStartDate(System.currentTimeMillis());
        reqInfo.setEndDate(System.currentTimeMillis());
        reqInfo.setCreateTime(System.currentTimeMillis());
        reqInfo.setStatus("new");
        reqInfo.setRagStatus("A");
        reqInfo.setProgress(0.85);

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


        List<ReqmntCheckList> checkLists = new ArrayList<>();

        ReqmntCheckList reqmntCheckList = new ReqmntCheckList();
        reqmntCheckList.setAssignerId("staff");

        checkLists.add(reqmntCheckList);

        request.setCheckLists(checkLists);

        List<ReqmntAttchUrl> attchUrls = new ArrayList<>();

        ReqmntAttchUrl urls = new ReqmntAttchUrl();
        urls.setReqmntId("R3");

        attchUrls.add(urls);

        request.setAttchUrls(attchUrls);




        System.out.println(new Gson().toJson(request,request.getClass()));
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
