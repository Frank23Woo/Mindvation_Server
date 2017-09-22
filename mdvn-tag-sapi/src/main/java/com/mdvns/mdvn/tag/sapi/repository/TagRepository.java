package com.mdvns.mdvn.tag.sapi.repository;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
