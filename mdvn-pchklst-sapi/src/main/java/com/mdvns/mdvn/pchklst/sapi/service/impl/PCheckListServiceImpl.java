package com.mdvns.mdvn.pchklst.sapi.service.impl;

import com.mdvns.mdvn.pchklst.sapi.domian.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import com.mdvns.mdvn.pchklst.sapi.repository.PCheckListRepository;
import com.mdvns.mdvn.pchklst.sapi.service.PCheckListService;
import com.mdvns.mdvn.pchklst.sapi.utils.PCheckListStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
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
        pCheckList.setStatus(PCheckListStatusCode.NEW);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        pCheckList.setCreateTime(currentTime);
        pCheckList.setLastUpdatTime(currentTime);
        pCheckList.setIsDeleted(0);
        pCheckList = this.pCheckListRepository.saveAndFlush(pCheckList);
        LOG.info("finish executing createCheckList()方法.", this.CLASS);
        return pCheckList;
    }

    @Override
    public List<PCheckList> createMultiCheckList(List<PCheckList> list) {
        LOG.info("start executing createMultiCheckList()方法.", this.CLASS);
        List<PCheckList> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStatus(PCheckListStatusCode.NEW);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            list.get(i).setCreateTime(currentTime);
            list.get(i).setLastUpdatTime(currentTime);
            list.get(i).setIsDeleted(0);
            returnList.add(this.pCheckListRepository.saveAndFlush(list.get(i)));
        }
        LOG.info("finish executing createCheckList()方法.", this.CLASS);
        return returnList;
    }

    @Override
    public PCheckList updateCheckList(PCheckList pCheckList) {
        LOG.info("start executing updateCheckList()方法.", this.CLASS);
        PCheckList record = new PCheckList();
        /* find the record to update*/
        record = this.pCheckListRepository.findByPCheckListId(pCheckList.getpCheckListId());

        /* indicate if this record has updated*/
        Boolean flag = false;

        if(record == null){
            throw new NullPointerException("Record cannot be found");
        }
        if(!StringUtils.isEmpty(pCheckList.getDescription()) && (!pCheckList.getDescription().equals(record.getDescription()))){
            record.setDescription(pCheckList.getDescription());
            flag = true;
        }
        if(!StringUtils.isEmpty(pCheckList.getStartDate()) && (!pCheckList.getStartDate().equals(record.getStartDate())) ){
            record.setStartDate(pCheckList.getStartDate());
            flag = true;
        }
        if(!StringUtils.isEmpty(pCheckList.getEndDate()) && (!pCheckList.getEndDate().equals(record.getEndDate())) ){
            record.setEndDate(pCheckList.getEndDate());
            flag = true;
        }
        if(!StringUtils.isEmpty(pCheckList.getAssigneeId()) && (!pCheckList.getAssigneeId().equals(record.getAssigneeId())) ){
            record.setAssigneeId(pCheckList.getAssigneeId());
            flag = true;
        }
        if(!StringUtils.isEmpty(pCheckList.getStatus()) && (!pCheckList.getStatus().equals(record.getStatus())) ){
            record.setStatus(pCheckList.getStatus());
            flag = true;
        }

        if(flag){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            record.setLastUpdatTime(currentTime);
            if(!StringUtils.isEmpty(pCheckList.getStatus()) && (pCheckList.getStatus() == PCheckListStatusCode.CLOSED)){
                record.setCloseTime(currentTime);
            }
            record = this.pCheckListRepository.saveAndFlush(record);
        }


        LOG.info("finish executing updateCheckList()方法.", this.CLASS);
        return record;
    }

    @Override
    public List<PCheckList> updateMultiCheckList(List<PCheckList> list) {
        LOG.info("start executing updateMultiCheckList()方法.", this.CLASS);
        if(list == null || list.size()<=0) {
            throw new NullPointerException("Check List is empty");
        }

        List<PCheckList> returnList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            PCheckList record = new PCheckList();
            record = this.pCheckListRepository.findByPCheckListId(list.get(i).getpCheckListId());

            /* indicate if this record has updated*/
            Boolean flag = false;

            if(record == null){
                throw new NullPointerException("Record cannot be found");
            }

            /* Determine if the attributes of this record need to be updated*/
            if(!StringUtils.isEmpty(list.get(i).getDescription()) && (!list.get(i).getDescription().equals(record.getDescription()))){
                record.setDescription(list.get(i).getDescription());
                flag = true;
            }
            if(!StringUtils.isEmpty(list.get(i).getStartDate()) && (!list.get(i).getStartDate().equals(record.getStartDate())) ){
                record.setStartDate(list.get(i).getStartDate());
                flag = true;
            }
            if(!StringUtils.isEmpty(list.get(i).getEndDate()) && (!list.get(i).getEndDate().equals(record.getEndDate())) ){
                record.setEndDate(list.get(i).getEndDate());
                flag = true;
            }
            if(!StringUtils.isEmpty(list.get(i).getAssigneeId()) && (!list.get(i).getAssigneeId().equals(record.getAssigneeId())) ){
                record.setAssigneeId(list.get(i).getAssigneeId());
                flag = true;
            }
            if(!StringUtils.isEmpty(list.get(i).getStatus()) && (!list.get(i).getStatus().equals(record.getStatus())) ){
                record.setStatus(list.get(i).getStatus());
                flag = true;
            }

            if(flag){
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                record.setLastUpdatTime(currentTime);
                if(!StringUtils.isEmpty(list.get(i).getStatus()) && (list.get(i).getStatus() == PCheckListStatusCode.CLOSED)){
                    record.setCloseTime(currentTime);
                }
                record = this.pCheckListRepository.saveAndFlush(record);
                returnList.add(record);
            }

        }
        LOG.info("finish executing updateMultiCheckList()方法.", this.CLASS);
        return returnList;
    }

    @Override
    public Integer deleteCheckList(PCheckList pCheckList) {
        LOG.info("start executing deleteCheckList()方法.", this.CLASS);
        PCheckList record = new PCheckList();
        /* find the record to update*/
        record = this.pCheckListRepository.findByPCheckListId(pCheckList.getpCheckListId());
        if(record == null){
            throw new NullPointerException("Record cannot be found");
        }
        if(record.getIsDeleted()!=null && record.getIsDeleted()!= 1){
            record.setIsDeleted(1);
            record.setLastUpdatTime(new Timestamp(System.currentTimeMillis()));
            record = this.pCheckListRepository.saveAndFlush(record);
            return 1;
        }
        LOG.info("finish executing deleteCheckList()方法.", this.CLASS);
        return 0;
    }

    @Override
    public Integer deleteMultiCheckList(List<PCheckList> list) {
        LOG.info("start executing deleteMultiCheckList()方法.", this.CLASS);
        if(list == null || list.size()<=0) {
            throw new NullPointerException("Check List is empty");
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            PCheckList record = this.pCheckListRepository.findByPCheckListId(list.get(i).getpCheckListId());
            if(record == null){
                throw new NullPointerException("Record cannot be found");
            }
            if(record.getIsDeleted()!=null && record.getIsDeleted()!= 1){
                record.setIsDeleted(1);
                record.setLastUpdatTime(new Timestamp(System.currentTimeMillis()));
                record = this.pCheckListRepository.saveAndFlush(record);
                count+=1;
            }
        }
        LOG.info("finish executing deleteMultiCheckList()方法.", this.CLASS);
        return count;
    }

    @Override
    public List<PCheckList> rtrvPCheckList(RtrvPCheckListRequest rtrvPCheckListRequest) {
        LOG.info("start executing rtrvPCheckList()方法.", this.CLASS);
        if(rtrvPCheckListRequest == null){
            throw new NullPointerException("RtrvPCheckListRequest cannot be found");
        }

        if(rtrvPCheckListRequest.getPage()==null || rtrvPCheckListRequest.getPageSize()==null || StringUtils.isEmpty(rtrvPCheckListRequest.getProjectId())){
            LOG.info("one or more params (page, pageSize, projectId) is empty", this.CLASS);
            return null;
        }

        if(rtrvPCheckListRequest.getPage()==0||rtrvPCheckListRequest.getPageSize()==0){
            LOG.info("finish executing rtrvPCheckList()方法.", this.CLASS);
            return  this.pCheckListRepository.findAllByProjectIdAndIsDeletedAndStatusGreaterThanEqualOrderByStatusAsc(rtrvPCheckListRequest.getProjectId(),0,0);
        }else{

            Integer page = rtrvPCheckListRequest.getPage();
            Integer pageSize = rtrvPCheckListRequest.getPageSize();
            Page<PCheckList> pCheckListPage = null;
            PageRequest pageable = new PageRequest(page-1, pageSize);
            pCheckListPage = this.pCheckListRepository.findAllByProjectIdAndIsDeletedAndStatusGreaterThanEqualOrderByStatusAsc(rtrvPCheckListRequest.getProjectId(),0,0,pageable);
            LOG.info("finish executing rtrvPCheckList()方法.", this.CLASS);
            return pCheckListPage.getContent();
        }



    }




}
