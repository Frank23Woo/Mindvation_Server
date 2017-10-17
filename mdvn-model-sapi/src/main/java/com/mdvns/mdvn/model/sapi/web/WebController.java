package com.mdvns.mdvn.model.sapi.web;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.model.sapi.domain.*;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import com.mdvns.mdvn.model.sapi.domain.entity.SubFunctionLabel;
import com.mdvns.mdvn.model.sapi.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
public class WebController {
    private Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private ModelService modelService;

    /**
     * 新建模型
     *
     * @param request
     * @return
     */
    @PostMapping("/models/model")
    public ResponseEntity<?> saveModel(@RequestBody CreateModelRequest request) throws SQLException {
        LOG.info("开始执行 createModel 方法.");
        return this.modelService.saveModel(request);
    }
    /**
     * 获取Model列表，分页/排序
     */
    @PostMapping(value = "/models")
    public RetrieveModelListResponse rtrvModelList(@RequestBody RetrieveModelListRequest retrieveModelListRequest) throws SQLException, BusinessException {
        Integer page = retrieveModelListRequest.getPage();
        Integer pageSize = retrieveModelListRequest.getPageSize();
        if (null==page||pageSize==null) {
            return this.modelService.rtrvModelList();
        }
        return this.modelService.rtrvModelList((page-1), pageSize, retrieveModelListRequest.getSortBy());
    }

    /**
     * 根据名称查询模型
     *
     * @param name 模型名称
     * @return Model
     */
    @PostMapping(value = "/models/model/{name}")
    public ResponseEntity<Model> findByName(@PathVariable String name) {
        return this.modelService.findByName(name);
    }

    /**
     * 根据指定的ID给quoteCnt值+1
     *
     * @param modelId
     * @return
     */
    @PostMapping(value = "/models/{modelId}")
    public ResponseEntity<Model> updateQuoteCnt(@PathVariable String modelId) {
        return this.modelService.updateQupteCnt(modelId);
    }

    /**
     * 根据id查询模块
     *
     * @param request 模块Id
     * @return Model
     */
    @PostMapping(value = "/models/findById")
    public RtrvModelByIdResponse findById(@RequestBody RtrvModelByIdRequest request) {
        return this.modelService.findById(request);
    }

    /**
     * 根据id查询模块
     *
     * @param request 模块Id
     * @return Model
     */
    @PostMapping(value = "/models/findFuncById")
    public SubFunctionLabel findById(@RequestBody RtrvSubFunctionLabelById request) {
        return this.modelService.findById(request);
    }
    /**
     * 根据角色id查询角色对象
     *
     * @param roleId 角色Id
     * @return ModelRole
     */
    @PostMapping(value = "/models/findModelRoleById")
=======
    @PostMapping(value = "/models/findRoleById")
>>>>>>> 71a76d1982e88238a3b91ad8d8c0dedc68640adc
    public ModelRole findById(@RequestBody String roleId) {
        return this.modelService.findById(roleId);
    }

    /**
     * 根据parentId 和 过程方法子模块的那么 查询过程方法子模块的Id，如果不存在添加
     *
     * @param request
     * @return ModelRole
     */
    @PostMapping(value = "/models/judgeSubLabelId")
    public SubFunctionLabel judgeSubLabelId(@RequestBody JudgeSubLabelIdRequest request) {
        return this.modelService.judgeSubLabelId(request);
    }



}
