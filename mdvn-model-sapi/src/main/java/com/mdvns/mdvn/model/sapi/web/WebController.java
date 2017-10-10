package com.mdvns.mdvn.model.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.model.sapi.domain.RetrieveModelListRequest;
import com.mdvns.mdvn.model.sapi.domain.RetrieveModelListResponse;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
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
     * 新建标签
     *
     * @param md
     * @return
     */
    @PostMapping("/models/model")
    public ResponseEntity<?> saveModel(@RequestBody Model md) throws SQLException {
        LOG.info("开始执行 createModel 方法.");
        return this.modelService.saveModel(md);
    }

    @PostMapping("/retrieveModelList")
    private RestResponse retrieveModelList() throws Exception {
        return modelService.getModelList();
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
     * 获取全部标签
     * @return
     */
    @PostMapping(value = "/modelList")
    public RetrieveModelListResponse  rtrvModelList() {
        return this.modelService.rtrvModelList();
    }

}
