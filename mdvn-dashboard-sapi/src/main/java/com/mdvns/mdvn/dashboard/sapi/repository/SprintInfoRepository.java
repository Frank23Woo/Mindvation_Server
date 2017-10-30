package com.mdvns.mdvn.dashboard.sapi.repository;

import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintInfoRepository extends JpaRepository<SprintInfo, Integer> {

    List<SprintInfo> findBySubjectIdAndIsDeletedAndSprintIndex(String subjectId , Integer isDeleted,Integer sprintIndex);

    List<SprintInfo> findBySubjectIdAndModelIdAndIsDeleted(String subjectId ,String modelId, Integer isDeleted);

    SprintInfo findBySubjectIdAndNameAndIsDeleted(String subjectId ,String name, Integer isDeleted);
}
