package com.mdvns.mdvn.tag.sapi.web;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * 标签SAPI控制层
 */

@RestController
@RequestMapping(value = {"/tags", "/v1.0/tags"})
public class TagController {

    private Logger LOG = LoggerFactory.getLogger(TagController.class);

    /*标签service注入*/
    @Autowired
    private TagService tagService;

    /**
     * 新建标签
     *
     * @param tg
     * @return
     */
    @PostMapping("/tag")
    public ResponseEntity<?> saveTag(@RequestBody Tag tg) throws SQLException {
        LOG.info("开始执行 createTag 方法.");
        return this.tagService.saveTag(tg);
    }

    /**
     * 获取Tag列表，分页/排序
     */

//    @PostMapping(value = "/tags")
//    public ResponseEntity<?> rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) throws SQLException {
//
//        return this.tagService.rtrvTagList(retrieveTagListRequest);
//    }

    @PostMapping(value = "/tagList")
    public ResponseEntity<?> rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) throws SQLException, BusinessException {

        Integer page = retrieveTagListRequest.getPage();
        Integer pageSize = retrieveTagListRequest.getPageSize();
        if (null==page||pageSize==null) {
            return this.tagService.rtrvTagList();
        }
//        page, pageSize不能小于1
        if (page<1||pageSize<1) {
            LOG.error("获取标签列表，分页参数不正确!");
            throw new IllegalArgumentException("分页参数不正确.");
        }
        return this.tagService.rtrvTagList((page-1), pageSize, retrieveTagListRequest.getSortBy());
    }


    /**
     * 根据名称查询标签
     *
     * @param name 标签名称
     * @return Tag
     */
    @PostMapping(value = "/tag/{name}")
    public ResponseEntity<Tag> findByName(@PathVariable String name) {
        return this.tagService.findByName(name);
    }

    @PostMapping(value = "/{tagId}/tag")
    public ResponseEntity<?> findById(@PathVariable String tagId) {
        return this.tagService.findById(tagId);
    }
    /**
     * 根据指定的ID给quoteCnt值+1
     *
     * @param tagId
     * @return
     */
    @PostMapping(value = "/{tagId}")
    public ResponseEntity<Tag> updateQuoteCnt(@PathVariable String tagId) {
        return this.tagService.updateQupteCnt(tagId);
    }


}
