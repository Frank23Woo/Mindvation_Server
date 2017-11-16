package com.mdvns.mdvn.story.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.story.sapi.domain.*;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.repository.*;
import com.mdvns.mdvn.story.sapi.service.ICreateStoryService;
import com.mdvns.mdvn.story.sapi.service.ICreateStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateStoryServiceImpl implements ICreateStoryService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(CreateStoryServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private Story story;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryRoleMemberRepository storyRoleMemberRepository;

    @Autowired
    private StoryTagRepository storyTagRepository;


    @Autowired
    private StoryTaskRepository storyTaskRepository;

    @Autowired
    private StoryAttchRepository storyAttchRepository;

    /**
     * 获取story整个列表
     *
     * @return
     */
    public ResponseEntity<?> rtrvStoryInfoList(RtrvStoryListRequest request) throws SQLException {
        RtrvStoryListResponse rtrvStoryListResponse = new RtrvStoryListResponse();
        if (request.getPage() != null && request.getPageSize() != null) {
            if (request.getPage() < 1 || request.getPageSize() < 1) {
                throw new IllegalArgumentException("Illegal Arguments for Pagination");
            }
        }
        if (request.getPage() == null || request.getPageSize() == null) {
            List<Story> list = this.storyRepository.findAllByReqmntIdAndIsDeletedOrderByUuIdAsc(request.getReqmntId(), 0);
            //获取story列表重新计算reqm的进度
            RtrvAverageReqmProgress rRequest = new RtrvAverageReqmProgress();
            String reqmId = request.getReqmntId();
            rRequest.setReqmId(reqmId);
            Float reqmProgress = this.averageReqmProgress(rRequest);
            String sql = "UPDATE requirement_info SET progress = " + reqmProgress + " WHERE reqmnt_id=" + "\"" + reqmId + "\"";
            this.jdbcTemplate.update(sql);
            //所有的story进度都为100时，reqmnt的状态变为done
            Float progresses = Float.valueOf(0);
            String status = this.storyRepository.rtrvStatus(reqmId);
            for (int i = 0; i < list.size(); i++) {
                progresses += list.get(i).getProgress();
            }
            if (progresses / list.size() == 100) {
                status = "done";
            }
            String statusSql = "UPDATE requirement_info SET status = " + "\""+ status+ "\"" + " WHERE reqmnt_id=" + "\"" + reqmId + "\"";
            this.jdbcTemplate.update(statusSql);

            rtrvStoryListResponse.setStories(list);
            rtrvStoryListResponse.setTotalElements(Long.valueOf(list.size()));
            return ResponseEntity.ok(rtrvStoryListResponse);
//            return RestResponseUtil.success(rtrvStoryListResponse);
        } else {
            Integer page = request.getPage();
            Integer pageSize = request.getPageSize();
            List<String> sortBy = request.getSortBy();
            Page<Story> storyInfos = null;
            PageRequest pageable = null;

            if (sortBy == null) {
                pageable = new PageRequest(page - 1, pageSize);
            } else {
                Sort.Order order = null;
                for (int i = 0; i < sortBy.size(); i++) {
                    order = new Sort.Order(Sort.Direction.ASC, sortBy.get(i));
                }
                Sort sort = new Sort(order);
                pageable = new PageRequest(page - 1, pageSize, sort);
            }
            storyInfos = this.storyRepository.findAllByReqmntIdAndIsDeleted(request.getReqmntId(), 0, pageable);
            rtrvStoryListResponse.setStories(storyInfos.getContent());
            rtrvStoryListResponse.setTotalElements(storyInfos.getTotalElements());
            //获取story列表重新计算reqm的进度
            RtrvAverageReqmProgress rRequest = new RtrvAverageReqmProgress();
            String reqmId = request.getReqmntId();
            rRequest.setReqmId(reqmId);
            Float reqmProgress = this.averageReqmProgress(rRequest);
            String sql = "UPDATE requirement_info SET progress = " + reqmProgress + " WHERE reqmnt_id=" + "\"" + reqmId + "\"";
            this.jdbcTemplate.update(sql);
            //所有的story进度都为100时，reqmnt的状态变为done
            Float progresses = Float.valueOf(0);
            String status = this.storyRepository.rtrvStatus(reqmId);
            for (int i = 0; i < storyInfos.getContent().size(); i++) {
                progresses += storyInfos.getContent().get(i).getProgress();
            }
            if (progresses / storyInfos.getContent().size() == 100) {
                status = "done";
            }
            String statusSql = "UPDATE requirement_info SET status = " + "\""+ status+ "\"" + " WHERE reqmnt_id=" + "\"" + reqmId + "\"";
            this.jdbcTemplate.update(statusSql);
            LOG.info("查询结果为：{}", rtrvStoryListResponse);
            return ResponseEntity.ok(rtrvStoryListResponse);
        }
    }

    /**
     * 通过reqmntlist获取story整个列表
     *
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvStoryInfoListByReqmntIds(RtrvStoryListByReqmntIdsRequest request) throws SQLException {
        RtrvStoryListByReqmntIdsResponse rtrvStoryListResponse = new RtrvStoryListByReqmntIdsResponse();
        List<String> reqmntIds = request.getReqmntIds();
        List<Story> list = this.storyRepository.rtrvStoryInfoList(reqmntIds);
        rtrvStoryListResponse.setStories(list);
        rtrvStoryListResponse.setTotalElements(Long.valueOf(list.size()));
        return ResponseEntity.ok(rtrvStoryListResponse);
    }

    /**
     * 通过storylist获取story整个列表
     *
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvStoryInfoListByStoryIds(RtrvStoryListByStoryIdsRequest request) throws SQLException {
        RtrvStoryListByStoryIdsResponse rtrvStoryListResponse = new RtrvStoryListByStoryIdsResponse();
        List<String> storyIds = request.getStoryIds();
        List<Story> list = this.storyRepository.rtrvStoryInfoByStoryIdsList(storyIds);
        rtrvStoryListResponse.setStories(list);
        rtrvStoryListResponse.setTotalElements(Long.valueOf(list.size()));
        return ResponseEntity.ok(rtrvStoryListResponse);
    }

    /**
     * 创建story（基本信息）
     *
     * @param createStoryRequest
     * @return
     */
    @Override
    public ResponseEntity<?> saveStory(CreateStoryRequest createStoryRequest) {
        //先保存项目基本信息
        Story story = new Story();
        if (StringUtils.isEmpty(createStoryRequest) || StringUtils.isEmpty(createStoryRequest.getStoryInfo().getSummary()) ||
                StringUtils.isEmpty(createStoryRequest.getCreatorId()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getReqmntId()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getDescription()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getStartDate()) ||
                StringUtils.isEmpty(createStoryRequest.getStoryInfo().getEndDate())) {
            throw new NullPointerException("createStoryRequest不能为空 或创建者Id不能为空 或所属需求 或用户故事概要不能为空 或用户故事描述不能为空 或者用户故事开始结束时间不能为空");
        }
        story.setProjId(createStoryRequest.getStoryInfo().getProjId());
        story.setReqmntId(createStoryRequest.getStoryInfo().getReqmntId());
        story.setSummary(createStoryRequest.getStoryInfo().getSummary());
        story.setDescription(createStoryRequest.getStoryInfo().getDescription());
        story.setCreatorId(createStoryRequest.getCreatorId());
        story.setLabelId(createStoryRequest.getSubFunctionLabel().getLabelId());
        story.setStoryPoint(createStoryRequest.getStoryInfo().getStoryPoint());
        story.setIsDeleted(0);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        story.setCreateTime(currentTime);
        story.setLastUpdateTime(currentTime);
        story.setStatus("new");
        story.setRagStatus("G");
        story.setProgress((float) 0);
        story.setStartDate(createStoryRequest.getStoryInfo().getStartDate());
        story.setEndDate(createStoryRequest.getStoryInfo().getEndDate());
        if (!StringUtils.isEmpty(createStoryRequest.getStoryInfo().getPriority())) {
            story.setPriority(createStoryRequest.getStoryInfo().getPriority());
        }
        Story sto = storyRepository.saveAndFlush(story);
        story.setStoryId("S" + sto.getUuId());
        //创建完story重新计算reqm的进度
        RtrvAverageReqmProgress request = new RtrvAverageReqmProgress();
        String reqmId = createStoryRequest.getStoryInfo().getReqmntId();
        request.setReqmId(reqmId);
        Float reqmProgress = this.averageReqmProgress(request);
        String sql = "UPDATE requirement_info SET progress = " + reqmProgress + " WHERE reqmnt_id=" + "\"" + reqmId + "\"";
        this.jdbcTemplate.update(sql);
        //保存storyId;
        Story st = storyRepository.saveAndFlush(story);
        ResponseEntity<?> responseEntity = new ResponseEntity<Object>(st, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 创建用户故事时保存members信息
     *
     * @param members
     * @return
     */
    @Override
    public List<StoryRoleMember> saveSRoleMembers(List<StoryRoleMember> members) {
        for (int i = 0; i < members.size(); i++) {
            members.get(i).setIsDeleted(0);
        }
        List<StoryRoleMember> pleaders = storyRoleMemberRepository.save(members);
        return pleaders;
    }

    /**
     * 创建story时保存标签信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTag> saveSTags(List<StoryTag> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<StoryTag> storyTags = storyTagRepository.save(request);
        return storyTags;
    }

    /**
     * 创建STORY时保存附件信息
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryAttchUrl> saveSAttchUrls(List<StoryAttchUrl> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<StoryAttchUrl> storyAttchUrls = this.storyAttchRepository.save(request);
        return storyAttchUrls;
    }

    /**
     * 保存story多条任务（TASKS）
     *
     * @param request
     * @return
     */
    @Override
    public List<StoryTask> saveSTasks(SaveSTasksRequest request) {
        for (int i = 0; i < request.getSTasks().size(); i++) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            request.getSTasks().get(i).setCreateTime(currentTime);
            request.getSTasks().get(i).setIsDeleted(0);
            request.getSTasks().get(i).setStatus("new");
            request.getSTasks().get(i).setCreatorId(request.getStaffId());
        }
        List<StoryTask> storyTaskList = storyTaskRepository.save(request.getSTasks());
        for (int j = 0; j < storyTaskList.size(); j++) {
            storyTaskList.get(j).setTaskId("T" + storyTaskList.get(j).getUuId());
        }
        List<StoryTask> storyTasks = storyTaskRepository.save(storyTaskList);
        return storyTasks;
    }


    /**
     * 通过主键查询单条story数据
     *
     * @param uuId
     * @return
     */
    @Override
    public Story getStory(Integer uuId) {
        Object obj = storyRepository.rtrvStoryBaseInfo(uuId);
        Story story = new Story();
        Object[] objs = (Object[]) obj;
        if (obj.toString().length() > 0) {
            story.setStoryId((String) objs[1]);
        }
        return story;

    }

    /**
     * Req的进度为所有Story进度的平均值（Story Point为权重1，优先级设定优先级系数。低：1.2；中：1.3；高：1.5）
     *
     * @param request
     * @return
     */
    @Override
    public Float averageReqmProgress(RtrvAverageReqmProgress request) {
        //获取一个proj下优先级种类
        List<Integer> prioritys = this.storyRepository.findPriority(request.getReqmId());
        Float reqmProgressByPriority = Float.valueOf(0);
        for (int i = 0; i < prioritys.size(); i++) {
            Integer priority = prioritys.get(i);
            //获取一个reqmnt下story的storypoint的总和(某个优先级下)
            Float storyPoints = this.storyRepository.rtrvStoryPointQty(request.getReqmId(), priority);
            if (storyPoints == 0) {
                continue;
            }
            //获取某个reqmnt下的高优先级的story列表
            List<Story> stories = this.storyRepository.findAllByReqmntIdAndIsDeletedAndPriority(request.getReqmId(), 0, priority);
            //给各种优先级的情况分配优先级系数
            double flag = 0;
            /*高~中~低*/
            if (prioritys.size() == 3) {
                if (priority.equals(3)) {
                    flag = 0.5;
                }
                if (priority.equals(2)) {
                    flag = 0.3;
                } else {
                    flag = 0.2;
                }
            }
            /*高~中*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 5) {
                if (priority.equals(3)) {
                    flag = 0.6;
                } else {
                    flag = 0.4;
                }
            }
            /*高~低*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 4) {
                if (priority.equals(3)) {
                    flag = 0.7;
                } else {
                    flag = 0.3;
                }
            }
            /*中~低*/
            if (prioritys.size() == 2 && prioritys.get(0) + prioritys.get(1) == 3) {
                if (priority.equals(2)) {
                    flag = 0.6;
                } else {
                    flag = 0.4;
                }
            }
            if (prioritys.size() == 1) {
                flag = 1;
            }
            //计算某个优先级下的进度的平均值
            Float reqmAverageProgress = Float.valueOf(0);
            for (int j = 0; j < stories.size(); j++) {
                Story story = stories.get(j);
                Float progress = story.getProgress();
                Float storyPoint = story.getStoryPoint();
                //某个story所占的比重
                Float spProportion = storyPoint / storyPoints;
                Float progressProportion = progress * spProportion;
                reqmAverageProgress += progressProportion;
            }
            Float reqmProgByPriority = ((float) (reqmAverageProgress * flag));
            reqmProgressByPriority += reqmProgByPriority;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        reqmProgressByPriority = Float.valueOf(df.format(reqmProgressByPriority));
        return reqmProgressByPriority;
    }
}
