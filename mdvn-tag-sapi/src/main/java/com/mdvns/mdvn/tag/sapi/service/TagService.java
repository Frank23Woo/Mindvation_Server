package com.mdvns.mdvn.tag.sapi.service;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;

import java.util.List;

/**
 * 標簽模塊接口
 */

public interface TagService {

    /*新建标签保存*/
    RestDefaultResponse saveTag(Tag tag);

    /*根据名称查询标签*/
    Tag findByName(String name);

    /*更新标签引用次数*/
    Tag updateQupteCnt(String tagId);

    List<Tag> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest);
}
