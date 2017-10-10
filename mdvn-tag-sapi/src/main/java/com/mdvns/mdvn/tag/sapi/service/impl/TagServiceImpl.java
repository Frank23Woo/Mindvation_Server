package com.mdvns.mdvn.tag.sapi.service.impl;


import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.repository.TagRepository;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @Author: Migan Wang
 * @Description: Tag sapi业务处理
 * @Date:
 */
@Service
public class TagServiceImpl implements TagService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    private final String CLASS = this.getClass().getName();
    /*Tag Repository*/
    @Autowired
    private TagRepository tagRepository;


    @Autowired
    private Tag tag;


    /**
     * @param tg
     * @return Tag
     * @desc: 保存新建标签
     * 1.tagId 为null;
     * 2.校验name对应的Tag是否已存在
     */
    @Override
    public ResponseEntity<?> saveTag(Tag tg) throws SQLException {
        LOG.info("开始执行{} createTag()方法.", this.CLASS);
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        tg.setCreateTime(createTime);
        tg.setQuoteCnt(0);
        tg.setIsDeleted(0);
        //数据保存后tagId没有生成
        tag = this.tagRepository.save(tg);
        tag.setTagId("T"+tag.getUuid());
        tag = this.tagRepository.saveAndFlush(tag);
        LOG.info("执行结束{} createTag()方法.", this.CLASS);
        return ResponseEntity.ok(tag);
    }

    /**
     * 获取指定名称的标签
     *
     * @param name
     * @return
     */
    @Override
    public ResponseEntity<Tag> findByName(String name) {
        tag = this.tagRepository.findByName(name);
        return ResponseEntity.ok(tag);
    }

    /**
     * 更新标签引用次数
     *
     * @param tagId
     * @return 跟新后的标签
     */
    @Override
    public ResponseEntity<Tag> updateQupteCnt(String tagId) {

        tag = this.tagRepository.findByTagId(tagId);
        if (tag == null) {
            return ResponseEntity.ok(tag);
        }
        tag.setQuoteCnt(tag.getQuoteCnt() + 1);
        tag = this.tagRepository.save(tag);
        return ResponseEntity.ok(tag);
    }


    /**
     * 获取标签：分页，排序
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvTagList(Integer page, Integer pageSize, String sortBy) throws SQLException{

        sortBy = (sortBy== null) ? "quoteCnt" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Tag> tagPage = null;
        tagPage = this.tagRepository.findAll(pageable);
        return ResponseEntity.ok(tagPage);
    }

    @Override
    public ResponseEntity<?> rtrvTagList() {

        return ResponseEntity.ok(this.tagRepository.findAll());
    }

}
