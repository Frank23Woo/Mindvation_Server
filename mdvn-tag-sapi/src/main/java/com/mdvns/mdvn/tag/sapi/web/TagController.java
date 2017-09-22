package com.mdvns.mdvn.tag.sapi.web;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private Tag  tag;


    /**
     *新建标签
     * @param tg
     * @return
     */
    @PostMapping("/saveTag")
    public Tag saveTag(@RequestBody Tag tg ) throws Exception {
        LOG.info("开始执行 createTag 方法.");

        tag =  this.tagService.saveTag(tg);

        LOG.info("执行结束 createTag 方法.");
        return tag;
    }

    /**
     * 获取Tag列表，分页/排序
     */
/*
    @GetMapping(value="/rtrvTagList")
    public List<Tag> rtrvTagList(){

    }*/

}
