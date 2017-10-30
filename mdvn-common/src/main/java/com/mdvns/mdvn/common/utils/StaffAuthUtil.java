package com.mdvns.mdvn.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RemoveAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvStaffAuthInfoRequest;
import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;

/**
 * 权限工具类
 * 
 * @author Administrator
 *
 */
public class StaffAuthUtil {
	private static final Logger LOG = LoggerFactory.getLogger(StaffAuthUtil.class);

	/**
	 * 分配权限
	 * 
	 * @param restTemplate
	 * @param assignAuthUrl
	 * @param assignAuthRequest
	 * @return
	 */
	public static ResponseEntity<?> assignAuth(RestTemplate restTemplate, AssignAuthRequest assignAuthRequest) {
		ResponseEntity<StaffAuthInfo[]> responseEntity = null;
		String assignAuthUrl = "http://localhost:10014/mdvn-staff-papi/staff/assignAuth";
		try {
			responseEntity = restTemplate.postForEntity(assignAuthUrl, assignAuthRequest, StaffAuthInfo[].class);
		} catch (Exception ex) {
			LOG.error("添加权限失败:{}", ex.getLocalizedMessage());
			throw new BusinessException(ExceptionEnum.UNKNOW_EXCEPTION);
		}
		LOG.info("添加权限完成：{}", responseEntity.getBody().toString());
		return responseEntity;
	}

	/**
	 * 获取员工在项目中的权限信息
	 * 
	 * @param restTemplate
	 * @param rtrvStaffAuthUrl
	 * @param rtrvAuthRequest
	 * @return
	 */
	public static StaffAuthInfo rtrvStaffAuthInfo(RestTemplate restTemplate, String projId, String hierarchyId,
			String staffId) {
		String rtrvStaffAuthUrl = "http://localhost:10014/mdvn-staff-papi/staff/rtrvAuth";
		RtrvStaffAuthInfoRequest rtrvStaffAuthInfoRequest = new RtrvStaffAuthInfoRequest();
		rtrvStaffAuthInfoRequest.setProjId(projId);
		rtrvStaffAuthInfoRequest.setStaffId(staffId);
		rtrvStaffAuthInfoRequest.setHierarchyId(hierarchyId);
		ResponseEntity<StaffAuthInfo> responseEntity = restTemplate.postForEntity(rtrvStaffAuthUrl,
				rtrvStaffAuthInfoRequest, StaffAuthInfo.class);

		StaffAuthInfo staffAuthInfo = null;
		if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
			staffAuthInfo = responseEntity.getBody();
		}
		return staffAuthInfo;
	}

	/**
	 * 取消权限
	 * 
	 * @param restTemplate
	 * @param removeAuthRequest
	 * @return
	 */
	public static ResponseEntity<?> removeAuth(RestTemplate restTemplate, RemoveAuthRequest removeAuthRequest) {
		String removeAuthUrl = "";
		ResponseEntity<StaffAuthInfo> responseEntity = restTemplate.postForEntity(removeAuthUrl, removeAuthRequest,
				StaffAuthInfo.class);
		if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
			throw new BusinessException(ExceptionEnum.UNKNOW_EXCEPTION);
		}
		return responseEntity;
	}

	/**
	 * 给创建者添加权限
	 * 
	 * @param restTemplate
	 * @param projId
	 * @param creatorId
	 * @param hierarchyId
	 * @param authCode
	 * @return
	 */
	public static ResponseEntity<?> assignAuthForCreator(RestTemplate restTemplate, String projId, String creatorId,
			String hierarchyId, Integer authCode) {
		AssignAuthRequest assignAuthRequest = new AssignAuthRequest();
		assignAuthRequest.setProjId(projId);
		assignAuthRequest.setAssignerId(creatorId);
		List<String> assignees = new ArrayList<String>();
		assignees.add(creatorId);
		assignAuthRequest.setAssignees(assignees);
		assignAuthRequest.setHierarchyId(hierarchyId);
		assignAuthRequest.setAuthCode(authCode);
		return assignAuth(restTemplate, assignAuthRequest);
	}

}
