package com.mdvns.mdvn.pchklst.sapi.domain.entity;

import com.google.gson.Gson;
import com.mdvns.mdvn.pchklst.sapi.domian.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PCheckListTest {

    public static void main(String[] args) {
//        createPCheckListJson();
//        createMultiPCheckListJson();
//        updatePCheckListJson(2);
//        updateMultiPCheckListJson(2);
//        deletePCheckListJson(2);
//        deleteMultiPCheckListJson(1);
        rtrvPCheckListJson(3);
    }


    private static void createPCheckListJson() {
        PCheckList pCheckList = new PCheckList();
        pCheckList.setProjectId("P15");
        pCheckList.setDescription("Desc");
        pCheckList.setStartDate(new Timestamp(System.currentTimeMillis()));
        pCheckList.setEndDate(new Timestamp(System.currentTimeMillis()));
        pCheckList.setAssigneeId("S34554");
        pCheckList.setAssignerId("S35453453");
        System.out.printf(new Gson().toJson(pCheckList));

    }

    private static void createMultiPCheckListJson() {
        List<PCheckList> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PCheckList pCheckList = new PCheckList();
            pCheckList.setProjectId("P" + (i + 1));
            pCheckList.setDescription("Desc" + (i + 1));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str_startDate = "2017-09-11";
            String str_endDate = "2017-09-22";
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = format.parse(str_startDate);
                endDate = format.parse(str_endDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }


            pCheckList.setStartDate(new Timestamp(startDate.getTime()));
            pCheckList.setEndDate(new Timestamp(endDate.getTime()));
            pCheckList.setAssigneeId("Staff" + (i + 1));
            pCheckList.setAssignerId("Leader" + (i + 1));
            list.add(pCheckList);
        }
        String jsonObject = new Gson().toJson(list, List.class);
        String jsonRequest = StringUtils.replace(jsonObject, "\"startDate\":\"Sep 11, 2017 12:00:00 AM\",\"endDate\":\"Sep 22, 2017 12:00:00 AM\"", "\"startDate\":159884513156,\"endDate\":2805649845356");
        System.out.printf(jsonRequest);

    }


    private static void updatePCheckListJson(int c) {

        if (c == 1) {
            PCheckList pCheckList = new PCheckList();
            pCheckList.setpCheckListId("PC2");
            pCheckList.setDescription("updated_desc");

            System.out.printf(new Gson().toJson(pCheckList));
        } else if (c == 2) {
            PCheckList pCheckList = new PCheckList();
            pCheckList.setpCheckListId("PC2");
            pCheckList.setDescription("updated_desc");
            pCheckList.setStatus(-1);
            System.out.printf(new Gson().toJson(pCheckList));
        }


    }

    private static void updateMultiPCheckListJson(int c) {

        List<PCheckList> list = new ArrayList<>();
        if (c == 1) {
            for (int i = 3; i <= 5; i++) {
                PCheckList pCheckList = new PCheckList();
                pCheckList.setpCheckListId("PC" + i);
                pCheckList.setDescription("updated_desc_pc" + i);
                list.add(pCheckList);
            }
            System.out.printf(new Gson().toJson(list));

        } else if (c == 2) {
            for (int i = 6; i <= 7; i++) {
                PCheckList pCheckList = new PCheckList();
                pCheckList.setpCheckListId("PC" + i);
                pCheckList.setStatus(-1);
                list.add(pCheckList);
            }
            System.out.printf(new Gson().toJson(list));
        }


    }

    private static void deletePCheckListJson(int c) {

        if (c == 1) {
            PCheckList pCheckList = new PCheckList();
            pCheckList.setpCheckListId("PC8");
            pCheckList.setDescription("updated_desc_pc8");
//            pCheckList.setIsDeleted(1);
            System.out.printf(new Gson().toJson(pCheckList));
        }else if (c == 2){
            PCheckList pCheckList = new PCheckList();
            pCheckList.setpCheckListId("PC9");
            System.out.printf(new Gson().toJson(pCheckList));
        }


    }

    private static void deleteMultiPCheckListJson(int c) {
        if(c==1){
            List<PCheckList> list = new ArrayList<>();
            for (int i = 10; i <= 12; i++) {
                PCheckList pCheckList = new PCheckList();
                pCheckList.setpCheckListId("PC"+i);
                list.add(pCheckList);
            }
            System.out.printf(new Gson().toJson(list));
        }else if(c==2){
            /* the second scenario*/
        }
    }

    private static void rtrvPCheckListJson(int c) {
        if(c==1){
            RtrvPCheckListRequest request = new RtrvPCheckListRequest();
            request.setProjectId("P1");
            request.setPage(1);
            System.out.printf(new Gson().toJson(request));

        }else if(c==2){
            /* the second scenario*/

            RtrvPCheckListRequest request = new RtrvPCheckListRequest();
            request.setProjectId("P1");
            request.setPage(0);
            System.out.printf(new Gson().toJson(request));
        }else if(c==3){
            /* the second scenario*/

            RtrvPCheckListRequest request = new RtrvPCheckListRequest();
            request.setProjectId("P1");
            request.setPage(0);
            request.setPageSize(0);
            System.out.printf(new Gson().toJson(request));
        }
    }





}
