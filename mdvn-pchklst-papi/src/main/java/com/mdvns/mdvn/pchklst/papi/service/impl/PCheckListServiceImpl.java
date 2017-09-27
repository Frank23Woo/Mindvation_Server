package com.mdvns.mdvn.pchklst.papi.service.impl;

import com.mdvns.mdvn.pchklst.papi.config.WebConfig;
import com.mdvns.mdvn.pchklst.papi.domain.*;
import com.mdvns.mdvn.pchklst.papi.service.PCheckListService;
import com.mdvns.mdvn.pchklst.papi.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PCheckListServiceImpl implements PCheckListService{




    /* 注入RestTamplate*/
    @Autowired
    private RestTemplate restTemplate;

    /*注入response*/
    @Autowired
    private CreatePCheckListResponse createPCheckListResponse;

    @Autowired
    private RtrvPCheckListResponse rtrvPCheckListResponse;

    @Autowired
    private PCheckList pCheckList;

    @Autowired
    private WebConfig webConfig;








    @Override
    public CreatePCheckListResponse createPCheckList(CreatePCheckListRequest createPCheckListRequest) {
        LogUtil.serviceStartLog("createPCheckList");

        if (createPCheckListRequest == null){
            throw new NullPointerException("createPCheckListRequest 不能为空");
        }

        if (!StringUtils.isEmpty(createPCheckListRequest.getDescription())){
            pCheckList.setDescription(createPCheckListRequest.getDescription());
        }else{
            throw new NullPointerException("Check List Description 不能为空");
        }

        pCheckList.setAsignerId(createPCheckListRequest.getAsignerId());
        pCheckList.setAsigneeId(createPCheckListRequest.getAsigneeId());

        if(!StringUtils.isEmpty(createPCheckListRequest.getProjId())) {
            pCheckList.setProjId(createPCheckListRequest.getProjId());
        }else{
            throw new NullPointerException("Project ID 不能为空");
        }

        pCheckList.setStartDate(createPCheckListRequest.getStartDate());
        pCheckList.setEndDate(createPCheckListRequest.getEndDate());

        String url = webConfig.getCreatePCheckListUrl();

        try {
//            pCheckList = this.restTemplate.postForObject(url,pCheckList,PCheckList.class);
        } catch (RestClientException e) {
            LogUtil.errorLog(e);
            throw new RuntimeException("调用SAPI保存数据失败.");
        }

        createPCheckListResponse.setpCheckList(pCheckList);
        LogUtil.serviceEndLog("createPCheckList");

        return createPCheckListResponse;
    }

    @Override
    public RtrvPCheckListResponse rtrvPCheckList(RtrvPCheckListRequest rtrvPCheckListRequest) {
        LogUtil.serviceStartLog("rtrvPCheckList");

        if (rtrvPCheckListRequest == null){
            throw new NullPointerException("rtrvPCheckListRequest 不能为空");
        }

        if (!StringUtils.isEmpty(rtrvPCheckListRequest.getProjectId())){
            String url = webConfig.getRtrvPCheckListUrl();
            List<PCheckList> pCheckLists = new ArrayList<>();
//            pCheckLists = this.restTemplate.postForEntity(url,rtrvPCheckListRequest,List.class).getBody();
            rtrvPCheckListResponse.setpCheckList(pCheckLists);
        }else{
            throw new NullPointerException("Project ID 不能为空");
        }

        LogUtil.serviceEndLog("rtrvPCheckList");
        return rtrvPCheckListResponse;
    }
}
