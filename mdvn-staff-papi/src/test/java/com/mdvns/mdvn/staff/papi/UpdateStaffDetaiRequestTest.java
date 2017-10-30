package com.mdvns.mdvn.staff.papi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mdvns.mdvn.staff.papi.domain.Staff;
import com.mdvns.mdvn.staff.papi.domain.UpdateStaffDetailRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateStaffDetaiRequestTest {

    public static void main(String[] args) {

        UpdateStaffDetailRequest updateStaffDetailRequest = new UpdateStaffDetailRequest();
        Staff staffInfo = new Staff();
        staffInfo.setStaffId("E10");
        staffInfo.setName("Test_Update");


        staffInfo.setAccount("Test_Update");

        staffInfo.setPassword("321321_Update");

        staffInfo.setGender("F");

        staffInfo.setDeptId("D1_");

        staffInfo.setPositionId("p1_");

        staffInfo.setPositionLvl("Junior_");

        staffInfo.setEmailAddr("test@test.com_");

        staffInfo.setPhoneNum("18877999544_");


        updateStaffDetailRequest.setStaffInfo(staffInfo);

        List<String> tagIds = new ArrayList<>();
        tagIds.add("T3");
        tagIds.add("T4");
        tagIds.add("T5");

        updateStaffDetailRequest.setTagIds(tagIds);


        System.out.println(new Gson().toJson(updateStaffDetailRequest));

        try {
            System.out.println(new ObjectMapper().writeValueAsString(updateStaffDetailRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
