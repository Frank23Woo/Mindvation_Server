package com.mdvns.mdvn.tag.sapi.web;

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
<<<<<<< HEAD
    public ResponseEntity<?> saveTag(@RequestBody Tag tg) throws SQLException {
        LOG.info("开始执行 createTag 方法.");
        return this.tagService.saveTag(tg);
=======
    public ResponseEntity<?> saveTag(@RequestBody Tag tg) {
        LOG.info("开始执行 createTag 方法.");

        RestDefaultResponse restDefaultResponse = null;

        return this.tagService.saveTag(tg);

//        LOG.info("执行结束 createTag 方法.");
//        return restDefaultResponse;
    }

    /**
     * 通过uuId获取项目的TagId(触发器引发的问题)
     * @param tag
     * @return
     */
    @PostMapping(value="/getTagIdByUuId")
    public Tag getTagIdByUuId(@RequestBody Tag tag){
        Tag pro = tagService.getTagIdByUuId(tag);
        return pro;
>>>>>>> 29a5f170fe0b2f4b8c1402144187839255cad32b
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
