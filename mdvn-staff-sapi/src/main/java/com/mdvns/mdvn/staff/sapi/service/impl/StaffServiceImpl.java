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
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
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
import java.util.*;

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
     * 获取全部模块
     *
     * @return
     */
    @Override
    public RetrieveStaffListResponse rtrvStaffList(RetrieveStaffListRequest request) {
        RetrieveStaffListResponse retrieveStaffListResponse = new RetrieveStaffListResponse();
        if (request.getPage() == null || request.getPageSize() == null) {
//            List<Staff> list = this.staffRepository.findAll();
            List<Staff> list = this.staffRepository.findAllByAccountIsNot("admin");
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
            staffPage = this.staffRepository.findAllByAccountIsNot("admin", pageable);
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
            if (!updateStaff.getDeptId().equals(staff.getDeptId())) {
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
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        String sortBy = (request.getSortBy() == null) ? "tagId" : request.getSortBy();
        //第几页
        Integer page = request.getPage();
        //需获取的数据条数
        Integer pageSize = request.getPageSize();
        page = (page == null || page < Integer.valueOf(ConstantEnum.ONE.getValue())) ? Integer.valueOf(ConstantEnum.ONE.getValue()) : page;
        pageSize = (pageSize == null || pageSize < Integer.valueOf(ConstantEnum.ONE.getValue())) ? Integer.valueOf(ConstantEnum.TEN.getValue()) : pageSize;
        //构建分页参数
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);

        //分页对象
        Page<Staff> staffPage;
        //姓名
        String name = request.getName();
        //声明方法返回Staff集合变量
        List<Staff> result;
        if (StringUtils.isEmpty(name)) {
            List<String> tags = request.getTags();
            if (tags == null) {
                LOG.error("标签参数为空:{}", tags);
            }
            //1.查出拥有tags中任意一种标签的所有人
            List<StaffTag> staffList = this.staffTagRepository.findByTagIdIn(tags);
            //2.对staffId去重
            List<String> idList = new ArrayList<>(distinctStaffId(staffList));
            //3.根据每个人的标签匹配情况，计算标签匹配分值，并按分值返回staff列表
            result = rtrvStaffListByTags(staffList, tags, idList);

            rtrvStaffListByNameResponse.setStaffs(result);
            rtrvStaffListByNameResponse.setTotalNumber((long) result.size());
        } else {
            staffPage = this.staffRepository.findByNameLike(name, pageable);
            rtrvStaffListByNameResponse.setStaffs(staffPage.getContent());
            rtrvStaffListByNameResponse.setTotalNumber(staffPage.getTotalElements());
        }

        return ResponseEntity.ok(rtrvStaffListByNameResponse);
    }

    /**
     * 按标签推荐staff，对标签进行斐波那契数列赋值，最后按分值倒叙排列
     * @param stList tagId在tags中的所有StaffTag对象；当StaffTag具有多个tag时，会有相同的staffId的多个StaffTag存在
     * @param tags 按照标签查Staff的参数
     * @param idList tagId在tags中的所有StaffTag不重复的staffId集合
     *  计算过程：
     *   1. 遍历idList， 并以每一个不重复的staffId 实例化一个StaffTagScore对象
     *   2.
     * @return
     */
    private List<Staff> rtrvStaffListByTags(List<StaffTag> stList, List<String> tags, List<String> idList) {
        List<StaffTagScore> stsList = new ArrayList<StaffTagScore>();
        //1. 遍历idList， 并以每一个不重复的staffId 实例化一个StaffTagScore对象
        for (String id : idList) {
            StaffTagScore sts = new StaffTagScore();
            sts.setStaffId(id);
            sts.setTagScore(0D);
            //2.遍历stList
            for (int i = 0; i < stList.size(); i++) {
                StaffTag st = stList.get(i);

                if (id.equals(st.getStaffId())) {
                    sts = countTagScore(st,tags,sts);


                    LOG.info("第{}个staff, 标签score: {}", i, sts.getTagScore());
                }
            }
            stsList.add(sts);
        }

        LOG.info("排序前的staff：{}", stsList.toString());
        //List排序

        Comparator<StaffTagScore> comparator = (h1, h2) -> h1.getTagScore().compareTo(h2.getTagScore());
        stsList.sort(comparator.reversed());
        LOG.info("排序后的staff：{}", stsList.toString());
        //取tag分值从高到底前10个staff数据
        List<Staff> sList = new ArrayList<Staff>();
        List<String> ids = new ArrayList<>();

        Staff staff = null;
        int m = 0;
        m = (stsList.size() > Integer.valueOf(ConstantEnum.TEN.getValue()))?Integer.valueOf(ConstantEnum.TEN.getValue()):stsList.size();
        for (int i = 0; i < m; i++) {
            LOG.info("staffId：{}", stsList.get(i).getStaffId());
            staff = this.staffRepository.findByStaffId(stsList.get(i).getStaffId());
            sList.add(staff);
        }

        return sList;
    }

    /**
     * 根据斐波那契梳理计算每个tag对应的分值
     * @param st
     * @param tags
     * @param sts
     * @return
     */
    private StaffTagScore countTagScore(StaffTag st, List<String> tags, StaffTagScore sts) {

        for (int j = 0; j < tags.size(); j++) {
            LOG.info("j的值是：{}", j);
            LOG.info("第{}个staff, 标签score: {}",st.getStaffId(), sts.getTagScore());
            if (st.getTagId().equals(tags.get(j))) {
                List<String> tagList = (sts.getTagId() == null) ? new ArrayList<String>() : sts.getTagId();
                tagList.add(st.getTagId());
                Collections.sort(tagList);
                sts.setTagId(tagList);
                Double tagScore = sts.getTagScore();
                sts.setTagScore(tagScore + Math.pow(0.5, j + 1));
            }
        }
        return sts;
    }

/*
    private List<StaffTagScore> itStaffList(Set<String> staffIdList, List<StaffTag> staffList, List<String> tags) {
        List<String> idList = new ArrayList<String>(staffIdList);
        List<StaffTagScore> staffTagScores = gernerateTagScoreList(tags);
        for (int i = 0; i < idList.size(); i++) {
            String staffId = idList.get(i);

            for (int j = 0; j < staffList.size(); j++) {
                StaffTag st = staffList.get(j);
                if (staffId.equals(st.getStaffId())) {
                    for (int k = 0; k < staffTagScores.size(); k++) {
                        //
                        StaffTagScore staffTagScore = staffTagScores.get(k);
                        if (st.getTagId().equals(staffTagScores.get(k).getTagId())) {
                            staffTagScore.setStaffId(staffId);

                        }
                    }

                }
            }
        }
        return staffTagScores;

    }
*/

    /**
     * StaffId 去重
     *
     * @param staffList
     * @return
     */
    private Set<String> distinctStaffId(List<StaffTag> staffList) {
        Set<String> staffSet = new HashSet<String>();
        for (StaffTag st : staffList) {
            staffSet.add(st.getStaffId());
        }

        return staffSet;
    }


   /* public List<StaffTagScore> gernerateTagScoreList(List<String> tagIds) {
        StaffTagScore staffTagScore = null;
        List<StaffTagScore> list = new ArrayList<>();
        for (int i = 0; i < tagIds.size(); i++) {
            staffTagScore = new StaffTagScore();
            staffTagScore.setTagId(tagIds.get(i));
            staffTagScore.setTagScore(Math.pow(0.5, i + 1));
            list.add(staffTagScore);
        }
        return list;

    }*/
/*
    private List<StaffTagScore> getStaff(List<StaffTag> staffList, List<String> tags) {

        *//**
     * 1. + 0.5
     * 2.+1/4
     *//*
        List<StaffTagScore> stcList = new ArrayList<StaffTagScore>();
        for (int i = 0; i < staffList.size(); i++) {

            StaffTag sTag = staffList.get(i);
            StaffTagScore stMpper = new StaffTagScore(sTag.getTagId(), sTag.getStaffId(), 0D);

            for (int j = 0; j < tags.size(); j++) {
                LOG.info("j的值是：{}", j);
                LOG.info("第{}个staff, 标签score: {}",i, stMpper.getTagScore());
                if (sTag.getTagId().equals(tags.get(j))) {

                    stMpper.setTagScore(stMpper.getTagScore() + Math.pow(0.5,  j+1));
                }
                stcList.add(stMpper);
            }
            LOG.info("第{}个staff, 标签score: {}",i, stMpper.getTagScore());
        }
        LOG.info("排序前的staff：{}",stcList.toString());
        //List排序
        Collections.sort(stcList, new Comparator<StaffTagScore>() {

            public int compare(StaffTagScore h1, StaffTagScore h2) {
                return h1.getTagScore().compareTo(h2.getTagScore());
            }
        });
        LOG.info("排序后的staff：{}",stcList.toString());
        return stcList;
    }*/

    /**
     * 根据指定account查询staff
     *
     * @param account
     * @return
     */
    @Override
    public ResponseEntity<?> findByAccountAndPassword(String account, String password) {
        Staff staff = this.staffRepository.findByAccountAndPassword(account, password);
        return ResponseEntity.ok(staff);
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
//       private Postion positionDetail;
//       private Department deptDetail;
        response.setEmailAddr(staff.getEmailAddr());
        response.setPhoneNum(staff.getPhoneNum());
        response.setTagsCnt(staffTags.size());
        response.setStatus(staff.getStatus());

        return response;
    }
}
