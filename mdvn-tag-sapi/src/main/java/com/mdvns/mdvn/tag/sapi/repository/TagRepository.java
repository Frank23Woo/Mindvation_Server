package com.mdvns.mdvn.tag.sapi.repository;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);

    Page<Tag> findAll(Pageable pageable);


//    Page<Tag> findAllOrderByQuoteCnt(Pageable pageable);
}
