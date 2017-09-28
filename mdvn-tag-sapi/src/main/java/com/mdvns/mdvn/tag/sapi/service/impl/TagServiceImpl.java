package com.mdvns.mdvn.tag.sapi.service.impl;


import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import com.mdvns.mdvn.tag.sapi.repository.TagRepository;
import com.mdvns.mdvn.tag.sapi.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private Tag tg;


    /**
     * @param tag
     * @return Tag
     * @throws Exception
     * @throws SQLException
     * @desc: 保存新建标签
     * 1.tagId 为null;
     * 2.校验name对应的Tag是否已存在
     */
    @Override
    public Tag saveTag(Tag tag) throws Exception, SQLException {
        LOG.info("开始执行{} createTag()方法.", this.CLASS);

        if (tag.getTagId() != null) {
            LOG.error("此ID的标签已存在.");
            throw new Exception("此ID的标签已存在.");
        }
        tg = this.tagRepository.findByName(tag.getName());
        if (tg != null) {
            LOG.error("标签已存在:{}", tg.toString());
        }
        tag.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tag.setQuoteCnt(0);
        tg = this.tagRepository.save(tag);

        LOG.info("执行结束{} createTag()方法.", this.CLASS);
        return tg;
    }

    /**
     * 获取指定名称的标签
     *
     * @param name
     * @return
     */
    @Override
    public Tag findByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new NullPointerException("标签名称不能为空.");
        }
        tg = this.tagRepository.findByName(name);
        if (null == tg) {
            LOG.error("名称为{}的标签不存在.", name);
            throw new NullPointerException(name + "标签不存在.");
        }
        return tg;
    }

    /**
     * 更新标签引用次数
     *
     * @param tagId
     * @return 跟新后的标签
     */
    @Override
    public Tag updateQupteCnt(String tagId) {

        tg = this.tagRepository.findByTagId(tagId);
        if (null == tg) {
            throw new NullPointerException("标签不存在.");
        }
        LOG.info("标签:{}", tg.toString());
        tg.setQuoteCnt(tg.getQuoteCnt()+1);
        return this.tagRepository.save(tg);
    }


    /**
     * 获取标签列表 按应用次数降序排列 支持分页
     * @param retrieveTagListRequest
     * @return
     */
    @Override
    public List<Tag> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest){

        Integer page = (retrieveTagListRequest.getPage()==null)?0:retrieveTagListRequest.getPage();

        Integer size = retrieveTagListRequest.getPageSize();
        Integer pageSize = (null == retrieveTagListRequest.getPageSize()) ? 6 : retrieveTagListRequest.getPageSize();
        String sortBy = (retrieveTagListRequest.getSortBy()==null)?"quoteCnt":retrieveTagListRequest.getSortBy();
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Tag> tagPage = null;
        tagPage = this.tagRepository.findAll(pageable);

        LOG.info("查询结果为：{}",tagPage.getContent().toString());
        return tagPage.getContent();
    }

}
