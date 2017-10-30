package com.mdvns.mdvn.staff.papi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mdvns.mdvn.staff.papi.domain.CreateStaffRequest;

import java.util.Arrays;
import java.util.List;

public class CreateStaffRequestTest {

    public static void main(String[] args) {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest();

        createStaffRequest.setCreatorId("M1");

        createStaffRequest.setName("Test");


        createStaffRequest.setAccount("Test");

         createStaffRequest.setPassword("321321");

         createStaffRequest.setGender("M");

        createStaffRequest.setDeptId("D1");

        createStaffRequest.setPositionId("p1");

        createStaffRequest.setPositionLvl("Junior");

        createStaffRequest.setEmailAddr("test@test.com");

       createStaffRequest.setPhoneNum("18877999544");

       List<String> tagIds = Arrays.asList(new String[]{"T1","T2","T3"});
        createStaffRequest.setTagIds(tagIds);

        System.out.println(new Gson().toJson(createStaffRequest));

        try {
            System.out.println(new ObjectMapper().writeValueAsString(createStaffRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }



}
