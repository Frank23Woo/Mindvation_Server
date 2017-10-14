package com.mdvns.mdvn.tag.sapi.service.impl;


import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListResponse;
import com.mdvns.mdvn.tag.sapi.domain.RtrvTagsRequest;
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
import java.util.List;

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
        //数据保存后tagId没有生成
        tag = this.tagRepository.save(tg);
//        tag = this.tagRepository.findOne(tag.getUuid());
        tag.setTagId("T" + tag.getUuId());
        tag = this.tagRepository.save(tag);
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
     *
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvTagList(Integer page, Integer pageSize, String sortBy) throws SQLException {
        RetrieveTagListResponse retrieveTagListResponse = new RetrieveTagListResponse();
        sortBy = (sortBy == null) ? "quoteCnt" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Tag> tagPage = null;
        tagPage = this.tagRepository.findAll(pageable);
        retrieveTagListResponse.setTags(tagPage.getContent());
        retrieveTagListResponse.setTotalNumber(tagPage.getTotalElements());
        return ResponseEntity.ok(retrieveTagListResponse);
    }

    /**
     * 获取全部标签
     *
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvTagList() {
        RetrieveTagListResponse retrieveTagListResponse = new RetrieveTagListResponse();
        List<Tag> tagList = this.tagRepository.findAll();
        Long count = this.tagRepository.getTagCount();
        retrieveTagListResponse.setTags(tagList);
        retrieveTagListResponse.setTotalNumber(count);
        return ResponseEntity.ok(tagList);
    }

    public ResponseEntity<?> rtrvTagsById(RtrvTagsRequest rtrvTagsRequest) {
        List<Tag> tags = this.tagRepository.findByTagIdIn(rtrvTagsRequest.getTagIds());
        return ResponseEntity.ok(tags);
    }

    /**
     * 获取制定Id的标签
     *
     * @param tagId
     * @return
     */
    public ResponseEntity<?> findById(String tagId) {
        tag = this.tagRepository.findByTagId(tagId);
        return ResponseEntity.ok(tag);
    }

}
