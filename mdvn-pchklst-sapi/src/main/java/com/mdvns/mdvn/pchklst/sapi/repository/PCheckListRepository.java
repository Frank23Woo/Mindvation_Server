package com.mdvns.mdvn.pchklst.sapi.repository;

import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PCheckListRepository extends JpaRepository<PCheckList, Integer> {


    PCheckList findByPCheckListId(String pCheckListId);

    List<PCheckList> findAllByProjectIdAndIsDeleted(String projectId, Integer isDeleted);

    List<PCheckList> findAllByProjectIdAndIsDeletedAndStatusGreaterThan(String projectId, Integer isDeleted, Integer status);

    List<PCheckList> findAllByProjectIdAndIsDeletedAndStatusGreaterThanEqualOrderByStatusAsc(String projectId, Integer isDeleted, Integer status);

    Page<PCheckList> findAllByProjectIdAndIsDeletedAndStatusGreaterThanEqualOrderByStatusAsc(String projectId, Integer isDeleted, Integer status, Pageable pageable);





}
