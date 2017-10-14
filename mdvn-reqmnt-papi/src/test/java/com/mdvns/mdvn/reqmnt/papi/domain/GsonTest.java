package com.mdvns.mdvn.reqmnt.papi.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GsonTest {


    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("id11");
        list.add("id2");
        list.add("id3");
        list.add("id4");
        list.add("id5");
        map.put("id", list);
        map.put("id2", "2222");

        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);

        System.out.print(jsonStr);

    }


}
