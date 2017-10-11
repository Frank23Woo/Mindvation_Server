package com.mdvns.mdvn.reqmnt.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.reqmnt.papi.config.ReqmntConfig;
import com.mdvns.mdvn.reqmnt.papi.domain.*;
import com.mdvns.mdvn.reqmnt.papi.service.IReqmntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReqmntServiceImpl implements IReqmntService {

    private static final Logger LOG = LoggerFactory.getLogger(ReqmntServiceImpl.class);

    @Autowired
    private ReqmntConfig config;

    @Autowired
    private ReqirementInfo reqirementInfo;

    @Autowired
    private RestResponse restResponse;

    @Autowired
    private RestTemplate restTemplate;



//    /**
//     * 获取project列表详细信息
//     *
//     * @param rtrvProjectListRequest
//     * @return
//     */
//    public ResponseEntity<?> rtrvProjInfoList(RtrvProjectListRequest rtrvProjectListRequest) {
//        rtrvProjectListRequest.setPage(rtrvProjectListRequest.getPage()-1);
//        String projInfoListUrl = config.getRtrvProjInfoListUrl();
//        restResponse = this.restTemplate.postForObject(projInfoListUrl, rtrvProjectListRequest, restResponse.class);
//        ResponseEntity<restResponse> responseEntity = null;
//        if (restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
////            HttpHeaders httpHeaders = new HttpHeaders();
////            httpHeaders.setAccessControlAllowOrigin("*");
//            responseEntity = new ResponseEntity<restResponse>(restResponse,HttpStatus.OK);
//            return responseEntity;
//        }
//        throw new BusinessException(restResponse.getResponseCode(), restResponse.getResponseBody().toString());
//    }

    /**
     * 调用sapi创建project
     * 1.创建保存project信息
     * 2.返回Project整个信息
     *
     * @param createReqmntRequest
     * @return
     */

    @Override
    public RestResponse createReqmnt(CreateReqmntRequest createReqmntRequest) {
        CreateReqmntResponse createReqmntResponse = new CreateReqmntResponse();

        //1.先保存requirement基本信息（获取reqmntId）

        if (createReqmntRequest == null || StringUtils.isEmpty(createReqmntRequest.getCreatorId()) ||
                StringUtils.isEmpty(createReqmntRequest.getSummary()) || StringUtils.isEmpty(createReqmntRequest.getDescription()) || StringUtils.isEmpty(createReqmntRequest.getFunctionLabelId())
                ) {
            throw new NullPointerException("Mandatory fields should not be empty for createReqmntRequest");
        }


        String saveReqmntUrl = config.getSaveReqmntUrl();
        ResponseEntity<ReqirementInfo> responseEntity = null;
        responseEntity = restTemplate.postForEntity(saveReqmntUrl, createReqmntRequest, ReqirementInfo.class);
        restResponse.setResponseBody(responseEntity.getBody());
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        reqirementInfo = responseEntity.getBody();


        //2.保存requirement member信息


        if (createReqmntRequest.getMembers()!=null && !createReqmntRequest.getMembers().isEmpty()) {
            List<ReqmntMember> reqmntMembers = createReqmntRequest.getMembers();
            for (int i = 0; i < reqmntMembers.size(); i++) {
                reqmntMembers.get(i).setReqmntId(reqirementInfo.getRqmntId());
            }
            String saveRMembersUrl = config.getSaveRMembersUrl();
            try {
                reqmntMembers = restTemplate.postForObject(saveRMembersUrl, reqmntMembers, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目负责人信息保存数据失败.");
            }
        }





        //3.保存requirement标签信息
        if (createReqmntRequest.getTags()!=null && !createReqmntRequest.getTags().isEmpty()) {
            List<ReqmntTag> reqmntTags = createReqmntRequest.getTags();
            for (int i = 0; i < reqmntTags.size(); i++) {
                reqmntTags.get(i).setReqmntId(reqirementInfo.getRqmntId());
            }
            String saveRTagsUrl = config.getSaveRTagsUrl();
            try {
                List<ReqmntTag> rTags = restTemplate.postForObject(saveRTagsUrl, reqmntTags, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目标签信息保存数据失败.");
            }
        }
        //4.保存项目模块信息

        //5.保存项目checklist信息
        if (createReqmntRequest.getrCheckLists()!=null && !createReqmntRequest.getrCheckLists().isEmpty()) {
            SaveRCheckListsRequest saveRCheckListsRequest = new SaveRCheckListsRequest();
            List<ReqmntChecklist> reqmntChecklists = createReqmntRequest.getrCheckLists();
            for (int i = 0; i < reqmntChecklists.size(); i++) {
                reqmntChecklists.get(i).setReqmntId(reqirementInfo.getRqmntId());
                reqmntChecklists.get(i).setAssignerId(createReqmntRequest.getCreatorId());
            }
            saveRCheckListsRequest.setStaffId(createReqmntRequest.getCreatorId());
            saveRCheckListsRequest.setCheckLists(reqmntChecklists);
            String saveCheckListsUrl = config.getSaveRCheckListUrl();
            try {
                 reqmntChecklists = restTemplate.postForObject(saveCheckListsUrl, saveRCheckListsRequest, List.class);

            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目checklist信息保存数据失败.");
            }
        }
        //6.保存requirement附件信息
        if (createReqmntRequest.getAttchUrls()!=null && !createReqmntRequest.getAttchUrls().isEmpty()) {
            List<ReqmntAttchUrl> reqmntAttchUrls = createReqmntRequest.getAttchUrls();
            for (int i = 0; i < reqmntAttchUrls.size(); i++) {
                reqmntAttchUrls.get(i).setReqmntId(reqirementInfo.getRqmntId());
            }
            String saveRAttchUrl = config.getSaveRAttchUrl();
            try {
                List<ReqmntAttchUrl> rAttchUrls = restTemplate.postForObject(saveRAttchUrl, reqmntAttchUrls, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目附件信息保存数据失败.");
            }
        }
        //response
//        if (restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
            return restResponse;
//        }
//        throw new BusinessException(restResponse.getResponseCode(), restResponse.getResponseBody().toString());
    }

//    /**
//     * 调用sapi更改project
//     *
//     * @param updateProjectDetailRequest
//     * @return
//     */
//    @Override
//    public restResponse updateProject(UpdateProjectDetailRequest updateProjectDetailRequest) {
//        UpdateProjectDetailResponse updateProjectDetailResponse = new UpdateProjectDetailResponse();
//        ProjectDetail projectDetail = new ProjectDetail();
//        RestTemplate restTemplate = new RestTemplate();
////        String projId = updateProjectRequest.getProjId();
//        if (updateProjectDetailRequest == null || updateProjectDetailRequest.getProjId() == null) {
//            throw new NullPointerException("updateProjectRequest 或项目Id不能为空");
//        }
//        //1.先判断是否更改项目基本信息
//        proj.setProjId(updateProjectDetailRequest.getProjId());
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getName()) ) {
//            proj.setName(updateProjectDetailRequest.getName());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getDescription())) {
//            proj.setDescription(updateProjectDetailRequest.getDescription());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStartDate())) {
//            proj.setStartDate(updateProjectDetailRequest.getStartDate());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getEndDate())) {
//            proj.setEndDate(updateProjectDetailRequest.getEndDate());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getPriority())) {
//            proj.setPriority(updateProjectDetailRequest.getPriority());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getContingency())) {
//            proj.setContingency(updateProjectDetailRequest.getContingency());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStatus())) {
//            proj.setStatus(updateProjectDetailRequest.getStatus());
//
//        }
//        //之后ragStatus需要后台计算以后传给前台
////        if (!StringUtils.isEmpty(updateProjectDetailRequest.getRagStatus())) {
////            proj.setRagStatus(updateProjectDetailRequest.getRagStatus());
////        }
//        String updateProjBaseInfoUrl = config.getUpdateProjBaseInfoUrl();
//        ReqirementInfo pro = restTemplate.postForObject(updateProjBaseInfoUrl, proj, ReqirementInfo.class);
//        projectDetail.setProject(pro);
//        //2.判断是否更改项目负责人信息
//        if (updateProjectDetailRequest.getLeaders()!=null && !updateProjectDetailRequest.getLeaders().isEmpty()) {
//            UpdatePLeadersRequest updatePLeadersRequest = new UpdatePLeadersRequest();
//            updatePLeadersRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePLeadersRequest.setLeaders(updateProjectDetailRequest.getLeaders());
//            String updateProjLeadersUrl = config.getUpdateProjLeadersUrl();
//            try {
//                List<Staff> pLeaders = restTemplate.postForObject(updateProjLeadersUrl, updatePLeadersRequest, List.class);
//                projectDetail.setLeaders(pLeaders);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目负责人信息保存数据失败.");
//            }
//        }
//        //3.判断是否更改项目标签信息
//        if (updateProjectDetailRequest.getTags()!=null && !updateProjectDetailRequest.getTags().isEmpty()) {
//            UpdatePTagsRequest updatePTagsRequest = new UpdatePTagsRequest();
//            updatePTagsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePTagsRequest.setTags(updateProjectDetailRequest.getTags());
//            String updateProjTagsUrl = config.getUpdateProjTagsUrl();
//            try {
//                List<Tag> pTags = restTemplate.postForObject(updateProjTagsUrl, updatePTagsRequest, List.class);
//                projectDetail.setTags(pTags);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目标签信息保存数据失败.");
//            }
//        }
//
//        //4.判断是否更改项目模块信息
//        if (updateProjectDetailRequest.getModels()!=null && !updateProjectDetailRequest.getModels().isEmpty()) {
//            UpdatePModelsRequest updatePModelsRequest = new UpdatePModelsRequest();
//            updatePModelsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePModelsRequest.setModels(updateProjectDetailRequest.getModels());
//            String updateProjModelsUrl = config.getUpdateProjModelsUrl();
//            try {
//                List<Model> pModels = restTemplate.postForObject(updateProjModelsUrl, updatePModelsRequest, List.class);
//                projectDetail.setModels(pModels);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目模块信息保存数据失败.");
//            }
//
//        }
//
//        //5.判断是否更改项目checklist信息
//        if (updateProjectDetailRequest.getCheckLists()!=null && !updateProjectDetailRequest.getCheckLists().isEmpty()) {
//            UpdatePCheckListsRequest updatePCheckListsRequest = new UpdatePCheckListsRequest();
//            updatePCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePCheckListsRequest.setStaffId(updateProjectDetailRequest.getStaffId());
//            updatePCheckListsRequest.setCheckLists(updateProjectDetailRequest.getCheckLists());
//            String updateProjChecklistsUrl = config.getUpdateProjChecklistsUrl();
//            try {
//                List<ReqmntChecklist> pChecklists = restTemplate.postForObject(updateProjChecklistsUrl, updatePCheckListsRequest, List.class);
//                //通过UUid遍历保存checklistId
//                UpdatePCheckListsRequest pCheckListsRequest = new UpdatePCheckListsRequest();
//                pCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
//                pCheckListsRequest.setCheckLists(pChecklists);
//                String checklistsListByUuIdUrl = config.getChecklistsListByUuIdUrl();
//                try {
//                    List<ProjChecklistsDetail> checklists = restTemplate.postForObject(checklistsListByUuIdUrl, pCheckListsRequest, List.class);
//                    projectDetail.setCheckLists(checklists);
//                } catch (Exception ex) {
//                    throw new RuntimeException("调用SAPI获取项目checklistId信息保存数据失败.");
//                }
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目checklist信息保存数据失败.");
//            }
//        }
//
//        //6.判断是否更改项目附件信息
//        if (updateProjectDetailRequest.getAttchUrls()!=null && !updateProjectDetailRequest.getAttchUrls().isEmpty()) {
//            UpdatePAttchUrlsRequest updatePAttchUrlsRequest = new UpdatePAttchUrlsRequest();
//            updatePAttchUrlsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePAttchUrlsRequest.setAttchUrls(updateProjectDetailRequest.getAttchUrls());
//            String updateProjAttchUrlsUrl = config.getUpdateProjAttchUrlsUrl();
//            try {
//                List<ReqmntAttchUrl> pAttchUrls = restTemplate.postForObject(updateProjAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
//                projectDetail.setAttchUrls(pAttchUrls);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目附件信息保存数据失败.");
//            }
//        }
//
//        updateProjectDetailResponse.setProjectDetail(projectDetail);
//        restResponse.setResponseBody(updateProjectDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }

//    /**
//     * 获取某个项目详细信息
//     * @param rtrvProjectDetailRequest
//     * @return
//     */
//    @Override
//    public restResponse rtrvProjectInfo(RtrvProjectDetailRequest rtrvProjectDetailRequest) {
//        RtrvProjectDetailResponse rtrvProjectDetailResponse = new RtrvProjectDetailResponse();
//        ProjectDetail projectDetail = new ProjectDetail();
//        if (rtrvProjectDetailRequest == null || rtrvProjectDetailRequest.getProjId() == null) {
//            throw new NullPointerException("rtrvProjectDetailRequest 或项目Id不能为空");
//        }
//        //1.先获取项目基本信息
//        String rtrvProjBaseInfoUrl = config.getRtrvProjBaseInfoUrl();
//        proj = restTemplate.postForObject(rtrvProjBaseInfoUrl, rtrvProjectDetailRequest, ReqirementInfo.class);
//        if (null == proj) {
//            LOG.error("获取项目基本信息不存在.");
//            throw new BusinessException("获取项目基本信息不存在.");
//        }
//        projectDetail.setProject(proj);
//        //2.获取项目负责人信息
//        String rtrvProjLedersUrl = config.getRtrvProjLedersUrl();
//        List<Staff> projLeaders = restTemplate.postForObject(rtrvProjLedersUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setLeaders(projLeaders);
//        //3.获取项目标签信息
//        String rtrvProjTagsUrl = config.getRtrvProjTagsUrl();
//        List<Tag> projTags = restTemplate.postForObject(rtrvProjTagsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setTags(projTags);
//        //4.获取项目模型信息
//        String rtrvProjModelsUrl = config.getRtrvProjModelsUrl();
//        List<Model> projModels = restTemplate.postForObject(rtrvProjModelsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setModels(projModels);
//        //5.获取项目checklist信息
//        String rtrvProjCheckListsUrl = config.getRtrvProjCheckListsUrl();
//        List<ProjChecklistsDetail> projChecklists = restTemplate.postForObject(rtrvProjCheckListsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setCheckLists(projChecklists);
//        //6.获取项目附件信息
//        String rtrvProjAttUrlsUrl = config.getRtrvProjAttUrlsUrl();
//        List<ReqmntAttchUrl> reqmntAttchUrls = restTemplate.postForObject(rtrvProjAttUrlsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setAttchUrls(reqmntAttchUrls);
//
//        rtrvProjectDetailResponse.setProjectDetail(projectDetail);
//        restResponse.setResponseBody(rtrvProjectDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }
}