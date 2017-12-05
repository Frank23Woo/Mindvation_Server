package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.enums.ConstantEnum;
import com.mdvns.mdvn.staff.sapi.domain.*;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffTag;
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
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private StaffTagRepository staffTagRepository;

    /**
     * 获取全部人员
     *
     * @return
     */
    @Override
    public RetrieveStaffListResponse rtrvStaffList(RetrieveStaffListRequest request) {
        RetrieveStaffListResponse retrieveStaffListResponse = new RetrieveStaffListResponse();
        if (request.getPage() == null || request.getPageSize() == null) {
//            List<Staff> list = this.staffRepository.findAll();
            List<Staff> list = this.staffRepository.findAllByAccountIsNot("Admin");
            retrieveStaffListResponse.setStaffs(list);
            retrieveStaffListResponse.setTotalNumber(Long.valueOf(list.size()));
            return retrieveStaffListResponse;
        } else {
            String sortBy = request.getSortBy();
            Integer page = request.getPage() - 1;
            Integer pageSize = request.getPageSize();
            sortBy = (sortBy == null) ? "uuId" : sortBy;
            PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, sortBy);
            Page<Staff> staffPage = null;
//            staffPage = this.staffRepository.findAll(pageable);
            staffPage = this.staffRepository.findAllByAccountIsNot("Admin", pageable);
//            Long count = this.staffRepository.getStaffCount();
            retrieveStaffListResponse.setStaffs(staffPage.getContent());
            retrieveStaffListResponse.setTotalNumber(staffPage.getTotalElements());
            return retrieveStaffListResponse;
        }
    }

    /**
     * 通过staffIdList获取staff对象列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Staff> rtrvStaffListById(RtrvStaffListByIdRequest request) {
        List<Staff> list = new ArrayList<>();
        for (int i = 0; i < request.getStaffIdList().size(); i++) {
            Staff staffInfo = this.staffRepository.findByStaffId(request.getStaffIdList().get(i));
            if (null == staffInfo) {
                LOG.error("该id的员工在员工库中不存在.", staffInfo);
                throw new BusinessException(staffInfo + "该id的员工在员工库中不存在.");
            } else {
                list.add(staffInfo);
            }
        }
        return list;
    }

    /**
     * 通过staffId获取单条staff对象
     *
     * @param staffId
     * @return
     */
    @Override
    public Staff rtrvStaffInfo(String staffId) {
        return this.staffRepository.findByStaffId(staffId);
    }


    @Override
    public List<StaffTag> rtrvStaffTagList(String staffId) {
        return staffTagRepository.findByStaffIdAndIsDeleted(staffId, 0);
    }

    @Override
    public Boolean updateStaffDetail(UpdateStaffDetailRequest request) {
        Boolean flag = false;
        Staff staff = staffRepository.findByStaffId(request.getStaffInfo().getStaffId());
        Staff updateStaff = request.getStaffInfo();
//        RtrvStaffDetailResponse response = new RtrvStaffDetailResponse();
        if (staff != null) {
            if (!StringUtils.isEmpty(updateStaff.getDeptId()) && !updateStaff.getDeptId().equals(staff.getDeptId())) {
                staff.setDeptId(updateStaff.getDeptId());
            }
            if (!StringUtils.isEmpty(updateStaff.getPassword()) && !updateStaff.getPassword().equals(staff.getPassword())) {
                staff.setPassword(updateStaff.getPassword());
            }
            if (!StringUtils.isEmpty(updateStaff.getGender()) && !updateStaff.getGender().equals(staff.getGender())) {
                staff.setGender(updateStaff.getGender());
            }
            if (!StringUtils.isEmpty(updateStaff.getPositionId()) && !updateStaff.getPositionId().equals(staff.getPositionId())) {
                staff.setPositionId(updateStaff.getPositionId());
            }
            if (!StringUtils.isEmpty(updateStaff.getPositionLvl()) && !updateStaff.getPositionLvl().equals(staff.getPositionLvl())) {
                staff.setPositionLvl(updateStaff.getPositionLvl());
            }
            if (!StringUtils.isEmpty(updateStaff.getEmailAddr()) && !updateStaff.getEmailAddr().equals(staff.getEmailAddr())) {
                staff.setEmailAddr(updateStaff.getEmailAddr());
            }
            if (!StringUtils.isEmpty(updateStaff.getPhoneNum()) && !updateStaff.getPhoneNum().equals(staff.getPhoneNum())) {
                staff.setPhoneNum(updateStaff.getPhoneNum());
            }
            if (!StringUtils.isEmpty(updateStaff.getStatus()) && !updateStaff.getStatus().equals(staff.getStatus())) {
                staff.setStatus(updateStaff.getStatus());
            }
            //更改头像
            if (!StringUtils.isEmpty(updateStaff.getAvatar()) && !updateStaff.getAvatar().equals(staff.getAvatar())) {
                staff.setAvatar(updateStaff.getAvatar());
            }

            staff = staffRepository.saveAndFlush(staff);

            flag = true;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<String> tagIds = request.getTagIds();
        List<StaffTag> staffTagList = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(), 0);
        if (tagIds != null && tagIds.size() == 0) {
            for (int i = 0; i < staffTagList.size(); i++) {
                staffTagList.get(i).setIsDeleted(1);
                staffTagList.get(i).setLastUpdateTime(now);
            }
            staffTagRepository.save(staffTagList);
        } else if (tagIds != null && tagIds.size() > 0) {
            List<StaffTag> oldStaffTag = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(), 0);
            List<StaffTag> rmvStaffTag = oldStaffTag;

            for (int i = 0; i < tagIds.size(); i++) {
                for (int j = 0; j < oldStaffTag.size(); j++) {
                    if (oldStaffTag.get(j).getTagId().equals(tagIds.get(i))) {
                        rmvStaffTag.remove(j);
                    }
                }
            }

            if (!rmvStaffTag.isEmpty()) {
                for (int i = 0; i < rmvStaffTag.size(); i++) {
                    rmvStaffTag.get(i).setIsDeleted(1);
                    rmvStaffTag.get(i).setLastUpdateTime(now);
                }
                staffTagRepository.save(oldStaffTag);
                flag = true;
            }

            oldStaffTag = staffTagRepository.findByStaffIdAndIsDeleted(request.getStaffInfo().getStaffId(), 0);
            List<String> addList = tagIds;
            for (int i = 0; i < tagIds.size(); i++) {
                for (int j = 0; j < oldStaffTag.size(); j++) {
                    if (tagIds.get(i).equals(oldStaffTag.get(j).getTagId())) {
                        addList.remove(i);
                    }
                }
            }

            if (!addList.isEmpty()) {
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
        if (staff != null && !staff.getStatus().equals("cancellation")) {
            staff.setStatus("cancellation");
        }
        staffRepository.saveAndFlush(staff);
        return true;
    }


    /**
     * 根据name模糊查询Staff，当name==null, 通过tag查询staff
     *
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByStaffName(RtrvStaffListByNameRequest request) throws SQLException {
        String sortBy = (request.getSortBy() == null) ? "staffId" : request.getSortBy();
        //姓名
        String name = request.getName();
        //如果name没有值
        if (StringUtils.isEmpty(name)) {
            List<String> tags = request.getTags();
            if (tags == null) {
                LOG.error("标签参数为空:{}", tags);
            }
            return null;
        }
        //第几页
        Integer page = request.getPage();
        //需获取的数据条数
        Integer pageSize = request.getPageSize();
        page = (page == null || page < Integer.valueOf(ConstantEnum.ONE.getValue())) ? Integer.valueOf(ConstantEnum.ONE.getValue()) : page;
        pageSize = (pageSize == null || pageSize < Integer.valueOf(ConstantEnum.ONE.getValue())) ? Integer.valueOf(ConstantEnum.TEN.getValue()) : pageSize;
        //构建分页参数
        PageRequest pageable = new PageRequest(page, pageSize);
        //分页对象
        Page<Staff> staffPage;
        LOG.info("查询name以：{}开头的员工", name);
        staffPage = this.staffRepository.findByNameStartingWith(name, pageable);

        return ResponseEntity.ok(staffPage);

    }


    /**
     * 根据指定account查询staff
     *
     * @param account
     * @return
     */
    @Override
    public ResponseEntity<?> findByAccountAndPassword(String account, String password) {
        Staff staff = this.staffRepository.findByAccountAndPasswordAndStatus(account, password,"active");
        return ResponseEntity.ok(staff);
    }

    /**
     * 查找拥有标签集中最多标签的StaffTag
     *
     * @param tags
     * @return List<StaffTag>
     */
    @Override
    public ResponseEntity<?> getStaffByTags(List<String> tags) {
        List<StaffTag> staffTags = this.staffTagRepository.findByTagIdIn(tags);
        //移除不存在的标签或者员工（其实删除员工或者标签时，它们的映射表的数据也应该删除）
        for (int i = 0; i < staffTags.size(); i++) {
            StaffTag staffTag = staffTags.get(i);
            String staffId = staffTag.getStaffId();
            String tagId = staffTag.getTagId();//标签还没判断
            Staff staff = this.staffRepository.findByStaffId(staffId);
            if (staff == null) {
                staffTags.remove(staffTags.get(i));
            }
        }
        LOG.info("拥有标签集中任意标签的Staff有：{}个", staffTags.size());
        return ResponseEntity.ok(staffTags);
    }

    /**
     * 查询name以指定字符串开头的所有Staff
     *
     * @param startingStr
     * @return
     */
    @Override
    public ResponseEntity<?> findByNameStartingWith(String startingStr) {

        List<Staff> staffPage = this.staffRepository.findByNameStartingWith(startingStr);
        LOG.info("查询name以指定字符串:{}开头的所有Staff...", startingStr);
        return ResponseEntity.ok(staffPage);
    }

    /**
     * 查询staffId为指定id的所有tagId集合
     *
     * @param staffId
     * @return
     */
    @Override
    public List<String> rtrvTagsByStaffId(String staffId) {

        return this.staffTagRepository.findTagIdByStaffId(staffId);
    }

    @Override
    public Boolean updateStaffPassword(UpdatePasswordRequest request) {
        Boolean flag = false;
        String staffId = request.getStaffId();
        String beforePW = request.getBeforePassword();
        String afterPW = request.getAfterPassword();
        Staff staff = this.staffRepository.findByStaffId(staffId);
        if (staff.getPassword().equals(beforePW)) {
            staff.setPassword(afterPW);
            this.staffRepository.saveAndFlush(staff);
            flag = true;
        }
        return flag;
    }


    @Override
    public CreateStaffResponse createStaff(CreateStaffRequest request) {
        Staff existStaff = staffRepository.findFirstByName(request.getName());
        if (existStaff != null) {
            throw new BusinessException(ExceptionEnum.USER_NAME_EXISTS);
        }
        existStaff = staffRepository.findFirstByAccount(request.getAccount());
        if (existStaff != null) {
            throw new BusinessException(ExceptionEnum.USER_ACCT_EXISTS);
        }

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
        /*添加头像*/
        if (!StringUtils.isEmpty(request.getAvatar())) {
            staff.setAvatar(request.getAvatar());
        }
        staff = staffRepository.save(staff);
        staff.setStaffId("E" + staff.getUuId());
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
        response.setEmailAddr(staff.getEmailAddr());
        response.setPhoneNum(staff.getPhoneNum());
        response.setTagsCnt(staffTags.size());
        response.setStatus(staff.getStatus());

        return response;
    }
}
