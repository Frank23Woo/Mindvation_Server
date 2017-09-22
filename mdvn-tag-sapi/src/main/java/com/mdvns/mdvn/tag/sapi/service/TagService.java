package com.mdvns.mdvn.tag.sapi.service;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;

/**
 * 標簽模塊接口
 */

public interface TagService {

    /*新建标签保存*/
    Tag saveTag(Tag tag) throws Exception;

}
