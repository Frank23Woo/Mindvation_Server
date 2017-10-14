package com.mdvns.mdvn.common.utils;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpJsonRequestUtil {

    public static Object post(RestTemplate restTemplate, String url, Map<String, Object> form, Class clazz) throws Exception{
        Gson gson = new Gson();
        String jsonStr = gson.toJson(form);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
        return restTemplate.postForObject(url, entity, clazz);
    }


}
