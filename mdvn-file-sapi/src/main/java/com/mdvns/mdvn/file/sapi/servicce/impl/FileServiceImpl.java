package com.mdvns.mdvn.file.sapi.servicce.impl;


import com.mdvns.mdvn.common.utils.RestResponseUtil;
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

@Service
public class FileServiceImpl implements FileService {
    /*初始化日志常量*/
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    /*注入repository*/
    @Autowired
    private AttchInfoRepository attchRepository;

    /*注入AttchInfo*/
    @Autowired
    private AttchInfo attch;

    /**
     * 保存上传成功的附件的信息
     * @param attchInfo
     * @return
     */
    public ResponseEntity<?> createAttchInfo(AttchInfo attchInfo) {
        //附件原名，上传人Id， 附件url 不能为空
        if (StringUtils.isEmpty(attchInfo.getOriginName())||StringUtils.isEmpty(attchInfo.getCreatorId())||StringUtils.isEmpty(attchInfo.getUrl())) {
        }
        //实例化附件信息添加时间*
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        //附件信息添加时间赋值为系统当前时间*
        attchInfo.setCreateTime(createTime);
        //附件信息是否已删除赋值为0，未删除
        attchInfo.setIsDeleted(0);
        //保存附件信息
        attch = this.attchRepository.save(attchInfo);

        return RestResponseUtil.successResponseEntity(attch);
    }
}
