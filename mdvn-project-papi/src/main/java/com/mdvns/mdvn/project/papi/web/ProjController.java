package com.mdvns.mdvn.project.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
import com.mdvns.mdvn.project.papi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value= {"/project", "/v1.0/project"})
public class ProjController {

    private Logger LOG = LoggerFactory.getLogger(ProjController.class);

    @Autowired
    private IProjService projService;

    @Autowired
    private RestResponse restResponse;
    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvProjInfoList")
    public ResponseEntity<?> rtrvProjInfoList(@RequestBody @Validated RtrvProjectListRequest rtrvProjectListRequest, BindingResult bindingResult) throws BindException{
        LOG.info("开始执行 rtrvProjInfoList 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 rtrvProjInfoList 方法.");
        return this.projService.rtrvProjInfoList(rtrvProjectListRequest);
    }

    /**
     * 创建项目
     * @param createProjectRequest
     * @return
     */
    @PostMapping(value="/createProject")
    public RestResponse createProject(@RequestBody @Validated CreateProjectRequest createProjectRequest, BindingResult bindingResult) throws BindException{
        LOG.info("开始执行 createProject 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 createProject 方法.");
        return projService.createProject(createProjectRequest);
    }

    /**
     * 更改项目
     * @param updateProjectDetailRequest
     * @return
     */
    @PostMapping(value="/updateProject")
    public RestResponse updateProject(@RequestBody @Validated UpdateProjectDetailRequest updateProjectDetailRequest,BindingResult bindingResult){
        LOG.info("开始执行 updateProject 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确:"+fieldError.getDefaultMessage());
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 updateProject 方法.");
        return projService.updateProject(updateProjectDetailRequest);
    }

    /**
     * 获取某个项目详细信息
     * @param rtrvProjectDetailRequest
     * @return
     */
    @PostMapping(value="/rtrvProjectInfo")
    public RestResponse rtrvProjectInfo(@RequestBody @Validated RtrvProjectDetailRequest rtrvProjectDetailRequest,BindingResult bindingResult){
        LOG.info("开始执行 rtrvProjectInfo 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 rtrvProjectInfo 方法.");
        return projService.rtrvProjectInfo(rtrvProjectDetailRequest);
    }

}
