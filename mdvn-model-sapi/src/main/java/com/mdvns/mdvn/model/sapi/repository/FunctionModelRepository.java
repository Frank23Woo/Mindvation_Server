package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.domain.entity.SubFunctionLabel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionModelRepository extends JpaRepository<SubFunctionLabel,Integer>{
    SubFunctionLabel findByName(String name);

    Page<SubFunctionLabel> findAll(Pageable pageable);

    List<SubFunctionLabel> findByParentId(String modelId);

    SubFunctionLabel findByLabelId(String labelId);

    @Query(value="  SELECT * FROM sub_function_label where parent_id=?1 AND name = ?2", nativeQuery = true)
    SubFunctionLabel findLabelId(String parentId,String name);

    @Query(value="  SELECT DISTINCT COUNT(*) FROM sub_function_label ", nativeQuery = true)
    Long getModelCount();
}
