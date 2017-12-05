package com.mdvns.mdvn.story.sapi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.enums.AuthEnum;
import com.mdvns.mdvn.common.utils.StaffAuthUtil;
import com.mdvns.mdvn.story.sapi.domain.*;
import com.mdvns.mdvn.story.sapi.domain.entity.*;
import com.mdvns.mdvn.story.sapi.repository.*;
import com.mdvns.mdvn.story.sapi.service.IUpdateStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class UpdateStoryServiceImpl implements IUpdateStoryService {
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

    @Autowired
    private StoryNoteRepository storyNoteRepository;

    /**
     * 更改用户故事基本信息
     *
     * @param st
     * @return
     */
    @Override
    public Story updateStoryBaseInfo(Story st) {
        LOG.info("start executing updateStoryBaseInfo()方法.", this.CLASS);
        story = this.storyRepository.findByStoryId(st.getStoryId());
        boolean changeFlag = false;
        if (story == null) {
            throw new NullPointerException("story cannot be found");
        }
        if (!StringUtils.isEmpty(st.getSummary()) && (!st.getSummary().equals(story.getSummary()))) {
            story.setSummary(st.getSummary());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getLabelId()) && (!st.getLabelId().equals(story.getLabelId()))) {
            story.setLabelId(st.getLabelId());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getDescription()) && (!st.getDescription().equals(story.getDescription()))) {
            story.setDescription(st.getDescription());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getStartDate()) && (!st.getStartDate().equals(story.getStartDate()))) {
            story.setStartDate(st.getStartDate());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getEndDate())) {
            story.setEndDate(st.getEndDate());
            //保存预期进度
            Timestamp startDate = st.getStartDate();
            Timestamp endDate = st.getEndDate();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000*3600*24));
            int nowDays = (int) ((currentTime.getTime() - startDate.getTime()) / (1000*3600*24));
            Float expectProgress = Float.valueOf(nowDays*100/days);
            DecimalFormat df = new DecimalFormat("#.00");
            expectProgress = Float.valueOf(df.format(expectProgress));
            //保存ragStatus
            if (expectProgress !=0){
                Float percent = story.getProgress() / expectProgress;
                if (percent >= 1) {
                    story.setRagStatus("G");
                }
                if (percent < 1 && percent >= 0.5) {
                    story.setRagStatus("A");
                }
                if (percent<0.5){
                    story.setRagStatus("R");
                }
            }
            if(expectProgress<0){
                expectProgress = Float.valueOf(0);
            }
            if (expectProgress>100){
                expectProgress = Float.valueOf(100);
            }
            story.setExpectProgress(expectProgress);
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getPriority()) && (!st.getPriority().equals(story.getPriority()))) {
            story.setPriority(st.getPriority());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getStatus()) && (!st.getStatus().equals(story.getStatus()))) {
            story.setStatus(st.getStatus());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getProgress()) && (!st.getProgress().equals(story.getProgress()))) {
            story.setProgress(st.getProgress());
            changeFlag = true;
        }
        if (!StringUtils.isEmpty(st.getStoryPoint()) && (!st.getStoryPoint().equals(story.getStoryPoint()))) {
            story.setStoryPoint(st.getStoryPoint());
            changeFlag = true;
        }


//        if (!StringUtils.isEmpty(st.getReqmntId()) && (!st.getReqmntId().equals(story.getReqmntId()))) {
//            story.setReqmntId(st.getReqmntId());
//        }

        //之后ragStatus需要后台计算以后传给前台
        if (!StringUtils.isEmpty(st.getRagStatus()) && (!st.getRagStatus().equals(story.getRagStatus()))) {
            story.setRagStatus(st.getRagStatus());
        }
        if (changeFlag) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            story.setLastUpdateTime(currentTime);
            story = storyRepository.save(story);
        }
        LOG.info("finish executing updateStoryBaseInfo()方法.", this.CLASS);
        return story;
    }

    /**
     * 更改用户故事成员信息
     *
     * @param list
     * @return
     */
    @Override
    public List<StoryRoleMember> updateStoryMembers(UpdateSMembersRequest list) {
        LOG.info("start executing updateStoryLeaders()方法.", this.CLASS);
        if (list == null || list.getsRoleMembers().size() <= 0) {
            throw new NullPointerException("StoryLeaders List is empty");
        }
        String storyId = list.getStoryId();
        //选出不同的角色
        List<String> roleIds = new ArrayList<>();
        for (int i = 0; i < list.getsRoleMembers().size(); i++) {
            String id = list.getsRoleMembers().get(i).getRoleId();
            if (!roleIds.isEmpty() && roleIds.contains(id)) {
                continue;
            }
            roleIds.add(list.getsRoleMembers().get(i).getRoleId());
        }
        //将数据库中没有的插入(分单个角色update)
        for (int j = 0; j < roleIds.size(); j++) {
            String roleId = roleIds.get(j);
            List<String> staffIdList = new ArrayList();
            List<String> addAuthList = new ArrayList<>();
            for (int i = 0; i < list.getsRoleMembers().size(); i++) {
                if (roleId.equals(list.getsRoleMembers().get(i).getRoleId())) {
                    StoryRoleMember storyLeader = new StoryRoleMember();
                    staffIdList.add(list.getsRoleMembers().get(i).getStaffId());
                    storyLeader = this.storyRoleMemberRepository.findByStoryIdAndRoleIdAndStaffId(storyId, list.getsRoleMembers().get(i).getRoleId(), list.getsRoleMembers().get(i).getStaffId());
                    //不存在的加上
                    if (storyLeader == null) {
                        list.getsRoleMembers().get(i).setStoryId(storyId);
                        list.getsRoleMembers().get(i).setIsDeleted(0);
                        this.storyRoleMemberRepository.saveAndFlush(list.getsRoleMembers().get(i));
                        addAuthList.add(list.getsRoleMembers().get(i).getStaffId());
                    } else {
                        //之前是成员后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                        if (storyLeader.getIsDeleted().equals(1)) {
                            String sql = "UPDATE staff_role_story_map SET is_deleted= 0 WHERE story_id=" + "\"" + storyId + "\"" + "AND  role_id =" + "\"" + list.getsRoleMembers().get(i).getRoleId() + "\"" + "AND  staff_id =" + "\"" + list.getsRoleMembers().get(i).getStaffId() + "\"" + "";
                            this.jdbcTemplate.update(sql);
                        }
                    }
                    //2.1 给新增的story成员分配权限
                    RestTemplate restTemplate = new RestTemplate();
                    StaffAuthUtil.assignAuth(restTemplate, new AssignAuthRequest(list.getProjId(), list.getStaffId(), addAuthList, list.getStoryId(), AuthEnum.SMEMBER.getCode()));
                    LOG.info("更新项目，给story：{}新增的member:{},分配权限成功:{}",list.getStoryId(), list.getStaffId(),addAuthList.toString());
                }
            }
                //将数据库中将要删除的成员信息修改is_deleted状态
                //数组转化为字符串格式
                StringBuffer members = new StringBuffer();
                for (int k = 0; k < staffIdList.size(); k++) {
                    members.append("\"" + staffIdList.get(k) + "\"");
                    members.append(",");
                }
                String pLeaders = members.substring(0, members.length() - 1);
                String sql = "UPDATE staff_role_story_map SET is_deleted= 1 WHERE story_id= " + "\"" + storyId + "\"" + "AND  role_id = " + "\"" + roleIds.get(j) + "\"" + " AND staff_id NOT IN (" + pLeaders + ")";
                this.jdbcTemplate.update(sql);
        }
        //查询数据库中有效的负责人
        List<StoryRoleMember> storyLeaders = this.storyRoleMemberRepository.findSRoleMembers(storyId);
        LOG.info("finish executing updateStoryLeaders()方法.", this.CLASS);
        return storyLeaders;
    }


    /**
     * 更改用户故事标签信息
     *
     * @param list
     * @return
     */
    @Override
    public List<StoryTag> updateStoryTags(UpdateSTagsRequest list) {
        LOG.info("start executing updateStoryTags()方法.", this.CLASS);
        if (list == null || list.getsTags().size() <= 0) {
            throw new NullPointerException("StoryTags List is empty");
        }
        String storyId = list.getStoryId();
        List<String> tagIdList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getsTags().size(); i++) {
            StoryTag storyTag = new StoryTag();
            tagIdList.add(list.getsTags().get(i).getTagId());
            storyTag = this.storyTagRepository.findAllByStoryIdAndTagId(storyId, list.getsTags().get(i).getTagId());
            //不存在的加上
            if (storyTag == null) {
                list.getsTags().get(i).setStoryId(storyId);
                list.getsTags().get(i).setIsDeleted(0);
                this.storyTagRepository.saveAndFlush(list.getsTags().get(i));
            } else {
                //之前是标签后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (storyTag.getIsDeleted().equals(1)) {
                    String sql = "UPDATE tag_story_map SET is_deleted= 0 WHERE story_id=" + "\"" + storyId + "\"" + "AND tag_id =" + "\"" + list.getsTags().get(i).getTagId() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的标签信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer tags = new StringBuffer();
        for (int i = 0; i < tagIdList.size(); i++) {
            tags.append("\"" + tagIdList.get(i) + "\"");
            tags.append(",");
        }
        String sTags = tags.substring(0, tags.length() - 1);
        String sql = "UPDATE tag_story_map SET is_deleted= 1 WHERE story_id= " + "\"" + storyId + "\"" + " AND tag_id NOT IN (" + sTags + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的标签信息
        List<StoryTag> storyTags = this.storyTagRepository.findSTags(storyId);
        LOG.info("finish executing updateStoryTags()方法.", this.CLASS);
        return storyTags;
    }

    /**
     * 更改用户故事checkList信息
     *
     * @param list
     * @return
     */
    @Override
    public List<StoryTask> updateStoryTasks(UpdateSTasksRequest list) {
        LOG.info("start executing updateStoryTasks()方法.", this.CLASS);
        if (list == null || list.getsTasks().size() <= 0) {
            throw new NullPointerException("StoryTasks List is empty");
        }
        List<StoryTask> returnList = new ArrayList<>();
        List uuIds = new ArrayList();
        for (int i = 0; i < list.getsTasks().size(); i++) {
            //新建checkList
            if (StringUtils.isEmpty(list.getsTasks().get(i).getTaskId())) {
                list.getsTasks().get(i).setStoryId(list.getStoryId());
                list.getsTasks().get(i).setCreatorId(list.getStaffId());
                list.getsTasks().get(i).setStatus("new");
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                list.getsTasks().get(i).setCreateTime(currentTime);
                list.getsTasks().get(i).setIsDeleted(0);
                StoryTask storyTask = storyTaskRepository.saveAndFlush(list.getsTasks().get(i));
                storyTask.setTaskId("T" + storyTask.getUuId());
                StoryTask sTask = storyTaskRepository.saveAndFlush(storyTask);
                uuIds.add(sTask.getUuId());
            } else {
                //修改checkList
                StoryTask record = new StoryTask();
                record = this.storyTaskRepository.findByTaskId(list.getsTasks().get(i).getTaskId());
            /* indicate if this record has updated*/
                Boolean flag = false;
                if (record == null) {
                    throw new NullPointerException("Record cannot be found");
                }
                uuIds.add(record.getUuId());
            /* Determine if the attributes of this record need to be updated*/
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getTaskDesc()) && (!list.getsTasks().get(i).getTaskDesc().equals(record.getTaskDesc()))) {
                    record.setTaskDesc(list.getsTasks().get(i).getTaskDesc());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getStartDate()) && (!list.getsTasks().get(i).getStartDate().equals(record.getStartDate()))) {
                    record.setStartDate(list.getsTasks().get(i).getStartDate());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getEndDate()) && (!list.getsTasks().get(i).getEndDate().equals(record.getEndDate()))) {
                    record.setEndDate(list.getsTasks().get(i).getEndDate());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getAssignerId()) && (!list.getsTasks().get(i).getAssignerId().equals(record.getAssignerId()))) {
                    record.setAssignerId(list.getsTasks().get(i).getAssignerId());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getExecutorId()) && (!list.getsTasks().get(i).getExecutorId().equals(record.getExecutorId()))) {
                    record.setExecutorId(list.getsTasks().get(i).getExecutorId());
                    flag = true;
                }
                if (!StringUtils.isEmpty(list.getsTasks().get(i).getStatus()) && (!list.getsTasks().get(i).getStatus().equals(record.getStatus()))) {
                    record.setStatus(list.getsTasks().get(i).getStatus());
                    flag = true;
                }
                if (record.getIsDeleted().equals(1)) {
                    record.setIsDeleted(0);
                    flag = true;
                }

                if (flag) {
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    record.setLastUpdateTime(currentTime);
                    if (!StringUtils.isEmpty(list.getsTasks().get(i).getStatus()) && (list.getsTasks().get(i).getStatus() == "close")) {
                        record.setCloseTime(currentTime);
                    }
                    record = this.storyTaskRepository.saveAndFlush(record);
//                returnList.add(record);
                }
            }
        }
        //数组转化为字符串格式
        StringBuffer uuIdList = new StringBuffer();
        for (int j = 0; j < uuIds.size(); j++) {
            uuIdList.append(uuIds.get(j));
            uuIdList.append(",");
        }
        String pUuIdList = uuIdList.substring(0, uuIdList.length() - 1);
        String sql = "UPDATE task_story SET is_deleted= 1 WHERE story_id= " + "\"" + list.getStoryId() + "\"" + " AND uu_id NOT IN (" + pUuIdList + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的用户故事checkList
        List<StoryTask> storyChecklists = this.storyTaskRepository.findSTasks(list.getStoryId());
        returnList.addAll(storyChecklists);
        LOG.info("finish executing updateStoryTasks()方法.", this.CLASS);
        return returnList;
    }
    /**
     * 更改story附件信息
     *
     * @param list
     * @return
     */
    @Override
    public List<StoryAttchUrl> updateStoryAttchUrls(UpdateAttchUrlsRequest list) {
        LOG.info("start executing updateStoryAttchUrls()方法.", this.CLASS);
        if (list == null || list.getAttchUrls().size() <= 0) {
            throw new NullPointerException("getAttchUrls List is empty");
        }
        String storyId = list.getStoryId();
        List<Integer> attachmentIdList = new ArrayList();
        //将数据库中没有的插入
        for (int i = 0; i < list.getAttchUrls().size(); i++) {
            StoryAttchUrl storyAttchUrl = new StoryAttchUrl();
            attachmentIdList.add(list.getAttchUrls().get(i).getAttachmentId());
            storyAttchUrl = this.storyAttchRepository.findAllByStoryIdAndAttachmentId(storyId, list.getAttchUrls().get(i).getAttachmentId());
            //不存在的加上
            if (storyAttchUrl == null) {
                list.getAttchUrls().get(i).setStoryId(storyId);
                list.getAttchUrls().get(i).setIsDeleted(0);
                this.storyAttchRepository.saveAndFlush(list.getAttchUrls().get(i));
            } else {
                //之前是附件后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (storyAttchUrl.getIsDeleted().equals(1)) {
                    String sql = "UPDATE attachment_story SET is_deleted= 0 WHERE story_id=" + "\"" + storyId + "\"" + "AND attachment_id =" + "\"" + list.getAttchUrls().get(i).getAttachmentId() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的附件信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer attchUrls = new StringBuffer();
        for (int i = 0; i < attachmentIdList.size(); i++) {
            attchUrls.append("\"" + attachmentIdList.get(i) + "\"");
            attchUrls.append(",");
        }
        String sAttchUrls = attchUrls.substring(0, attchUrls.length() - 1);
        String sql = "UPDATE attachment_story SET is_deleted= 1 WHERE story_id= " + "\"" + storyId + "\"" + " AND attachment_id NOT IN (" + sAttchUrls + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的附件
        List<StoryAttchUrl> storyAttchUrls = this.storyAttchRepository.findSAttchUrls(storyId);
        LOG.info("finish executing updateStoryAttchUrls()方法.", this.CLASS);
        return storyAttchUrls;
    }

    /**
     * 更改便签信息
     * @param request
     * @return
     */
    @Override
    public StoryNote updateStoryNote(UpdateSNoteRequest request) {
        StoryNote storyNote = new StoryNote();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String storyId = request.getStoryId();
        String noteDesc = request.getNoteDesc();
        String creatorId = request.getCreatorId();
        StoryNote stoNote = this.storyNoteRepository.findByStoryId(storyId);
        if (stoNote == null){
            storyNote.setCreatorId(creatorId);
            storyNote.setNoteDesc(noteDesc);
            storyNote.setStoryId(storyId);
            storyNote.setLastUpdateTime(currentTime);
            storyNote.setIsDeleted(0);
        } else{
            storyNote = stoNote;
            storyNote.setCreatorId(creatorId);
            storyNote.setNoteDesc(noteDesc);
            storyNote.setLastUpdateTime(currentTime);
        }
        storyNote = this.storyNoteRepository.saveAndFlush(storyNote);
        return storyNote;
    }

}
