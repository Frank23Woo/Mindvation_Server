package com.mdvns.mdvn.tag.sapi.service;

import com.mdvns.mdvn.tag.sapi.domain.RetrieveTagListRequest;
import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
=======
>>>>>>> 29a5f170fe0b2f4b8c1402144187839255cad32b
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

/**
 * 標簽模塊接口
 */

public interface TagService {

    /*新建标签保存*/
<<<<<<< HEAD
    ResponseEntity<?> saveTag(Tag tag) throws SQLException;
=======
    ResponseEntity<?> saveTag(Tag tag);

    /*由UUID获取tagId*/
    Tag getTagIdByUuId(Tag tag);
>>>>>>> 29a5f170fe0b2f4b8c1402144187839255cad32b

    /*根据名称查询标签*/
    ResponseEntity<Tag> findByName(String name);

    /*更新标签引用次数*/
    ResponseEntity<Tag> updateQupteCnt(String tagId);

<<<<<<< HEAD
    ResponseEntity<Page<Tag>> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest) throws SQLException;
=======
    List<Tag> rtrvTagList(RetrieveTagListRequest retrieveTagListRequest);


>>>>>>> 29a5f170fe0b2f4b8c1402144187839255cad32b
}
