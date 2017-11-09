package com.mdvns.mdvn.reqmnt.sapi.service.impl;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.RoleMember;
import com.mdvns.mdvn.reqmnt.sapi.domain.UpdateReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;
import com.mdvns.mdvn.reqmnt.sapi.repository.*;
import com.mdvns.mdvn.reqmnt.sapi.service.IUpdateReqmntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateReqmntServiceImpl implements IUpdateReqmntService {

    @Autowired
    private ReqmntRepository reqmntRepository;
    @Autowired
    private ReqmntMemberRepository memberRepository;
    @Autowired
    private ReqmntCheckListRepository checkListRepository;
    @Autowired
    private ReqmntAttchUrlsRepository attchUrlsRepository;
    @Autowired
    private ReqmntTagRepository tagRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateReqmntInfo(UpdateReqmntInfoRequest request) throws Exception {
        boolean result = false;

        boolean changeFlag = false;

        final String reqmntId = request.getReqmntInfo().getReqmntId();

        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 基本信息
        if (request.getReqmntInfo() != null) {
            RequirementInfo newInfo = request.getReqmntInfo();
            RequirementInfo oldInfo = reqmntRepository.findByReqmntIdAndIsDeleted(newInfo.getReqmntId(), 0);
            if (oldInfo != null) {
                // sumary
                if (newInfo.getSummary() != null && !newInfo.getSummary().equals(oldInfo.getSummary())) {
                    oldInfo.setSummary(newInfo.getSummary());
                    changeFlag = true;
                }

                // description
                if (newInfo.getDescription() != null && !newInfo.getDescription().equals(oldInfo.getDescription())) {
                    oldInfo.setDescription(newInfo.getDescription());
                    changeFlag = true;
                }

                // priority
                if (newInfo.getPriority() != null && !newInfo.getPriority().equals(oldInfo.getPriority())) {
                    oldInfo.setPriority(newInfo.getPriority());
                    changeFlag = true;
                }

                // FunctionLabelId
                if (newInfo.getFunctionLabelId() != null && !newInfo.getFunctionLabelId().equals(oldInfo.getFunctionLabelId())) {
                    oldInfo.setFunctionLabelId(newInfo.getFunctionLabelId());
                    changeFlag = true;
                }

                // startDate
                if (newInfo.getStartDate() != null && !newInfo.getStartDate().equals(oldInfo.getStartDate())) {
                    oldInfo.setStartDate(newInfo.getStartDate());
                    changeFlag = true;
                }

                // EndDate
                if (newInfo.getEndDate() != null && !newInfo.getEndDate().equals(oldInfo.getEndDate())) {
                    oldInfo.setEndDate(newInfo.getEndDate());
                    changeFlag = true;
                }

                // status
                if (newInfo.getStatus() != null && !newInfo.getStatus().equals(oldInfo.getStatus())) {
                    oldInfo.setStatus(newInfo.getStatus());
                    changeFlag = true;
                }

                // progress
                if (newInfo.getProgress() != null && !newInfo.getProgress().equals(oldInfo.getProgress())) {
                    oldInfo.setProgress(newInfo.getProgress());
                    changeFlag = true;
                }

                // ragStatus
                if (newInfo.getRagStatus() != null && !newInfo.getRagStatus().equals(oldInfo.getRagStatus())) {
                    oldInfo.setRagStatus(newInfo.getRagStatus());
                    changeFlag = true;
                }

                // totalStoryPoint

                // remarks
                if (newInfo.getRemarks() != null && !newInfo.getRemarks().equals(oldInfo.getRemarks())) {
                    oldInfo.setRemarks(newInfo.getRemarks());
                    changeFlag = true;
                }

                //modelID change
                if (!StringUtils.isEmpty(newInfo.getModelId()) && !newInfo.getModelId().equals(oldInfo.getModelId())) {
                    oldInfo.setModelId(newInfo.getModelId());
                    changeFlag = true;
                }

                if (changeFlag) {
                    oldInfo.setLastUpdateTime(System.currentTimeMillis());
                    reqmntRepository.save(oldInfo);
                }
            }
        }
//        if (request.getFunctionLabel() !=null){
//
//        }


//        // tags
//        if (request.getTags() != null) {
//            tagRepository.deleteAllByReqmntId(request.getReqmntInfo().getReqmntId());
//            List<Tag> tags = request.getTags();
//            List<ReqmntTag> reqmntTags = new ArrayList<>(tags.size());
//            Timestamp now = new Timestamp(System.currentTimeMillis());
//            for (Tag tag : tags) {
//                ReqmntTag reqmntTag = new ReqmntTag();
//                reqmntTag.setIsDeleted(0);
//                reqmntTag.setReqmntId(request.getReqmntInfo().getReqmntId());
//                reqmntTag.setTagId(tag.getTagId());
//                reqmntTag.setUpdateTime(now);
//            }
//
//            List<> tagRepository.save(reqmntTags);
//        }

        //tags
        List<Tag> currentTags = request.getTags();
        if (currentTags != null && currentTags.size() == 0) {
            List<ReqmntTag> deleteList = tagRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            for (int i = 0; i < deleteList.size(); i++) {
                deleteList.get(i).setIsDeleted(1);
                deleteList.get(i).setUpdateTime(now);
            }
            tagRepository.save(deleteList);
        } else if (currentTags != null && currentTags.size() > 0) {

            List<ReqmntTag> oldTagList = new ArrayList<>();
            List<Tag> addTagList = currentTags;

            oldTagList = tagRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            List<ReqmntTag> rmvTagList = oldTagList;
            for (int i = 0; i < currentTags.size(); i++) {
                for (int j = 0; j < oldTagList.size(); j++) {
                    if (oldTagList.get(j).getTagId().equals(currentTags.get(i).getTagId())) {
                        rmvTagList.remove(j);
                    }

                }
            }

            if (!rmvTagList.isEmpty()) {
                for (int i = 0; i < rmvTagList.size(); i++) {
                    rmvTagList.get(i).setIsDeleted(1);
                    rmvTagList.get(i).setUpdateTime(now);
                }
                tagRepository.save(rmvTagList);
            }

            oldTagList = tagRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            for (int j = 0; j < oldTagList.size(); j++) {
                for (int i = 0; i < currentTags.size(); i++) {
                    if (currentTags.get(i).getTagId().equals(oldTagList.get(j).getTagId())) {
                        addTagList.remove(i);
                    }
                }
            }


            if (!addTagList.isEmpty()) {
                List<ReqmntTag> addReqmntTagList = new ArrayList<>();
                for (int i = 0; i < addTagList.size(); i++) {
                    ReqmntTag reqmntTag = new ReqmntTag();
                    reqmntTag.setReqmntId(reqmntId);
                    reqmntTag.setTagId(addTagList.get(i).getTagId());
                    reqmntTag.setIsDeleted(0);
                    reqmntTag.setUpdateTime(now);
                    addReqmntTagList.add(reqmntTag);
                }
                tagRepository.save(addReqmntTagList);
            }


        }


        // attch urls
        changeFlag = false;
        List<ReqmntAttchUrl> currentAttches = request.getAttchUrls();
//        List<Tag> currentTags = request.getTags();
        if (currentAttches != null && currentAttches.size() == 0) {
//            List<ReqmntTag> deleteList = tagRepository.findAllByReqmntIdAndIsDeleted(reqmntId,0);
            List<ReqmntAttchUrl> deleteList = attchUrlsRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);

            for (int i = 0; i < deleteList.size(); i++) {
                deleteList.get(i).setIsDeleted(1);
                deleteList.get(i).setUpdateTime(now);
            }
//            tagRepository.save(deleteList);
            attchUrlsRepository.save(deleteList);
        } else if (currentAttches != null && currentAttches.size() > 0) {

            List<ReqmntAttchUrl> oldAttaches = attchUrlsRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            List<ReqmntAttchUrl> rmvAttchList = oldAttaches;
            List<ReqmntAttchUrl> addAttchList = currentAttches;


            for (int i = 0; i < currentAttches.size(); i++) {
                for (int j = 0; j < oldAttaches.size(); j++) {
                    if (oldAttaches.get(j).getAttachmentId().equals(currentAttches.get(i).getAttachmentId())) {
                        rmvAttchList.remove(j);
                    }

                }
            }

            if (!rmvAttchList.isEmpty()) {
                for (int i = 0; i < rmvAttchList.size(); i++) {
                    rmvAttchList.get(i).setIsDeleted(1);
                    rmvAttchList.get(i).setUpdateTime(now);
                }
                attchUrlsRepository.save(rmvAttchList);
            }

            oldAttaches = attchUrlsRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            for (int i = 0; i < currentAttches.size(); i++) {
                for (int j = 0; j < oldAttaches.size(); j++) {
                    if (currentAttches.get(i).getAttachmentId().equals(oldAttaches.get(j).getAttachmentId())) {
                        addAttchList.remove(i);
                    }
                }
            }


            if (!addAttchList.isEmpty()) {
                List<ReqmntAttchUrl> addReqmentAttchList = new ArrayList<>();
                for (int i = 0; i < addAttchList.size(); i++) {
                    ReqmntAttchUrl reqmntAttchUrl = new ReqmntAttchUrl();
                    reqmntAttchUrl.setReqmntId(reqmntId);
                    reqmntAttchUrl.setAttachmentId(addAttchList.get(i).getAttachmentId());
                    reqmntAttchUrl.setIsDeleted(0);
                    reqmntAttchUrl.setUpdateTime(now);
                    addReqmentAttchList.add(reqmntAttchUrl);
                }
                attchUrlsRepository.save(addReqmentAttchList);


            }


        }


//        List<ReqmntAttchUrl> attchUrls = request.getAttchUrls();
//        if (attchUrls != null) {
//            if (attchUrls.size() == 0) {
//                // 全部删除
//                List<ReqmntAttchUrl> datas = attchUrlsRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
//                for (ReqmntAttchUrl url : datas) {
//                    url.setIsDeleted(1);
//                }
//                attchUrlsRepository.save(datas);
//            } else {
//                for (int i = 0; i < attchUrls.size(); i++) {
//
//                }
//            }
//        }


        // checklist
        List<ReqmntCheckList> checkLists = request.getCheckLists();
        if (checkLists != null && checkLists.size() == 0) {
            List<ReqmntCheckList> deleteList = checkListRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            for (ReqmntCheckList checkList : deleteList) {
                checkList.setIsDeleted(1);
                checkList.setLastUpdateTime(now);
            }
            checkListRepository.save(deleteList);
        } else if (checkLists != null && checkLists.size() > 0) {
            List<ReqmntCheckList> addList = new ArrayList<>();
            List<ReqmntCheckList> oldList = new ArrayList<>();
            oldList = checkListRepository.findAllByReqmntIdAndIsDeleted(reqmntId, 0);
            List<ReqmntCheckList> removeList = oldList;
            for (int i = 0; i < checkLists.size(); i++) {

                // if id is empty, it means we need to add new CheckList
                if (StringUtils.isEmpty(checkLists.get(i).getCheckListId())) {
                    checkLists.get(i).setReqmntId(reqmntId);
                    checkLists.get(i).setCreateTime(now);
                    checkLists.get(i).setLastUpdateTime(now);
                    checkLists.get(i).setIsDeleted(0);
                    checkLists.get(i).setStatus("new");
                    checkLists.get(i).setCreatorId(request.getStaffId());
                    addList.add(checkLists.get(i));
                } else {
                    //if id is not empty, it means we need to update & delete the old CheckList

                    //Delete action: get all check list and compare, if the check list from DB does not exit in request, we need to deleted

//                    oldList = checkListRepository.findAllByReqmntIdAndIsDeleted(reqmntId,0);

                    if (!oldList.isEmpty()) {
                        for (int j = 0; j < oldList.size(); j++) {
                            if (oldList.get(j).getCheckListId().equals(checkLists.get(i).getCheckListId())) {
                                removeList.remove(j);
                            }
                        }
                    }


                    // Update action : get the check list which has check list id
                    List<ReqmntCheckList> items = checkListRepository.findAllByReqmntIdAndCheckListIdAndIsDeleted(reqmntId, checkLists.get(i).getCheckListId(), 0);
                    ReqmntCheckList item = items.get(0);
                    boolean itemChangeFlag = false;
                    if (!item.getdescription().equals(checkLists.get(i).getdescription())) {
                        item.setdescription(checkLists.get(i).getdescription());
                        itemChangeFlag = true;
                    }

                    if (!item.getAssigneeId().equals(checkLists.get(i).getAssigneeId())) {
                        item.setAssigneeId(checkLists.get(i).getAssigneeId());
                        itemChangeFlag = true;
                    }

                    if (itemChangeFlag) {
                        item.setLastUpdateTime(now);
                        checkListRepository.save(item);
                    }

                }

            }

            if (!addList.isEmpty()) {
                List<ReqmntCheckList> updateIdList = checkListRepository.save(addList);
                for (int i = 0; i < updateIdList.size(); i++) {
                    updateIdList.get(i).setCheckListId("RC" + updateIdList.get(i).getUuId());
                }
                checkListRepository.save(updateIdList);
            }

            if (!removeList.isEmpty()) {
                for (int i = 0; i < removeList.size(); i++) {
                    removeList.get(i).setIsDeleted(1);
                    removeList.get(i).setLastUpdateTime(now);
                }
                checkListRepository.save(removeList);
            }


        }


        // memebers

        if (request.getMembers() != null) {
            List<RoleMember> roleMembers = request.getMembers();
            List<ReqmntMember> reqmntMembers = new ArrayList<>();
            ReqmntMember reqmntMember = null;
            String roleId = "";
            for (int i = 0; i < roleMembers.size(); i++) {
                roleId = roleMembers.get(i).getRoleId();
                List<String> memberIds = roleMembers.get(i).getMemberIds();
                for (int j = 0; j < memberIds.size(); j++) {
                    reqmntMember = new ReqmntMember();
                    reqmntMember.setStaffId(memberIds.get(j));
                    reqmntMember.setRoleId(roleId);
                    reqmntMember.setReqmntId(request.getReqmntInfo().getReqmntId());
                    reqmntMember.setIsDeleted(0);
                    reqmntMember.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                    reqmntMembers.add(reqmntMember);
                }

            }

            int tmp = memberRepository.deleteAllByReqmntId(request.getReqmntInfo().getReqmntId());
            memberRepository.save(reqmntMembers);
        }

        result = true;
        return result;
    }

}
