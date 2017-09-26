package com.mdvns.mdvn.tag.sapi.service;

import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;

import java.sql.SQLException;
import java.util.List;

/**
 * 標簽模塊接口
 */

public interface TagService {

    /*新建标签保存*/
    Tag saveTag(Tag tag) throws Exception;

    /*根据名称查询标签*/
    Tag findByName(String name);

    /*更新标签引用次数*/
    Tag updateQupteCnt(String tagId);

    List<Tag> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest) throws SQLException;
}
