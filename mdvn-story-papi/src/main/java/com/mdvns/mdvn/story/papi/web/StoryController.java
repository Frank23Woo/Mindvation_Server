package com.mdvns.mdvn.story.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.story.papi.domain.CreateStoryRequest;
import com.mdvns.mdvn.story.papi.domain.RtrvStoryDetailRequest;
import com.mdvns.mdvn.story.papi.domain.RtrvStoryListRequest;
import com.mdvns.mdvn.story.papi.domain.UpdateStoryDetailRequest;
import com.mdvns.mdvn.story.papi.service.IStoryService;
import com.mdvns.mdvn.story.papi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/story", "/v1.0/story"})
public class StoryController {

    private Logger LOG = LoggerFactory.getLogger(StoryController.class);

    @Autowired
    private IStoryService storyService;

    @Autowired
    private RestResponse restResponse;
    /**
     * 获取story整个列表
     * @return
     */
    @PostMapping(value="/rtrvStoryInfoList")
    public ResponseEntity<?> rtrvStoryInfoList(@RequestBody @Validated RtrvStoryListRequest rtrvStoryListRequest, BindingResult bindingResult) throws BindException{
        LOG.info("开始执行 rtrvStoryInfoList 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 rtrvStoryInfoList 方法.");
        return this.storyService.rtrvStoryInfoList(rtrvStoryListRequest);
    }

    /**
     * 创建用户故事
     * @param createStoryRequest
     * @return
     */
    @PostMapping(value="/createStory")
    public RestResponse createStory(@RequestBody @Validated CreateStoryRequest createStoryRequest, BindingResult bindingResult) throws BindException{
        LOG.info("开始执行 createStory 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 createStory 方法.");
        return storyService.createStory(createStoryRequest);
    }

    /**
     * 更改用户故事
     * @param updateStoryDetailRequest
     * @return
     */
    @PostMapping(value="/updateStory")
    public RestResponse updateStory(@RequestBody @Validated UpdateStoryDetailRequest updateStoryDetailRequest, BindingResult bindingResult){
        LOG.info("开始执行 updateStory 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 updateStory 方法.");
        return storyService.updateStory(updateStoryDetailRequest);
    }

    /**
     * 获取某个用户故事详细信息
     * @param rtrvStoryDetailRequest
     * @return
     */
    @PostMapping(value="/rtrvStoryInfo")
    public RestResponse rtrvStoryInfo(@RequestBody @Validated RtrvStoryDetailRequest rtrvStoryDetailRequest, BindingResult bindingResult){
        LOG.info("开始执行 rtrvStoryInfo 方法.");
        if (bindingResult.hasErrors()) {
            //获取对象给出的message信息
            FieldError fieldError= bindingResult.getFieldError();
            LogUtil.errorLog("请求参数不正确");
            ExceptionEnum.REQUEST_NOT_VALID.setErrorMsg(fieldError.getDefaultMessage());
            throw new BusinessException(ExceptionEnum.REQUEST_NOT_VALID);
        }
        LOG.info("执行结束 rtrvStoryInfo 方法.");
        return storyService.rtrvStoryInfo(rtrvStoryDetailRequest);
    }

}
