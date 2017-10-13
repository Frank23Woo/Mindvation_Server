package com.mdvns.mdvn.model.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.sapi.domain.CreateModelRequest;
import com.mdvns.mdvn.model.sapi.domain.CreateModelResponse;
import com.mdvns.mdvn.model.sapi.domain.RetrieveModelListResponse;
import com.mdvns.mdvn.model.sapi.domain.entity.FunctionModel;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import com.mdvns.mdvn.model.sapi.repository.FunctionModelRepository;
import com.mdvns.mdvn.model.sapi.repository.ModelRepository;
import com.mdvns.mdvn.model.sapi.repository.ModelRoleRepository;
import com.mdvns.mdvn.model.sapi.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(ModelServiceImpl.class);

    private final String CLASS = this.getClass().getName();


    @Autowired
    private FunctionModelRepository functionModelRepository;

    @Autowired
    private ModelRoleRepository modelRoleRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private Model model;

    @Autowired
    private FunctionModel functionModel;

    @Autowired
    private ModelRole modelRole;
    /**
     * @param request
     * @return Model
     * @desc: 保存新建模块
     * 1.modelId 为null;
     * 2.校验name对应的Model是否已存在
     */
    @Override
    public ResponseEntity<?> saveModel(CreateModelRequest request) throws SQLException {
        LOG.info("开始执行{} createModel()方法.", this.CLASS);
        CreateModelResponse createModelResponse = new CreateModelResponse();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        //1.保存Model表数据
        model.setCreateTime(createTime);
        model.setIsDeleted(0);
        model.setQuoteCnt(0);
        model.setName(request.getName());
        model.setCreatorId(request.getCreatorId());
        model.setModelType(request.getIndustry());
             //数据保存后modelId没有生成
        model = this.modelRepository.saveAndFlush(model);
        model.setModelId("M"+ model.getUuId());
        model = this.modelRepository.saveAndFlush(model);
        createModelResponse.setModel(model);
        //2.保存FunctionModel表数据
        List<FunctionModel> functionModels = request.getFunctionLabel();
        List<FunctionModel> functionModelList = null;
        for (int i = 0; i < functionModels.size(); i++) {
            functionModel.setCreateTime(createTime);
            functionModel.setCreatorId(request.getCreatorId());
            functionModel.setIsDeleted(0);
            functionModel.setQuoteCnt(0);
            functionModel.setName(functionModels.get(i).getName());
            functionModel.setParentId(model.getModelId());
            functionModel = this.functionModelRepository.saveAndFlush(functionModel);
            functionModel.setModelId("MF"+ functionModel.getUuId());
            functionModel = this.functionModelRepository.saveAndFlush(functionModel);
            functionModelList.add(functionModel);
        }
        createModelResponse.setFunctionLabel(functionModelList);
        //3.保存ModelRole表数据
        List<ModelRole> modelRoles = request.getRoles();
        List<ModelRole> modelRoleList = null;
        for (int i = 0; i < modelRoles.size(); i++) {
            modelRole.setName(modelRoles.get(i).getName());
            modelRole.setCreateTime(createTime);
            modelRole.setCreatorId(request.getCreatorId());
            modelRole.setIsDeleted(0);
            modelRole.setQuoteCnt(0);
            modelRole = this.modelRoleRepository.saveAndFlush(modelRole);
            modelRole.setRoleId("MR"+ modelRole.getUuId());
            modelRole = this.modelRoleRepository.saveAndFlush(modelRole);
            modelRoleList.add(modelRole);
        }
        createModelResponse.setRoles(modelRoleList);
        LOG.info("执行结束{} createModel()方法.", this.CLASS);
        return ResponseEntity.ok(createModelResponse);
    }

    /**
     * 获取指定名称的模块
     *
     * @param name
     * @return
     */
    @Override
    public ResponseEntity<Model> findByName(String name) {
        model = this.modelRepository.findByName(name);
        return ResponseEntity.ok(model);
    }

    /**
     * 更新模块引用次数
     *
     * @param modelId
     * @return 跟新后的模块
     */
    @Override
    public ResponseEntity<Model> updateQupteCnt(String modelId) {

        model = this.modelRepository.findByModelId(modelId);
        if (model == null) {
            return ResponseEntity.ok(model);
        }
        model.setQuoteCnt(model.getQuoteCnt() + 1);
        model = this.modelRepository.save(model);
        return ResponseEntity.ok(model);
    }


    /**
     * 获取模块：分页，排序
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public RetrieveModelListResponse rtrvModelList(Integer page, Integer pageSize, String sortBy) throws SQLException{
        RetrieveModelListResponse retrieveModelListResponse =new RetrieveModelListResponse();
        sortBy = (sortBy== null) ? "quoteCnt" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Model> modelPage = null;
        modelPage = this.modelRepository.findAll(pageable);
        retrieveModelListResponse.setModels(modelPage.getContent());
        retrieveModelListResponse.setTotalNumber(modelPage.getTotalElements());
        return retrieveModelListResponse;
    }

    /**
     * 获取全部模块
     * @return
     */
    @Override
    public RetrieveModelListResponse rtrvModelList() {
        RetrieveModelListResponse retrieveModelListResponse =new RetrieveModelListResponse();
        List<Model> modelList = this.modelRepository.findAll();
        Long count = this.modelRepository.getModelCount();
        retrieveModelListResponse.setModels(modelList);
        retrieveModelListResponse.setTotalNumber(count);
        return retrieveModelListResponse;
    }

}
