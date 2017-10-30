package com.mdvns.mdvn.model.sapi.service;

import com.mdvns.mdvn.model.sapi.domain.*;
import com.mdvns.mdvn.model.sapi.domain.entity.*;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface ModelService {

    /*新建模型保存*/
    ResponseEntity<?> saveModel(CreateModelRequest request) throws SQLException;

    /*根据名称查询模型*/
    ResponseEntity<Model> findByName(String name);

    RetrieveModelListResponse rtrvModelList();

    /*更新模型引用次数*/
    ResponseEntity<Model> updateQupteCnt(String modelId);

    RetrieveModelListResponse rtrvModelList(Integer page, Integer pageSize, String sortBy) throws SQLException;

    RetrieveModelListResponse rtrvModelList(RetrieveModelListByTypeRequest retrieveModelListRequest) throws
            SQLException;

    RetrieveModelListAndSortResponse rtrvModelAndSortList(RetrieveModelListByTypeRequest retrieveModelListRequest) throws
            SQLException;

    RtrvModelByIdResponse findById(RtrvModelByIdRequest request);

    SubFunctionLabel findById(RtrvSubFunctionLabelById request);

    ModelRole findById(String roleId);

    Model findModelById(String modelId);

    List<IterationModel> findIterationModelById(String modelId);

    SubFunctionLabel judgeSubLabelId(JudgeSubLabelIdRequest request);

    List<TaskDelivery> findTaskDeliveryById(RtrvModelByIdRequest request);

    CreateModelResponse findModelDetailById(RtrvModelByIdRequest request);

}
