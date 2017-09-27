package com.mdvns.mdvn.pchklst.papi.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RtrvPCheckListRequestTest {

    public static void main(String[] args) {

        RtrvPCheckListRequest request = new RtrvPCheckListRequest();
        request.setPage(1);
        request.setProjectId("P11");
        request.setPageSize(10);
        List<String> remarks = new ArrayList<>();
        remarks.add("remark1");
        remarks.add("remark2");
        request.setRemarks(remarks);
        System.out.printf(new Gson().toJson(request));


    }
}
