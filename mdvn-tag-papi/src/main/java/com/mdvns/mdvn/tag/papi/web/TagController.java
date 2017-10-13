package com.mdvns.mdvn.tag.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.tag.papi.domain.*;
import com.mdvns.mdvn.tag.papi.service.TagService;
import com.mdvns.mdvn.tag.papi.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/tag", "/v1.0/tag"})
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private Tag tag;

    /**
     * 新建标签
     *
     * @param createTagRequest
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @PostMapping(value = "/createTag")
    public ResponseEntity<?> createTag(@RequestBody @Validated CreateTagRequest createTagRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            LogUtil.errorLog("请求参数不正确");
            throw new BindException(bindingResult);
        }
        return this.tagService.createTag(createTagRequest);
    }

    /**
     * 更新Tag引用次数
     *
     * @param updateQuoteCntRequest
     * @return
     */
    @PostMapping(value = "/updateQupteCnt")
    public ResponseEntity<?> updateQuoteCnt(@RequestBody UpdateQuoteCntRequest updateQuoteCntRequest) {
        String tagId = updateQuoteCntRequest.getTagId();
        if (StringUtils.isEmpty(tagId)) {
            LogUtil.errorLog("tagId is empty.");
            throw new NullPointerException("tagId 不能为空.");
        }

        return this.tagService.updateQuoteCnt(tagId);
    }

    /**
     * 根据名称查询标签
     * @param retrieveTagRequest
     * @return
     */
    @PostMapping(value = "/findByName")
    public ResponseEntity<?> findByName(@RequestBody RetrieveTagRequest retrieveTagRequest) {

        return this.tagService.findByName(retrieveTagRequest.getName());
    }

    /**
     * 获取标签列表
     * @param retrieveTagListRequest
     * @return
     */
    @PostMapping(value = "/rtrvTagList")
    public RestResponse rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) {
        return this.tagService.rtrvTagList(retrieveTagListRequest);
    }




    /**
     * 根据Id删除Tag
     *
     * @param tagId
     * @return
     */
    @PostMapping(value = "/deleteTag")
    public Integer deleteTag(String tagId) {

        return null;
    }

}
