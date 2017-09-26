package com.mdvns.mdvn.pchklst.sapi.service.impl;

import com.mdvns.mdvn.pchklst.sapi.domian.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import com.mdvns.mdvn.pchklst.sapi.repository.PCheckListRepository;
import com.mdvns.mdvn.pchklst.sapi.service.PCheckListService;
import com.mdvns.mdvn.pchklst.sapi.utils.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

public class PCheckListServiceImpl implements PCheckListService{

    private static final Logger LOG = LoggerFactory.getLogger(PCheckListServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    PCheckListRepository pCheckListRepository;

    @Autowired
    PCheckList pCheckList;





    @Override
    public PCheckList createCheckList(PCheckList pCheckList) {
        LOG.info("start executing createCheckList()方法.", this.CLASS);
        pCheckList.setStatus(StatusCode.NEW);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        pCheckList.setCreateTime(currentTime);
        pCheckList.setLastUpdatTime(currentTime);
        pCheckList = this.pCheckListRepository.saveAndFlush(pCheckList);
        LOG.info("finish executing createCheckList()方法.", this.CLASS);
        return pCheckList;
    }

    @Override
    public PCheckList updateCheckList(PCheckList pCheckList) {
        PCheckList record = new PCheckList();
        /* find the record to update*/
        record = this.pCheckListRepository.findByPCheckListId();
        if(record == null){
            throw new NullPointerException("Record cannot be found");
        }


        if(!StringUtils.isEmpty(pCheckList.getDescription())){
            record.setDescription(pCheckList.getDescription());
        }
        if(!StringUtils.isEmpty(pCheckList.getStartDate())){
            record.setStartDate(pCheckList.getStartDate());
        }
        if(!StringUtils.isEmpty(pCheckList.getEndDate())){
            record.setEndDate(pCheckList.getEndDate());
        }
        if(!StringUtils.isEmpty(pCheckList.getAssigneeId())){
            record.setAssigneeId(pCheckList.getAssigneeId());
        }
        if(!StringUtils.isEmpty(pCheckList.getStatus())){
            record.setStatus(pCheckList.getStatus());
        }
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        record.setLastUpdatTime(currentTime);
        if(pCheckList.getStatus() == StatusCode.CLOSED){
            record.setCloseTime(currentTime);
        }

        record = this.pCheckListRepository.saveAndFlush(record);


        return record;
    }

    @Override
    public Integer deleteCheckList(PCheckList pCheckList) {
        return null;
    }

    @Override
    public List<PCheckList> rtrvPCheckList(RtrvPCheckListRequest rtrvPCheckListRequest) {
        
        return null;
    }
}
