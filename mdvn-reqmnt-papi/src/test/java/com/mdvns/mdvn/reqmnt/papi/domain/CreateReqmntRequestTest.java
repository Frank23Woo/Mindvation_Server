package com.mdvns.mdvn.reqmnt.papi.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreateReqmntRequestTest {
    public static void main(String[] args) {


//    /* requirement attachment url*/
//        private List<ReqmntAttchUrl> attchUrls;
//    /* remarks*/
//        private List<String> remarks;








        CreateReqmntRequest createReqmntRequest = new CreateReqmntRequest();
        createReqmntRequest.setProjId("P1");
        createReqmntRequest.setCreatorId("staff123");
        createReqmntRequest.setSummary("Summary123");
        createReqmntRequest.setDescription("Desc123");
        createReqmntRequest.setPriority(2);
        createReqmntRequest.setFunctionLabelId("funcLabel");
        createReqmntRequest.setStartDate(System.currentTimeMillis());
        createReqmntRequest.setEndDate(System.currentTimeMillis());
        createReqmntRequest.setLeaderId("staff333");
        ReqmntMember reqmntMember = new ReqmntMember();
        reqmntMember.setReqmntId("");
        reqmntMember.setRoleName("BA");
        reqmntMember.setStaffId("staff1");
        reqmntMember.setIsDeleted(0);
        List<ReqmntMember> members = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            members.add(reqmntMember);
        }
        createReqmntRequest.setMembers(members);

        List<ReqmntTag> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReqmntTag tag = new ReqmntTag();
            tag.setIsDeleted(0);
            tag.setTagId("" + i);
            tag.setUpdateTime(System.currentTimeMillis());
            tags.add(tag);
        }
        createReqmntRequest.setTags(tags);

        List<ReqmntCheckList> rCheckLists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReqmntCheckList checkList = new ReqmntCheckList();
            checkList.setIsDeleted(0);
            checkList.setAssigneeId("staff" + i);
            checkList.setAssignerId("staff" + i);
            checkList.setCheckListStatus("new");
            checkList.setCreateTime(System.currentTimeMillis());
            checkList.setDescription("dddfsdfadesfadsfadsf");
            checkList.setStartDate(System.currentTimeMillis());
            checkList.setEndDate(System.currentTimeMillis() + 1000000);
            rCheckLists.add(checkList);
        }
        createReqmntRequest.setrCheckLists(rCheckLists);

        List<ReqmntAttchUrl> attchUrls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReqmntAttchUrl url = new ReqmntAttchUrl();
            url.setAttachmentName("attach" + i);
            url.setUpdateTime(System.currentTimeMillis());
            attchUrls.add(url);
        }
        createReqmntRequest.setAttchUrls(attchUrls);


        System.out.println(new Gson().toJson(createReqmntRequest));
    }

}
