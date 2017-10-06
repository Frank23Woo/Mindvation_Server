package com.mdvns.mdvn.project.papi.web;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
import com.mdvns.mdvn.project.papi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/project", "/v1.0/project"})
public class ProjController {

    private Logger LOG = LoggerFactory.getLogger(ProjController.class);

    @Autowired
    private IProjService projService;
    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvProjInfoList")
    public ResponseEntity<?> rtrvProjInfoList(@RequestBody @Validated RtrvProjectRequest rtrvProjectRequest, BindingResult bindingResult) throws BindException{
        LOG.info("开始执行 rtrvProjInfoList 方法.");
        if (bindingResult.hasErrors()) {
            LogUtil.errorLog("请求参数不正确");
            throw new BindException(bindingResult);
        }
        LOG.info("执行结束 rtrvProjInfoList 方法.");
        return this.projService.rtrvProjInfoList(rtrvProjectRequest);
    }

    /**
     * 创建项目
     * @param createProjectRequest
     * @return
     */
    @PostMapping(value="/createProject")
    public RestDefaultResponse createProject(@RequestBody CreateProjectRequest createProjectRequest){
        return projService.createProject(createProjectRequest);
    }

    /**
     * 更改项目
     * @param updateProjectRequest
     * @return
     */
    @PostMapping(value="/updateProject")
    public RestDefaultResponse updateProject(@RequestBody UpdateProjectRequest updateProjectRequest){
        return projService.updateProject(updateProjectRequest);
    }

    /**
     * 获取某个项目详细信息
     * @param rtrvProjectDetailRequest
     * @return
     */
    @PostMapping(value="/rtrvProjectInfo")
    public RestDefaultResponse rtrvProjectInfo(@RequestBody RtrvProjectDetailRequest rtrvProjectDetailRequest){
        return projService.rtrvProjectInfo(rtrvProjectDetailRequest);
    }

}
