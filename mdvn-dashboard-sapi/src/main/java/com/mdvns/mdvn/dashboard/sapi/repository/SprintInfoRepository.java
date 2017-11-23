package com.mdvns.mdvn.dashboard.sapi.repository;

import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintInfoRepository extends JpaRepository<SprintInfo, Integer> {

    List<SprintInfo> findBySubjectIdAndCreatorIdAndIsDeletedAndSprintIndexAndStatusIsNot(String subjectId ,String creatorId, Integer isDeleted,Integer sprintIndex,String status);

    List<SprintInfo> findBySubjectIdAndModelIdAndCreatorIdAndIsDeletedAndStatusIsNot(String subjectId ,String modelId,String creatorId, Integer isDeleted,String status);

    SprintInfo findBySubjectIdAndCreatorIdAndNameAndIsDeleted(String subjectId ,String creatorId,String name, Integer isDeleted);
    SprintInfo findBySubjectIdAndCreatorIdAndSprintIndex(String subjectId,String creatorId ,Integer sprintIndex);
    //查询所有dashboard信息
    List<SprintInfo> findBySubjectIdAndIsDeletedAndSprintIndexAndStatusIsNot(String subjectId, Integer isDeleted,Integer sprintIndex,String status);
    List<SprintInfo> findBySubjectIdAndModelIdAndIsDeletedAndStatusIsNot(String subjectId ,String modelId, Integer isDeleted,String status);
    //查询一个项目相同模型下name相同的sprint
    List<SprintInfo> findBySubjectIdAndModelIdAndNameAndSprintIndex(String subjectId ,String modelId, String name,Integer sprintIndex);


}
