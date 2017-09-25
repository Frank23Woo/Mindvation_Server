package com.mdvns.mdvn.tag.papi.web;

import com.mdvns.mdvn.tag.papi.domain.*;
import com.mdvns.mdvn.tag.papi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*@Author: Migan Wang
*@Description: 标签模块Controller
*@Date:
*/
@RestController
public class TagController {

    private Logger LOG = LoggerFactory.getLogger(TagController.class);

    /*注入TagService*/
    @Autowired
    private TagService tagService;

    /*注入CreateTagResponse*/
    @Autowired
    private CreateTagResponse createTagResponse;

    /**
     * 新建标签
     *
     * @param request
     * @return
     */
    @PostMapping("/createTag")
    public CreateTagResponse createTag(@RequestBody CreateTagRequest request) {
        LOG.info("开始执行 createTag 方法.");
        createTagResponse = this.tagService.createTag(request);
        LOG.info("执行结束 createTag 方法.");
        return createTagResponse;
    }


    /**
     * 获取标签列表
     * @param retrieveTagListRequest
     * @return
     */
    @PostMapping(value = "/rtrvTagList")
    public RetrieveTagListResponse rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) {

       return this.tagService.rtrvTagList(retrieveTagListRequest);
    }

}
