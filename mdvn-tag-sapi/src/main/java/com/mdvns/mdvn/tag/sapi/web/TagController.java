package com.mdvns.mdvn.tag.sapi.web;

import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 标签控制层
 */

@RestController
public class TagController {

    private Logger LOG = LoggerFactory.getLogger(TagController.class);

    /*标签service注入*/
    @Autowired
    private TagService tagService;


    /*注入Tag*/
    private Tag tag;


    /**
     * 新建标签
     *
     * @param tg
     * @return
     */
    @PostMapping("/tags/tag")
    public Tag saveTag(@RequestBody Tag tg) throws Exception {
        LOG.info("开始执行 createTag 方法.");

        tag = this.tagService.saveTag(tg);

        LOG.info("执行结束 createTag 方法.");
        return tag;
    }

    /**
     * 获取Tag列表，分页/排序
     */
   /* @PostMapping(value="/tags")
    public Page<Tag> rtrvTagList(@RequestParam Integer page, @RequestParam Integer pageSize ){
       return this.tagService.rtrvTagList(page, pageSize);
    }*/
    @PostMapping(value = "/tags")
    public List<Tag> rtrvTagList(@RequestBody RetrieveTagListRequest retrieveTagListRequest) throws SQLException {

        return this.tagService.rtrvTagList(retrieveTagListRequest);

    }

    /**
     * 根据名称查询标签
     *
     * @param name 标签名称
     * @return Tag
     */
    @GetMapping(value = "/tags/{name}")
    public Tag findByName(@PathVariable String name) {
        return this.tagService.findByName(name);
    }

    /**
     * 根据指定的ID给quoteCnt值+1
     *
     * @param tagId
     * @return
     */
    @PatchMapping(value = "/tags/{tagId}")
    public Tag updateQuoteCnt(@PathVariable Integer tagId) {
        return this.tagService.updateQupteCnt(tagId);
    }


}
