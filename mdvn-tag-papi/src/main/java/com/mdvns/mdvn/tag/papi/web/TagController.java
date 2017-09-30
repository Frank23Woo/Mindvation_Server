package com.mdvns.mdvn.tag.papi.web;


import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.tag.papi.domain.*;
import com.mdvns.mdvn.tag.papi.service.TagService;
import com.mdvns.mdvn.tag.papi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Migan Wang
 * @Description: 标签模块Controller
 * @Date:
 */
@RestController
@RequestMapping(value = {"", "/v0.1"})
public class TagController {

    private Logger LOG = LoggerFactory.getLogger(TagController.class);

    /*注入TagService*/
    @Autowired
    private TagService tagService;

    /*注入CreateTagResponse*/
    @Autowired
    private CreateTagResponse createTagResponse;

    @Autowired
    private RestDefaultResponse restDefaultResponse;


    /**
     * 新建标签
     *
     * @param request
     * @return
     */
    @PostMapping("/createTag")
    public RestDefaultResponse createTag(@RequestBody @Validated CreateTagRequest request, BindingResult bindingResult) throws BindException {
        LOG.info("开始执行 createTag 方法.");
        if (bindingResult.hasErrors()) {
            LogUtil.errorLog("请求参数不正确");
            throw new BindException(bindingResult);
        }
        restDefaultResponse = this.tagService.createTag(request);
        LOG.info("执行结束 createTag 方法.");
        return restDefaultResponse;
    }


    /**
     * 获取标签列表
     *
     * @param retrieveTagListRequest
     * @return
     */
    @PostMapping(value = "/rtrvTagList")
    public RetrieveTagListResponse rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) {

        return this.tagService.rtrvTagList(retrieveTagListRequest);
    }

    @PostMapping(value = "/updateQuoteCnt")
    public Tag updateQuoteCnt(@RequestBody UpdateQuoteCntRequest updateQuoteCntRequest) {
        return this.tagService.updateQuoteCnt(updateQuoteCntRequest);
    }

}
