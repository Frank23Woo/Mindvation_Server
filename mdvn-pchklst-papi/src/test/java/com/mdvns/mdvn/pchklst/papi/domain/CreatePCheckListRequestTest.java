package com.mdvns.mdvn.pchklst.papi.domain;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreatePCheckListRequestTest {

    public static void main(String[] args) {

        CreatePCheckListRequest createPCheckListRequest = new CreatePCheckListRequest();
        createPCheckListRequest.setDescription("Desc");
        createPCheckListRequest.setAsigneeId("ABC9527");
        createPCheckListRequest.setAsignerId("BBC9527");
        createPCheckListRequest.setProjId("P66");
        List<String> remarks = new ArrayList<>();
        remarks.add("remark1");
        remarks.add("remark2");
        createPCheckListRequest.setStartDate(new Date().getTime());
        createPCheckListRequest.setEndDate(new Date().getTime());
        createPCheckListRequest.setRemarks(remarks);
        Gson gson = new Gson();
        System.out.printf(gson.toJson(createPCheckListRequest));




    }

}
