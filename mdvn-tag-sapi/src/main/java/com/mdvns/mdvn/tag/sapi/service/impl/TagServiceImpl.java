package com.mdvns.mdvn.tag.sapi.service.impl;


import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.repository.TagRepository;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class TagServiceImpl implements TagService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    private final String CLASS = this.getClass().getName();
    /*Tag Repository*/
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Tag tg;

    /* 保存标签对象到数据库 */
    @Override
    public Tag saveTag(Tag tag) throws Exception, SQLException {
        LOG.info("开始执行{} createTag()方法.", this.CLASS);

        if(tag.getTagId()!=null){
            LOG.error("此ID的标签已存在.");
            throw new Exception("此ID的标签已存在.");
        }
        tag.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tg = this.tagRepository.save(tag);

        LOG.info("执行结束{} createTag()方法.", this.CLASS);
        return tg;
    }

}
