package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.staff.sapi.domain.*;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffAuthInfo;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffTag;
import com.mdvns.mdvn.staff.sapi.repository.StaffAuthInfoRepository;
import com.mdvns.mdvn.staff.sapi.repository.StaffRepository;
import com.mdvns.mdvn.staff.sapi.repository.StaffTagRepository;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private Staff staff;

    @Autowired
    private StaffAuthInfo staffAuthInfo;

    @Autowired
    private StaffAuthInfoRepository authInfoRepository;

    @Autowired
    private StaffTagRepository staffTagRepository;

    /**
     * 获取全部模块
     * @return
     */
    @Override
    public RetrieveStaffListResponse rtrvStaffList() {
        RetrieveStaffListResponse retrieveStaffListResponse =new RetrieveStaffListResponse();
        List<Staff> staffList = this.staffRepository.findAll();
        Long count = this.staffRepository.getStaffCount();
        retrieveStaffListResponse.setStaffs(staffList);
        retrieveStaffListResponse.setTotalNumber(count);
        return retrieveStaffListResponse;
    }

    /**
     * 通过staffIdList获取staff对象列表
     * @param request
     * @return
     */
    @Override
    public List<Staff> rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request) {
        List<Staff> list = new ArrayList<>();
        for (int i = 0; i < request.getStaffIdList().size(); i++) {
            System.out.println(request.getStaffIdList().get(i));
            Staff staffInfo = this.staffRepository.findByStaffId(request.getStaffIdList().get(i));
            if (null == staffInfo) {
                LOG.error("该id的员工在员工库中不存在.", staffInfo);
                throw new BusinessException(staffInfo + "该id的员工在员工库中不存在.");
            }else{
                list.add(staffInfo);
            }
        }
        return list;
    }

    /**
     * 通过staffId获取单条staff对象
     * @param staffId
     * @return
     */
    @Override
    public Staff rtrvStaffInfo(String staffId) {
        return this.staffRepository.findByStaffId(staffId);
    }


    @Override
    public List<StaffTag> rtrvStaffTagList(String staffId) {
        return staffTagRepository.findByStaffIdAndIsDeleted(staffId,0);
    }

    @Override
    public Boolean updateStaffDetail(UpdateStaffDetailRequest request) {
        Boolean flag = false;
        Staff staff = staffRepository.findByStaffId(request.getStaffInfo().getStaffId());
        Staff updateStaff = request.getStaffInfo();
//        RtrvStaffDetailResponse response = new RtrvStaffDetailResponse();
        if(staff!=null){
            if(!updateStaff.getDeptId().equals(staff.getDeptId())){
                staff.setDeptId(updateStaff.getDeptId());
            }
            if(!updateStaff.getPassword().equals(staff.getPassword())){
                staff.setPassword(updateStaff.getPassword());
            }
            if(!updateStaff.getGender().equals(staff.getGender())){
                staff.setGender(updateStaff.getGender());
            }
            if(!updateStaff.getPositionId().equals(staff.getPositionId())){
                staff.setPositionId(updateStaff.getPositionId());
            }
            if(!updateStaff.getPositionLvl().equals(staff.getPositionLvl())){
                staff.setPositionLvl(updateStaff.getPositionLvl());
            }
            if(!updateStaff.getEmailAddr().equals(staff.getEmailAddr())){
                staff.setEmailAddr(updateStaff.getEmailAddr());
            }
            if(!updateStaff.getPhoneNum().equals(staff.getPhoneNum())){
                staff.setPhoneNum(updateStaff.getPhoneNum());
            }
            if(!updateStaff.getStatus().equals(staff.getStatus())){
                staff.setStatus(updateStaff.getStatus());
            }

            staff = staffRepository.saveAndFlush(staff);

            flag = true;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<String> tagIds = request.getTagIds();
        List<StaffTag> staffTagList = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(),0);
        if(tagIds!=null && tagIds.size()==0){
            for (int i = 0; i < staffTagList.size(); i++) {
                staffTagList.get(i).setIsDeleted(1);
                staffTagList.get(i).setLastUpdateTime(now);
            }
            staffTagRepository.save(staffTagList);
        }else if(tagIds!=null && tagIds.size()>0){
            List<StaffTag> oldStaffTag = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(),0);
            List<StaffTag> rmvStaffTag = oldStaffTag;

            for (int i = 0; i < tagIds.size(); i++) {
                for (int j = 0; j < oldStaffTag.size(); j++) {
                    if(oldStaffTag.get(j).getTagId().equals(tagIds.get(i))){
                        rmvStaffTag.remove(j);
                    }
                }
            }

            if(!rmvStaffTag.isEmpty()){
                for (int i = 0; i < rmvStaffTag.size(); i++) {
                    rmvStaffTag.get(i).setIsDeleted(1);
                    rmvStaffTag.get(i).setLastUpdateTime(now);
                }
                staffTagRepository.save(oldStaffTag);
                flag = true;
            }

            oldStaffTag = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(),0);
            List<String> addList = tagIds;
            for (int i = 0; i < tagIds.size(); i++) {
                for (int j = 0; j < oldStaffTag.size(); j++) {
                    if(tagIds.get(i).equals(oldStaffTag.get(j).getTagId())){
                        addList.remove(i);
                    }
                }
            }

            if(!addList.isEmpty()){
                List<StaffTag> addStaffTag = new ArrayList<>();
                for (int i = 0; i < addList.size(); i++) {
                    StaffTag staffTag = new StaffTag();
                    staffTag.setStaffId(staff.getStaffId());
                    staffTag.setTagId(addList.get(i));
                    staffTag.setIsDeleted(0);
                    staffTag.setLastUpdateTime(now);
                    addStaffTag.add(staffTag);
                }
                staffTagRepository.save(addStaffTag);
                flag = true;
            }

            
        }


        return flag;
    }

    @Override
    public Boolean deleteStaff(String staffId) {
        Staff staff = staffRepository.findByStaffId(staffId);
        if(staff!=null && !staff.getStatus().equals("cancellation")){
            staff.setStatus("cancellation");
        }
        staffRepository.saveAndFlush(staff);
        return true;
    }

    /**
     * 简单模糊查询staff(不分页不存在，后续删除)
     * @param name
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByStaffName(String name) {
        String flag = "%"+name+"%";

        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        List<Staff> staffList = this.staffRepository.rtrvStaffListByStaffName(flag);
        Long count = this.staffRepository.getStaffCount();
        rtrvStaffListByNameResponse.setStaffs(staffList);
        rtrvStaffListByNameResponse.setTotalNumber(count);
        return ResponseEntity.ok(rtrvStaffListByNameResponse);
    }

    /**
     * 姓名分页查询（模糊）
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByStaffName(Integer page, Integer pageSize, String name, String sortBy) throws SQLException {
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        sortBy = (sortBy == null) ? "staffId" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize,Sort.Direction.DESC, sortBy);
        Page<Staff> staffPage = null;
        String flag = "%"+name+"%";
        List<Staff> staffList =  this.staffRepository.rtrvStaffListByStaffName(flag);
        staffPage = this.staffRepository.findByNameLike(flag,pageable);
//        staffPage = this.staffRepository.findAll(pageable);
        rtrvStaffListByNameResponse.setStaffs(staffPage.getContent());
        rtrvStaffListByNameResponse.setTotalNumber(staffPage.getTotalElements());
        return ResponseEntity.ok(rtrvStaffListByNameResponse);
    }

    /**
     * 根据指定account查询staff
     * @param account
     * @return
     */
    @Override
    public RestResponse<?> findByAccount(String account) {
        Staff staff = this.staffRepository.findByAccount(account);
        return RestResponseUtil.success(staff);
    }


    @Override
    public CreateStaffResponse createStaff(CreateStaffRequest request) {
        Staff staff = new Staff();
        staff.setPassword(request.getPassword());
        staff.setDeptId(request.getDeptId());
        staff.setName(request.getName());
        staff.setAccount(request.getAccount());
        staff.setCreatorId(request.getCreatorId());
        staff.setPositionId(request.getPositionId());
        staff.setPositionLvl(request.getPositionLvl());
        staff.setEmailAddr(request.getEmailAddr());
        staff.setPhoneNum(request.getPhoneNum());
        staff.setGender(request.getGender());
        staff.setStatus("active");
        staff = staffRepository.save(staff);
        staff.setStaffId("E"+ staff.getUuId());
        staff = staffRepository.save(staff);

        List<String> tagIds = request.getTagIds();
        List<StaffTag> staffTags = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < tagIds.size(); i++) {
            StaffTag staffTag = new StaffTag();
            staffTag.setStaffId(staff.getStaffId());
            staffTag.setTagId(tagIds.get(i));
            staffTag.setIsDeleted(0);
            staffTag.setLastUpdateTime(now);
            staffTags.add(staffTag);
        }
        staffTags = staffTagRepository.save(staffTags);

        CreateStaffResponse response = new CreateStaffResponse();
        response.setStaffId(staff.getStaffId());
        response.setName(staff.getName());
        response.setGender(staff.getGender());
//       private Postion positionDetail;
//       private Department deptDetail;
        response.setEmailAddr(staff.getEmailAddr());
        response.setPhoneNum(staff.getPhoneNum());
        response.setTagsCnt(staffTags.size());
        response.setStatus(staff.getStatus());

        return response;
    }
}
