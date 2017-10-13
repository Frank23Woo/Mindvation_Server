package com.mdvns.mdvn.tag.sapi.service;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

/**
 * 標簽模塊接口
 */

public interface TagService {

    /*新建标签保存*/
    ResponseEntity<?> saveTag(Tag tag) throws SQLException;

    /*根据名称查询标签*/
    ResponseEntity<Tag> findByName(String name);

    ResponseEntity<?> rtrvTagList();

    /*更新标签引用次数*/
    ResponseEntity<Tag> updateQupteCnt(String tagId);

    ResponseEntity<?> rtrvTagList(Integer page, Integer pageSize, String sortBy) throws SQLException;

}
