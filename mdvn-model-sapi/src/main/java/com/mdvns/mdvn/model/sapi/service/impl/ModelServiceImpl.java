package com.mdvns.mdvn.model.sapi.service.impl;

import com.mdvns.mdvn.common.beans.TaskDeliver;
import com.mdvns.mdvn.model.sapi.domain.*;

import com.mdvns.mdvn.model.sapi.domain.entity.*;
import com.mdvns.mdvn.model.sapi.repository.*;
import com.mdvns.mdvn.model.sapi.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private ItModelRepository itModelRepository;

    @Autowired
    private TaskDeliveryRepository taskDeliveryRepository;

    @Autowired
    private Model model;

    @Autowired
    private SubFunctionLabel subFunctionLabel;

    @Autowired
    private SubFunctionLabel subfunctionModelSub;

    @Autowired
    private ModelRole modelRole;

    /**
     *
     *
     * @param request
     * @return Model
     * @desc: 保存新建模块
     * 1.modelId 为null;
     * 2.校验name对应的Model是否已存在
     */
    @Override
    public ResponseEntity<?> saveModel(CreateModelRequest request) throws SQLException {
        LOG.info("开始执行{} saveModel()方法.", this.CLASS);
        CreateModelResponse createModelResponse = new CreateModelResponse();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Model model = new Model();
        ModelRole modelRole = new ModelRole();
        //1.保存Model表数据
        model.setIsDeleted(0);
        model.setQuoteCnt(0);
        model.setCreateTime(createTime);
        model.setName(request.getName());
        model.setCreatorId(request.getCreatorId());
        model.setModelType(request.getModelType());
        //数据保存后modelId没有生成
        model = this.modelRepository.saveAndFlush(model);
        model.setModelId("M" + model.getUuId());
        model = this.modelRepository.saveAndFlush(model);
        createModelResponse.setModel(model);
        //2.保存FunctionModel表数据(过程方法模块)
        List<FunctionLabel> functionLabels = request.getFunctionLabels();
        List<FunctionLabel> functionLabelList = new ArrayList<FunctionLabel>();
        for (int i = 0; i < functionLabels.size(); i++) {
            SubFunctionLabel subFunctionLabel = new SubFunctionLabel();
            FunctionLabel functionLabel = new FunctionLabel();
            subFunctionLabel.setCreateTime(createTime);
            subFunctionLabel.setCreatorId(request.getCreatorId());
            subFunctionLabel.setIsDeleted(0);
            subFunctionLabel.setQuoteCnt(0);
            subFunctionLabel.setName(functionLabels.get(i).getName());
            subFunctionLabel.setParentId(model.getModelId());
            subFunctionLabel = this.functionModelRepository.saveAndFlush(subFunctionLabel);
            subFunctionLabel.setLabelId("MF" + subFunctionLabel.getUuId());
            subFunctionLabel = this.functionModelRepository.saveAndFlush(subFunctionLabel);
            functionLabel.setName(subFunctionLabel.getName());
            functionLabel.setCreateTime(subFunctionLabel.getCreateTime());
            functionLabel.setCreatorId(subFunctionLabel.getCreatorId());
            functionLabel.setModelId(subFunctionLabel.getLabelId());
            functionLabel.setParentId(subFunctionLabel.getParentId());
            functionLabel.setIsDeleted(subFunctionLabel.getIsDeleted());
            //3.保存FunctionModel表数据(过程方法子模块)
            List<SubFunctionLabel> subfunctionLabels = functionLabels.get(i).getSubFunctionLabels();
            List<SubFunctionLabel> subfunctionModelListSub = new ArrayList<SubFunctionLabel>();
            for (int j = 0; j < subfunctionLabels.size(); j++) {
                SubFunctionLabel subfunctionModelSub = new SubFunctionLabel();
                SubFunctionLabel funcModel = new SubFunctionLabel();
                subfunctionModelSub.setCreateTime(createTime);
                subfunctionModelSub.setCreatorId(request.getCreatorId());
                subfunctionModelSub.setIsDeleted(0);
                subfunctionModelSub.setQuoteCnt(0);
                subfunctionModelSub.setName(subfunctionLabels.get(i).getName());
                subfunctionModelSub.setParentId(subFunctionLabel.getLabelId());
                subfunctionModelSub = this.functionModelRepository.saveAndFlush(subfunctionModelSub);
                subfunctionModelSub.setLabelId("MF" + subfunctionModelSub.getUuId());
                subfunctionModelSub = this.functionModelRepository.saveAndFlush(subfunctionModelSub);
                subfunctionModelListSub.add(subfunctionModelSub);
            }
            functionLabel.setSubFunctionLabels(subfunctionModelListSub);
            functionLabelList.add(functionLabel);
        }
        createModelResponse.setFunctionLabels(functionLabelList);
        //4.保存ModelRole表数据
        List<ModelRole> modelRoles = request.getRoles();
        List<ModelRole> modelRoleList = new ArrayList<ModelRole>();
        for (int i = 0; i < modelRoles.size(); i++) {
            modelRole.setModelId(model.getModelId());
            modelRole.setName(modelRoles.get(i).getName());
            modelRole.setCreateTime(createTime);
            modelRole.setCreatorId(request.getCreatorId());
            modelRole.setIsDeleted(0);
            modelRole.setQuoteCnt(0);
            modelRole = this.modelRoleRepository.saveAndFlush(modelRole);
            modelRole.setRoleId("MR" + modelRole.getUuId());
            modelRole = this.modelRoleRepository.saveAndFlush(modelRole);
            modelRoleList.add(modelRole);
        }
        createModelResponse.setRoles(modelRoleList);
        //5.保存IterationModel表数据
        if (request.getIterationModels() != null && !request.getIterationModels().isEmpty()) {
            List<CreateItModelResponse> createItModelResponses = new ArrayList<>();
            List<CreateItModelRequest> iterationModels = request.getIterationModels();
            List<IterationModel> iterationModelList = new ArrayList<IterationModel>();
            for (int i = 0; i < iterationModels.size(); i++) {
                CreateItModelResponse createItModelResponse = new CreateItModelResponse();
                IterationModel iterationModel = new IterationModel();
                iterationModel.setModelId(model.getModelId());
                iterationModel.setIsDeleted(0);
                iterationModel.setCreateTime(createTime);
                if (!StringUtils.isEmpty(iterationModels.get(i).getName())) {
                    iterationModel.setName(iterationModels.get(i).getName());
                }
                List<String> labels = iterationModels.get(i).getLabels();
                List<String> labelIds = new ArrayList<>();
                for (int j = 0; j < labels.size(); j++) {
                    SubFunctionLabel subfuncModel = this.functionModelRepository.findByNameAndParentId(labels.get(j),model.getModelId());
                    String labelId = subfuncModel.getLabelId();
                    labelIds.add(labelId);
                }
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < labelIds.size(); j++) {
                    if (j == labelIds.size() - 1) {
                        str.append(labelIds.get(j));
                    } else {
                        str.append(labelIds.get(j));
                        str.append(",");
                    }
                }
                iterationModel.setLabelIds(str.toString());
                iterationModel = this.itModelRepository.saveAndFlush(iterationModel);
                iterationModelList.add(iterationModel);
                createItModelResponse.setIterationModel(iterationModel);
                //响应
                List<SubFunctionLabel> funcLabels = new ArrayList<>();
                for (int j = 0; j < labelIds.size(); j++) {
                    SubFunctionLabel subFuncLabel = new SubFunctionLabel();
                    subFuncLabel = this.functionModelRepository.findByLabelId(labelIds.get(j));
                    funcLabels.add(subFuncLabel);
                }
                createItModelResponse.setFunctionLabels(funcLabels);
                createItModelResponses.add(createItModelResponse);
            }
            createModelResponse.setIterationModels(createItModelResponses);
        }
        //6.保存TaskDelinery表数据
        if (request.getTaskDeliveries() != null && !request.getTaskDeliveries().isEmpty()) {
            List<TaskDelivery> taskDelivers = request.getTaskDeliveries();
            List<TaskDelivery> taskDeliverlist = new ArrayList<>();
            for (int i = 0; i < taskDelivers.size() ; i++) {
                TaskDelivery taskDelivery = new TaskDelivery();
                taskDelivery.setName(taskDelivers.get(i).getName());
                taskDelivery.setModelId(model.getModelId());
                taskDelivery.setType(taskDelivers.get(i).getType());
                if (!StringUtils.isEmpty(taskDelivers.get(i).getColor())){
                    taskDelivery.setColor(taskDelivers.get(i).getColor());
                }
                taskDelivery.setCreateTime(createTime);
                taskDelivery = this.taskDeliveryRepository.saveAndFlush(taskDelivery);
                taskDeliverlist.add(taskDelivery);
            }
            createModelResponse.setTaskDeliveries(taskDeliverlist);
        }
        LOG.info("执行结束{} saveModel()方法.", this.CLASS);
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
     *
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public RetrieveModelListResponse rtrvModelList(Integer page, Integer pageSize, String sortBy) throws
            SQLException {
        RetrieveModelListResponse retrieveModelListResponse = new RetrieveModelListResponse();
        sortBy = (sortBy == null) ? "quoteCnt" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, sortBy);
        Page<Model> modelPage = null;
        modelPage = this.modelRepository.findAll(pageable);
        retrieveModelListResponse.setModels(modelPage.getContent());
        retrieveModelListResponse.setTotalNumber(modelPage.getTotalElements());
        return retrieveModelListResponse;
    }

    /**
     * 通过modelId获取它的过程方法模块对象（List）
     *
     * @param request
     * @return
     */
    @Override
    public RtrvModelByIdResponse findById(RtrvModelByIdRequest request) {
        LOG.info("开始执行{} findById()方法.", this.CLASS);
        RtrvModelByIdResponse rtrvModelByIdResponse = new RtrvModelByIdResponse();
        List<SubFunctionLabel> subFunctionLabels = new ArrayList<>();
        subFunctionLabels = this.functionModelRepository.findByParentId(request.getModelId());
        rtrvModelByIdResponse.setFunctionLabels(subFunctionLabels);
        List<ModelRole> modelRoles = new ArrayList<>();
        modelRoles = this.modelRoleRepository.findByModelId(request.getModelId());
        rtrvModelByIdResponse.setModelRoles(modelRoles);
        LOG.info("执行结束{} findById()方法.", this.CLASS);
        return rtrvModelByIdResponse;
    }

    /**
     * 通过labelId获取它自己的过程方法模块对象（单个）
     *
     * @param request
     * @return
     */
    @Override
    public SubFunctionLabel findById(RtrvSubFunctionLabelById request) {
        LOG.info("开始执行{} findById()方法.", this.CLASS);
        subFunctionLabel = this.functionModelRepository.findByLabelId(request.getLabelId());
        LOG.info("执行结束{} findById()方法.", this.CLASS);
        return subFunctionLabel;
    }

    /**
     * 通过roleId获取ModelRole对象（单个）
     *
     * @param roleId
     * @return
     */
    @Override
    public ModelRole findById(String roleId) {
        LOG.info("开始执行{} findById()方法.", this.CLASS);
        modelRole = this.modelRoleRepository.findByRoleId(roleId);
        LOG.info("执行结束{} findById()方法.", this.CLASS);
        return modelRole;
    }

    /**
     * 判断子模块是否存在数据库里面
     *
     * @param request
     * @return
     */
    @Override
    public SubFunctionLabel judgeSubLabelId(JudgeSubLabelIdRequest request) {
        LOG.info("开始执行{} findLabelId()方法.", this.CLASS);
        subFunctionLabel = request.getSubFunctionLabel();
        String creatorId = request.getCreatorId();
        String name = request.getSubFunctionLabel().getName();
        SubFunctionLabel subFuncLabel = new SubFunctionLabel();
        if (subFunctionLabel.getLabelId() == null) {
            subFuncLabel.setIsDeleted(0);
            subFuncLabel.setName(name);
            subFuncLabel.setCreatorId(creatorId);
            Timestamp createTime = new Timestamp(System.currentTimeMillis());
            subFuncLabel.setCreateTime(createTime);
            subFuncLabel.setQuoteCnt(0);
            subFunctionLabel = this.functionModelRepository.saveAndFlush(subFuncLabel);
            subFunctionLabel.setLabelId("MF" + subFunctionLabel.getUuId());
            subFunctionLabel = this.functionModelRepository.saveAndFlush(subFunctionLabel);
        } else {
            subFunctionLabel = this.functionModelRepository.findByLabelId(request.getSubFunctionLabel().getLabelId());
        }
        LOG.info("执行结束{} findLabelId()方法.", this.CLASS);
        return subFunctionLabel;
    }

    /**
     * 获取全部模块
     *
     * @return
     */
    @Override
    public RetrieveModelListResponse rtrvModelList() {
        RetrieveModelListResponse retrieveModelListResponse = new RetrieveModelListResponse();
        List<Model> modelList = this.modelRepository.findAll();
        Long count = this.modelRepository.getModelCount();
        retrieveModelListResponse.setModels(modelList);
        retrieveModelListResponse.setTotalNumber(count);
        return retrieveModelListResponse;
    }


}
