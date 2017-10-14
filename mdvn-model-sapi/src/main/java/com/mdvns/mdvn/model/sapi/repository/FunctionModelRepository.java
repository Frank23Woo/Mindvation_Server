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

    @Query(value="  SELECT DISTINCT COUNT(*) FROM function_model ", nativeQuery = true)
    Long getModelCount();
}
