package com.mdvns.mdvn.tag.papi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void listToString() {

        List<String> strs = new ArrayList<>();
        strs.add("S1");
        strs.add("S2");
        strs.add("S3");
        String str = strs.toString();
        System.out.println(str);

    }

}
