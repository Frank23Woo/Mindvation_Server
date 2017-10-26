package com.mdvns.mdvn.file.sapi.repository;

import com.mdvns.mdvn.file.sapi.domain.entity.AttchInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttchInfoRepository extends JpaRepository<AttchInfo, Integer>{


    List<AttchInfo> findByIdIn(List<Integer> idList);
}
