package com.mdvns.mdvn.pchklst.sapi.web;

import com.mdvns.mdvn.pchklst.sapi.domian.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import com.mdvns.mdvn.pchklst.sapi.service.PCheckListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PCheckListController {

    private Logger LOG = LoggerFactory.getLogger(PCheckListController.class);

    @Autowired
    private PCheckListService pCheckListService;

    @Autowired
    private PCheckList pCheckList;

    @Autowired
    private List<PCheckList> pCheckLists;

    @Autowired
    private RtrvPCheckListRequest rtrvPCheckListRequest;


    @PostMapping("/createPCheckList/pCheckList")
    public PCheckList createPCheckList(@RequestBody PCheckList request){
        LOG.info("start executing createPCheckList() method......");
        pCheckList = this.pCheckListService.createCheckList(request);
        LOG.info("finish executing createPCheckList() method......");
        return pCheckList;

    }

    @PostMapping("/createMultiPCheckList/pCheckLists")
    public List<PCheckList> createMultiPCheckList(@RequestBody List<PCheckList> request){
        LOG.info("start executing createPCheckList() method......");
        pCheckLists = this.pCheckListService.createMultiCheckList(request);
        LOG.info("finish executing createPCheckList() method......");
        return pCheckLists;

    }


    @PostMapping("/rtrvPCheckList")
    public List<PCheckList> RtrvPCheckList(@RequestBody RtrvPCheckListRequest request){
        LOG.info("executing RtrvPCheckList() method......");
        return this.pCheckListService.rtrvPCheckList(request);

    }

    @PostMapping("/updatePCheckList/pCheckList")
    public PCheckList updatePCheckList(@RequestBody PCheckList request){
        LOG.info("executing updatePCheckList() method......");
        return this.pCheckListService.updateCheckList(request);

    }

    @PostMapping("/updateMultiPCheckList/pCheckLists")
    public List<PCheckList> updateMultiPCheckList(@RequestBody List<PCheckList> request){
        LOG.info("executing updateMultiPCheckList() method......");
        return this.pCheckListService.updateMultiCheckList(request);

    }

    @PostMapping("/deletePCheckList/pCheckList")
    public Integer deletePCheckList(@RequestBody PCheckList request){
        LOG.info("executing deletePCheckList() method......");
        return this.pCheckListService.deleteCheckList(request);

    }

    @PostMapping("/deleteMultiPCheckList/pCheckLists")
    public Integer deleteMultiPCheckList(@RequestBody List<PCheckList> request){
        LOG.info("executing deleteMultiPCheckList() method......");
        return this.pCheckListService.deleteMultiCheckList(request);

    }





}
