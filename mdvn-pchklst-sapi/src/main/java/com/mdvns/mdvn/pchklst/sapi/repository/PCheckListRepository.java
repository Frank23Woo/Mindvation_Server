package com.mdvns.mdvn.pchklst.sapi.repository;

import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCheckListRepository extends JpaRepository<PCheckList, Integer> {


    PCheckList findByPCheckListId();


}
