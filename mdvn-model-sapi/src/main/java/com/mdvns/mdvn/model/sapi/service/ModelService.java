package com.mdvns.mdvn.model.sapi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.sapi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.sapi.domain.RetrieveModelListResponse;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface ModelService {

    /*新建模型保存*/
    ResponseEntity<?> saveModel(CreateModelRequest request) throws SQLException;

    /*根据名称查询模型*/
    ResponseEntity<Model> findByName(String name);

    RetrieveModelListResponse rtrvModelList();

    /*更新模型引用次数*/
    ResponseEntity<Model> updateQupteCnt(String modelId);

    RetrieveModelListResponse rtrvModelList(Integer page, Integer pageSize, String sortBy) throws SQLException;

}
