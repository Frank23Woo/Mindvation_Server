package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.enums.ConstantEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.staff.papi.config.WebConfig;
import com.mdvns.mdvn.staff.papi.domain.*;
import com.mdvns.mdvn.staff.papi.domain.Staff;
import com.mdvns.mdvn.staff.papi.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class StaffServiceImpl implements StaffService {

    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private Staff staff;

    @Autowired
    private RestResponse restResponse;

    /**
     * 调用SAPI获取Staff列表
     *
     * @param retrieveStaffListRequest
     * @return
     */
    public RestResponse rtrvStaffList(RetrieveStaffListRequest retrieveStaffListRequest) {
//        RetrieveStaffListAndTagCntResponse retrieveStaffListAndTagCntResponse = new RetrieveStaffListAndTagCntResponse();
        RetrieveStaffListResponse retrieveStaffListResponse = new RetrieveStaffListResponse();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvStaffListUrl();
        retrieveStaffListResponse = this.restTemplate.postForObject(url, retrieveStaffListRequest, RetrieveStaffListResponse.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        List<StaffAndTagCount> staffAndTagCounts = new ArrayList<>();
        List<Staff> staffs = new ArrayList<>();
        for (int i = 0; i < retrieveStaffListResponse.getStaffs().size(); i++) {
            Staff staff = retrieveStaffListResponse.getStaffs().get(i);
//            StaffAndTagCount staffAndTagCount = new StaffAndTagCount();
//            staffAndTagCount.setStaff(retrieveStaffListResponse.getStaffs().get(i));
            String staffId = retrieveStaffListResponse.getStaffs().get(i).getStaffId();
            String staffTagUrl = webConfig.getRtrvStaffTagListUrl();
            ParameterizedTypeReference<List<StaffTag>> parameterizedTypeReference = new ParameterizedTypeReference<List<StaffTag>>() {
            };
            List<StaffTag> staffTagList = FetchListUtil.fetch(this.restTemplate, staffTagUrl, staffId, parameterizedTypeReference);
            staff.setTagsCnt(staffTagList.size());
//            staffAndTagCount.setTagCnt(staffTagList.size());
//            staffAndTagCounts.add(staffAndTagCount);
            staffs.add(staff);
        }
        retrieveStaffListResponse.setStaffs(staffs);
//        retrieveStaffListAndTagCntResponse.setStaffs(staffAndTagCounts);
//        retrieveStaffListAndTagCntResponse.setTotalNumber(retrieveStaffListResponse.getTotalNumber());
        restResponse.setResponseBody(retrieveStaffListResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 通过staffId的list集合获取staff的对象列表
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvStaffListById(RtrvStaffListByIdRequest request) {
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvStaffListByStaffIdListUrl();
        List<Staff> list = this.restTemplate.postForObject(url, request, List.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(list);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 通过staffId获取staff对象信息
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvStaffInfo(RtrvStaffInfoRequest request) {
        String staffId = request.getStaffId();
        String url = webConfig.getRtrvStaffInfoUrl();
        staff = this.restTemplate.postForObject(url, staffId, Staff.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(staff);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }


    /**
     * 根据指定Id获取Staff信息
     *
     * @param id
     * @return
     */
    @Override
    public RestResponse<Staff> retrieve(String id) {
        //1.初始化URL
        String findByIdUrl = webConfig.getFindByIdUrl() + "/" + id;
        RestResponse<Staff> restResponse = null;
        try {
            restResponse = this.restTemplate.getForObject(findByIdUrl, RestResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }

        if ("000".equals(restResponse.getResponseCode())) {
            return restResponse;
        }
        throw new BusinessException(restResponse.getStatusCode());
    }

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {

        LOG.info("开始执行StaffPapi Service Login:{}", loginRequest.getAccount());
        //调用SAPI根据account查询用户
        Staff staff = getStaffByAccountAndPassword(loginRequest);
        if (staff == null) {
            throw new BusinessException(ExceptionEnum.ACCOUNT_OR_PASSWORD_INCORRECT);
        }
        return RestResponseUtil.successResponseEntity(staff);
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> logOut(logOutRequest request) {
        LOG.info("开始执行StaffPapi Service logOut:{}", request.getStaffId());
        Boolean flag = this.restTemplate.postForObject(webConfig.getLogOutUrl(),request.getStaffId(),Boolean.TYPE);
        if (flag == false) {
            throw new BusinessException(ExceptionEnum.LOGOUT_FAIL);
        }
        return RestResponseUtil.successResponseEntity(flag);
    }

    @Override
    public ResponseEntity<?> createStaff(CreateStaffRequest request) {
        String url = webConfig.getCreateStaffUrl();
        CreateStaffResponse response = this.restTemplate.postForObject(url, request, CreateStaffResponse.class);
        restResponse = RestResponseUtil.success(response);
        return ResponseEntity.ok(restResponse);
    }

    @Override
    public ResponseEntity<?> deleteStaff(String staffId) {
        String url = webConfig.getDeleteStaffUrl();
        Boolean flag = this.restTemplate.postForObject(url, staffId, Boolean.class);
        if (flag) {
            return rtrvStaffDetail(staffId);
        } else {
            return ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED, ExceptionEnum.DELETE_STAFF_FAIL + "", "Fail to delete staff"));
        }

    }

    @Override
    public ResponseEntity<?> updateStaffPassword(UpdatePasswordRequest request) {

        Boolean flag = this.restTemplate.postForObject(webConfig.getUpdateStaffPasswordUrl(), request, Boolean.class);
        if (flag) {
            return rtrvStaffDetail(request.getStaffId());
        } else {
            return ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED, ExceptionEnum.UPDATE_STAFF_PASSWORD_FAIL + "", "Fail to update staff PASSWORD"));
        }
    }




    @Override
    public ResponseEntity<?> rtrvStaffDetail(String staffId) {
        String url = webConfig.getRtrvStaffInfoUrl();
        RtrvStaffDetailResponse response = new RtrvStaffDetailResponse();
        Staff staff = this.restTemplate.postForObject(url, staffId, Staff.class);


        String staffTagUrl = webConfig.getRtrvStaffTagListUrl();
//        List<StaffTag> staffTagList = this.restTemplate.postForObject(staffTagUrl,staffId,List.class);
        ParameterizedTypeReference<List<StaffTag>> parameterizedTypeReference = new ParameterizedTypeReference<List<StaffTag>>() {
        };
        List<StaffTag> staffTagList = FetchListUtil.fetch(this.restTemplate, staffTagUrl, staffId, parameterizedTypeReference);

        List<String> tagIds = new ArrayList<>();
        for (int i = 0; i < staffTagList.size(); i++) {
            tagIds.add(staffTagList.get(i).getTagId());
        }
        String tagUrl = webConfig.getRtrvTagsUrl();

        RtrvTagsRequest rtrvTagsRequest = new RtrvTagsRequest();
        rtrvTagsRequest.setTagIds(tagIds);

//        List<Tag> tagList = this.restTemplate.postForObject(tagUrl,params, List.class);
        ParameterizedTypeReference<List<Tag>> tagRefer = new ParameterizedTypeReference<List<Tag>>() {
        };
        List<Tag> tagList = FetchListUtil.fetch(this.restTemplate, tagUrl, rtrvTagsRequest, tagRefer);
        staff.setTagsCnt(tagList.size());
        response.setStaffInfo(staff);
        response.setTags(tagList);

        //返回部门信息和职位信息
        QueryDepartmentRequest queryDepartmentRequest = new QueryDepartmentRequest();
        queryDepartmentRequest.setDepartmentId(staff.getDeptId());
        DepartmentDetail departmentDetail = restTemplate.postForObject(webConfig.getRtrvDepartmentUrl(), queryDepartmentRequest, DepartmentDetail.class);
        response.setDepartmentDetail(departmentDetail);
        Integer positionId = staff.getPositionId();
        List<Position> positions = departmentDetail.getPositions();
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getId().equals(positionId)) {
                response.setPosition(positions.get(i));
            }
        }
        return ResponseEntity.ok(RestResponseUtil.success(response));
    }

    @Override
    public ResponseEntity<?> updateStaffDetail(UpdateStaffDetailRequest request) {
        Boolean flag = this.restTemplate.postForObject(webConfig.getUpdateStaffDetailUrl(), request, Boolean.class);
        if (flag) {
            return rtrvStaffDetail(request.getStaffInfo().getStaffId());
        } else {
            return ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED, ExceptionEnum.UPDATE_STAFF_FAIL + "", "Fail to update staff info"));
        }
    }


    /**
     * 调用SAPI根据account查询用户
     *
     * @param loginRequest
     * @return
     */
    private Staff getStaffByAccountAndPassword(LoginRequest loginRequest) {
        String findByAccounAndPasswordtUrl = webConfig.getFindByAccounAndPasswordtUrl();
        ResponseEntity<Staff> responseEntity = null;
        ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<RestResponse<Staff>>() {
        };
        try {
//            responseEntity = restTemplate.getForEntity(findByAccounAndPasswordtUrl, RestResponse.class, loginRequest);
            responseEntity = restTemplate.postForEntity(findByAccounAndPasswordtUrl, loginRequest, Staff.class);
//            responseEntity = restTemplate.exchange(findByAccountUrl, HttpMethod.GET, new HttpEntity<LoginRequest>(loginRequest), parameterizedTypeReference);
        } catch (Exception ex) {
            LOG.error("调用SAPI查询用户失败: {}", ex.getLocalizedMessage());
        }
        Staff staff = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            staff = responseEntity.getBody();

        }
        return staff;
    }

    /**
     * 获取指定name的staff的详细信息，如果结果多于10条，只返回10条
     * 1. 如果name不为空，查询所有名字以name开头的staff,并获取其标签详情
     * 2. 如果name为空，就查询拥有标签集中标签最多的staff，以及标签详情
     * 3. 如果name 和 tags都为空，抛出请求参数不正确 异常
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByName(RtrvStaffListByNameRequest request) {
        //name以startingStr开头
        String startingStr = request.getName();
        List<String> tags = request.getTags();
        //实例化response
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();

        //如果startingStr为空，则按标签获取Staff
        if (StringUtils.isEmpty(startingStr) && tags != null) {
            //如果name为空，标签id也为空，则抛请求参数异常
            if (tags.isEmpty()) {
                throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
            }
            //1.查出拥有tags中任意一种标签的所有人
            List<StaffTag> staffList = rtrvStaffBytags(tags);
            //2.对staffId去重
            List<String> idList = new ArrayList<>(distinctStaffId(staffList));
            //3.获取拥有指定标签集中标签最多的用户，按匹配度最多获取前十
            List<StaffTagScore> stsList = rtrvStaffTagScore(staffList, tags, idList);
            //4.根据StaffTagScore集合获取每员工的个人及匹配标签的详细信息
            List<StaffMatched> staffMatcheds = getStaffMatchedByScore(stsList);

            rtrvStaffListByNameResponse.setStaffMatched(staffMatcheds);
            rtrvStaffListByNameResponse.setTotalNumber((long) stsList.size());
            restResponse = RestResponseUtil.success(rtrvStaffListByNameResponse);
            return ResponseEntity.ok(restResponse);
        }
        //startingStr不为空，则按查询name以startingStr开始的用户
        rtrvStaffListByNameResponse = getStaffByNameStarting(startingStr);
        restResponse = RestResponseUtil.success(rtrvStaffListByNameResponse);
        return ResponseEntity.ok(restResponse);
    }

    /**
     * 查询name以指定字符串开始的Staff
     *
     * @param startingStr
     * @return
     */
    private RtrvStaffListByNameResponse getStaffByNameStarting(String startingStr) {
        //1.获取name以指定字符串开头的所有sataff
        String rtrvStaffByNameStartingUrl = webConfig.getRtrvStaffByNameStartingUrl();
        Staff[] staffList = this.restTemplate.postForObject(rtrvStaffByNameStartingUrl + "/" + startingStr, startingStr, Staff[].class);
        //2.根据staffId获取其拥有的tagId集合的Url
        String rtrvTagsByStaffIdUrl = webConfig.getRtrvTagsByStaffIdUrl();
        List<StaffMatched> matcheds = new ArrayList<>();
        for (Staff staff : staffList) {
            //3.根据staffId获取其拥有的tagId集合的
            String[] tags = this.restTemplate.postForObject(rtrvTagsByStaffIdUrl + "/" + staff.getStaffId(), staff.getStaffId(), String[].class);
            StaffMatched matched = getStaffMatched(staff.getStaffId(), Arrays.asList(tags));
            matcheds.add(matched);
        }
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        rtrvStaffListByNameResponse.setStaffMatched(matcheds);
        rtrvStaffListByNameResponse.setTotalNumber((long) matcheds.size());
        return rtrvStaffListByNameResponse;
    }

    /**
     * 根据StaffTagScore集合获取每员工的个人及匹配标签的详细信息
     *
     * @param stsList
     * @return
     */
    private List<StaffMatched> getStaffMatchedByScore(List<StaffTagScore> stsList) {
        List<StaffMatched> matchedList = new ArrayList<>();
        for (int i = 0; i < stsList.size(); i++) {
            StaffMatched staffMatched = getStaffMatched(stsList.get(i).getStaffId(), stsList.get(i).getTagId());
            staffMatched.setRecommendation(stsList.get(i).getTagScore());
            matchedList.add(staffMatched);
        }
        return matchedList;
    }

    /**
     * 查询 拥有指定标签集中任意标签的StaffTag
     *
     * @param tags
     * @return
     */
    private List<StaffTag> rtrvStaffBytags(List<String> tags) {
        String rtrvStaffByTagsUrl = webConfig.getRtrvStaffByTagsUrl();
        ResponseEntity<StaffTag[]> responseEntity = this.restTemplate.postForEntity(rtrvStaffByTagsUrl, tags, StaffTag[].class);
        LOG.info("获取拥有标签集中标签的员工成功。。。");
        return Arrays.asList(responseEntity.getBody());
    }

    /**
     * 获取指定id的员工及标签的详细信息
     *
     * @param staffId
     * @param tags
     * @return
     */
    private StaffMatched getStaffMatched(String staffId, List<String> tags) {
        //根据Id获取员工信息
        String rtrvStaffInfoUrl = webConfig.getRtrvStaffInfoUrl();
        Staff staff = this.restTemplate.postForObject(rtrvStaffInfoUrl, staffId, Staff.class);
        //根据多个标签id获取标签详细信息Url
        String tagUrl = webConfig.getRtrvTagsUrl();
        //构建request
        RtrvTagsRequest rtrvTagsRequest = new RtrvTagsRequest();
        rtrvTagsRequest.setTagIds(tags);
        //获取多个指定id的标签集合
        ResponseEntity<Tag[]> respEntity = this.restTemplate.postForEntity(tagUrl, rtrvTagsRequest, Tag[].class);
        Tag[] tagList = respEntity.getBody();
        StaffMatched staffMatched = new StaffMatched();
        staffMatched.setStaff(staff);
        staffMatched.setTags(Arrays.asList(tagList));
        return staffMatched;
    }

    /**
     * 按标签推荐staff，对标签进行斐波那契数列赋值，最后按分值倒叙排列
     *
     * @param stList tagId在tags中的所有StaffTag对象；当StaffTag具有多个tag时，会有相同的staffId的多个StaffTag存在
     * @param tags   按照标签查Staff的参数
     * @param idList tagId在tags中的所有StaffTag不重复的staffId集合
     *               计算过程：
     *               1. 遍历idList， 并以每一个不重复的staffId 实例化一个StaffTagScore对象
     *               2. 计算每一个StaffTagScore 的tagScore
     *               3. 按照tagScore排序，最多获取前十条数据
     * @return
     */
    private List<StaffTagScore> rtrvStaffTagScore(List<StaffTag> stList, List<String> tags, List<String> idList) {
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
                    //2. 计算每一个StaffTagScore 的tagScore
                    sts = countTagScore(st, tags, sts);
                    LOG.info("第{}个staff, 标签score: {}", i, sts.getTagScore());
                }
            }
            stsList.add(sts);
        }
        return topTenAtMost(stsList);
    }

    /**
     * 1.根据tagScore排序，降序
     * 2.最多取前十条数据
     *
     * @param stsList
     * @return
     */
    private List<StaffTagScore> topTenAtMost(List<StaffTagScore> stsList) {
        LOG.info("排序前的StaffTagScore：{}", stsList.toString());
        //对StaffTagScore 按照tagScore排序，从高到底
        Comparator<StaffTagScore> comparator = (h1, h2) -> h1.getTagScore().compareTo(h2.getTagScore());
        stsList.sort(comparator.reversed());
        LOG.info("排序后的StaffTagScore：{}", stsList.toString());
        //取tag分值从高到底前10个staff数据
        List<StaffTagScore> sList = new ArrayList<>();
        int m = (stsList.size() > Integer.valueOf(ConstantEnum.TEN.getValue())) ? Integer.valueOf(ConstantEnum.TEN.getValue()) : stsList.size();
        if (stsList.isEmpty()) {
            return new ArrayList<>();
        }
        //最多取前十条数据
        for (int i = 0; i < m; i++) {
            LOG.info("staffId：{}", stsList.get(i).getStaffId());
            sList.add(stsList.get(i));
        }
        return sList;
    }

    /**
     * 根据斐波那契梳理计算每个tag对应的分值
     *
     * @param st
     * @param tags
     * @param sts
     * @return
     */
    private StaffTagScore countTagScore(StaffTag st, List<String> tags, StaffTagScore sts) {
        //如果tags只有一个元素
        if (tags.size() == Integer.valueOf(ConstantEnum.ONE.getValue())) {
            sts.setTagId(tags);
            sts.setTagScore(Double.valueOf(ConstantEnum.ONE.getValue()));
            return sts;
        }
        for (int j = 0; j < tags.size(); j++) {
            LOG.info("j的值是：{}", j);
            LOG.info("第{}个staff, 标签score: {}", st.getStaffId(), sts.getTagScore());
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
}
