package com.mdvns.mdvn.tag.sapi.web;

import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * 标签SAPI控制层
 */

@RestController
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
    @PostMapping("/tags/tag")
    public ResponseEntity<?> saveTag(@RequestBody Tag tg) throws SQLException {
        LOG.info("开始执行 createTag 方法.");
        return this.tagService.saveTag(tg);
    }

    /**
     * 获取Tag列表，分页/排序
     */

    @PostMapping(value = "/tags")
    public ResponseEntity<Page<Tag>> rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) throws SQLException {

        return this.tagService.rtrvTagList(retrieveTagListRequest);
    }

    /**
     * 根据名称查询标签
     *
     * @param name 标签名称
     * @return Tag
     */
    @PostMapping(value = "/tags/tag/{name}")
    public ResponseEntity<Tag> findByName(@PathVariable String name) {
        return this.tagService.findByName(name);
    }

    /**
     * 根据指定的ID给quoteCnt值+1
     *
     * @param tagId
     * @return
     */
    @PostMapping(value = "/tags/{tagId}")
    public ResponseEntity<Tag> updateQuoteCnt(@PathVariable String tagId) {
        return this.tagService.updateQupteCnt(tagId);
    }


}
