package com.mdvns.mdvn.model.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.model.sapi.domain.RetrieveModelListResponse;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import com.mdvns.mdvn.model.sapi.repository.ModelRepository;
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
    private ModelRepository modelRepository;

    @Autowired
    private Model model;

    @Override
    public RestResponse getModelList() throws Exception {
        List<Model> models = modelRepository.findAll();
        RestResponse restResponse = new RestResponse();
        restResponse.setResponseMsg("");
        restResponse.setResponseCode("0");
        restResponse.setResponseBody(models);
        return restResponse;
    }

    /**
     * @param tg
     * @return Model
     * @desc: 保存新建模块
     * 1.modelId 为null;
     * 2.校验name对应的Model是否已存在
     */
    @Override
    public ResponseEntity<?> saveModel(Model tg) throws SQLException {
        LOG.info("开始执行{} createModel()方法.", this.CLASS);


        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        tg.setCreateTime(createTime);
        tg.setIsDeleted(0);
        tg.setQuoteCnt(0);
        //数据保存后modelId没有生成
        model = this.modelRepository.save(tg);
//        model = this.modelRepository.findOne(model.getUuid());
        model.setModelId("T"+ model.getUuId());
        model = this.modelRepository.save(model);
        LOG.info("执行结束{} createModel()方法.", this.CLASS);

        return ResponseEntity.ok(model);
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
