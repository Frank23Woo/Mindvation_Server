package com.mdvns.mdvn.pchklst.papi.web;

import com.mdvns.mdvn.pchklst.papi.domain.CreatePCheckListRequest;
import com.mdvns.mdvn.pchklst.papi.domain.CreatePCheckListResponse;
import com.mdvns.mdvn.pchklst.papi.domain.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.papi.domain.RtrvPCheckListResponse;
import com.mdvns.mdvn.pchklst.papi.service.PCheckListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PCheckListController {

    private Logger LOG = LoggerFactory.getLogger(PCheckListController.class);

    @Autowired
    private PCheckListService pCheckListService;

    @Autowired
    private CreatePCheckListResponse createPCheckListResponse;

    @Autowired
    private RtrvPCheckListResponse rtrvPCheckListResponse;

    @PostMapping("/createPChecklist")
    public CreatePCheckListResponse createPChecklist(@RequestBody CreatePCheckListRequest createPCheckListRequest){
        LOG.info("start executing 'createPChecklist' method ");
        createPCheckListResponse = pCheckListService.createPCheckList(createPCheckListRequest);
        LOG.info("finished executing 'createPChecklist' method ");
        return createPCheckListResponse;
    }


    @PostMapping("/rtrvPCheckList")
    public RtrvPCheckListResponse rtrvPCheckList(@RequestBody RtrvPCheckListRequest rtrvPCheckListRequest){
        LOG.info("start executing 'rtrvPCheckList' method ");
        rtrvPCheckListResponse = pCheckListService.rtrvPCheckList(rtrvPCheckListRequest);
        LOG.info("finished executing 'rtrvPCheckList' method ");
        return rtrvPCheckListResponse;
    }





}
