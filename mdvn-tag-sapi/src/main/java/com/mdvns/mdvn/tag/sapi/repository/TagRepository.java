package com.mdvns.mdvn.tag.sapi.repository;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {


    Tag findByName(String name);

    Page<Tag> findAll(Pageable pageable);

    Tag findByTagId(String tagId);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM tag ", nativeQuery = true)
    Long getTagCount();

    List<Tag> findByTagIdIn(List<String> tagIds);


//    Page<Tag> findAllOrderByQuoteCnt(Pageable pageable);
}
