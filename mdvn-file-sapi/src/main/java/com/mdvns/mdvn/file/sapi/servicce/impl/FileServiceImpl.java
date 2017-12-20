package com.mdvns.mdvn.file.sapi.servicce.impl;


import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.file.sapi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.sapi.domain.entity.AttchInfo;
import com.mdvns.mdvn.file.sapi.repository.AttchInfoRepository;
import com.mdvns.mdvn.file.sapi.servicce.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    /*初始化日志常量*/
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    /*注入repository*/
    @Autowired
    private AttchInfoRepository attchRepository;

    /*注入AttchInfo*/
    @Autowired
    private AttchInfo attchInfo;

    /**
     * 保存上传成功的附件的信息
     * @param attch
     * @return
     */
    public ResponseEntity<?> create(AttchInfo attch) {
        //附件原名，上传人Id， 附件url 不能为空
        if (StringUtils.isEmpty(attch.getOriginName())||StringUtils.isEmpty(attch.getCreatorId())||StringUtils.isEmpty(attch.getUrl())) {
        }
        //实例化附件信息添加时间*
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        //附件信息添加时间赋值为系统当前时间*
        attch.setCreateTime(createTime);
        //附件信息是否已删除赋值为0，未删除
        attch.setIsDeleted(0);
        //保存附件信息
        attchInfo = this.attchRepository.save(attch);

        return RestResponseUtil.successResponseEntity(attchInfo);
    }

    @Override
    public ResponseEntity<?> update(UpdateAttchRequest updateAttchRequest) {

        attchInfo = this.attchRepository.findOne(updateAttchRequest.getAttchId());
        if (!(updateAttchRequest.getSubjectid().equals(attchInfo.getSubjectId()))||attchInfo == null) {
            LOG.error("更新的附件不存在!");
        }
        attchInfo.setIsDeleted(1);
        attchInfo = this.attchRepository.saveAndFlush(attchInfo);
        return RestResponseUtil.successResponseEntity(attchInfo);
    }

    /**
     *删除附件
     * @param attchId
     * @return
     */
    @Override
    public ResponseEntity<?> delete(Integer attchId) {
        attchInfo = this.attchRepository.findOne(attchId);
        if(attchInfo==null){
            LOG.error("删除失败，Id 为：{} 的附件不存在!", attchId);
        }
        attchInfo.setIsDeleted(1);
        attchInfo = this.attchRepository.saveAndFlush(attchInfo);
        LOG.info("删除后Attch:{} 的状态为： {}", attchInfo.getId(), attchInfo.getIsDeleted());
        return RestResponseUtil.successResponseEntity();
    }

    /**
     * 根据Id获取附件信息
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> retrieve(Integer id) {
        attchInfo = this.attchRepository.findOne(id);
        LOG.info("查询到的AttchInfo 是： {}", attchInfo.getId());
        return RestResponseUtil.successResponseEntity(attchInfo);
    }

    /**
     * 根据Id获取附件信息
     * @param id
     * @return
     */
    @Override
    public AttchInfo rtrvAttachInfo(Integer id) {
        attchInfo = this.attchRepository.findOne(id);
        LOG.info("查询到的AttchInfo 是： {}", attchInfo.getId());
        return attchInfo;
    }



    /**
     * 根据指定Id集合查询多个附件信息
     * @param ids
     * @return
     */
    @Override
    public ResponseEntity<?> retrieve(String ids) {
        List<Integer> idList = new ArrayList<Integer>();
        for (String id:ids.split(",")) {
            idList.add(Integer.valueOf(id));
        }

        List<AttchInfo> attchs = this.attchRepository.findByIdIn(idList);

        return RestResponseUtil.successResponseEntity(attchs);
    }
}
