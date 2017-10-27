package com.mdvns.mdvn.file.papi.service.impl;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.file.papi.config.WebConfig;
import com.mdvns.mdvn.file.papi.domain.AttchInfo;
import com.mdvns.mdvn.file.papi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.papi.service.FileService;
import com.mdvns.mdvn.file.papi.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    /*SAPI的URL信息*/
    private WebConfig webConfig;

    /*附件保存目录*/
    @Value("${web.upload-path}")
    private String uploadDir;

    @Autowired
    private AttchInfo attchInfo;

    private final RestTemplate restTemplate;
    public FileServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 多文件上传
     * @param request
     * @param mFiles
     * @param creatorId
     * @return
     * @throws IOException
     */
    @Transactional
    @Override
    public ResponseEntity<?> uploadFiles(HttpServletRequest request, List<MultipartFile> mFiles, String creatorId) throws IOException {

        //保存成功的附件信息Id使用List保存
        List<AttchInfo> attchs = new ArrayList<AttchInfo>();

        //文件依次上传
        try {
            for (MultipartFile mFile : mFiles) {
                attchs.add(doUpload(request, mFile, creatorId));
            }
        } catch (Exception ex) {
            LOG.info("文件上传失败:{}", ex.getLocalizedMessage());
            throw new IOException("文件上传失败");
        }

        return RestResponseUtil.successResponseEntity(attchs);
    }

    /**
     * 单文件上传
     * @param request
     * @param mFile
     * @param creatorId
     * @return
     * @throws IOException
     */
    @Transactional
    @Override
    public ResponseEntity<?> uploadFile(HttpServletRequest request, MultipartFile mFile, String creatorId) throws IOException {
        AttchInfo attch = doUpload(request, mFile, creatorId);
        return RestResponseUtil.successResponseEntity(attch);
    }

    /**
     * 更新
     * @param updateAttchRequest
     * @return
     */
    @Override
    public ResponseEntity<?> update(UpdateAttchRequest updateAttchRequest) {

        String updateAttchUrl = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(updateAttchUrl, updateAttchRequest);
        } catch (Exception ex) {
            LOG.error("跟新附件：{}, 失败:{}", updateAttchRequest.getAttchId(), ex.getLocalizedMessage());
        }

        return RestResponseUtil.successResponseEntity();
    }

    /**
     * 删除附件
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<?> delete(Integer id) {

        String deleteAttchUrl = "http://localhost:10019/mdvn-file-sapi/files/file/"+id;

        restTemplate.put(deleteAttchUrl.toString(), id);
        return RestResponseUtil.successResponseEntity();
    }

    /**
     * 查询附件详情
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> retrieve(Integer id) {
        String retrieveUrl = "http://localhost:10019/mdvn-file-sapi/files/file/"+id;
        LOG.info("URLwei：{}", retrieveUrl);
        ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(retrieveUrl, RestResponse.class);
        return responseEntity;
    }

    @Override
    public ResponseEntity<?> retrieve(String ids) {

        //构建调用SAPI的Url
        String retrieveAttchsUrl = "http://localhost:10019/mdvn-file-sapi/files/"+ids;
        LOG.info("获取附件列表的Url：{}", retrieveAttchsUrl);
        //调用SAPI
        ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(retrieveAttchsUrl, RestResponse.class);

        return responseEntity;
    }

    /**
     * @param request
     * @param mFile   文件对象
     * @return 文件信息保存后的Id
     * @throws IOException
     */
    private AttchInfo doUpload(HttpServletRequest request, MultipartFile mFile, String creatorId) throws IOException {

        //如果目录不存在，自动创建文件夹
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //原文件名
        String fileOrigName = mFile.getOriginalFilename();
        //文件后缀名
        String suffix = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        //重命名上传文件
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + System.nanoTime() + suffix;
        //将上传的文件写入到服务器端文件内
        mFile.transferTo(new File(uploadDir, fileName));
        //文件上传成功后，生成url
        String url = FileUtil.genUrl(request, fileName);
        //实例化AttchInfo对象
        attchInfo.setOriginName(fileOrigName);
        attchInfo.setCreatorId(creatorId);
        attchInfo.setUrl(url);
        //调用SAPI保存实例化AttchInfo对象
        return create(attchInfo);
    }

    /**
     * 调用Sapi保存AttchInfo
     * @param attch
     * @return
     */
    private AttchInfo create(AttchInfo attch) {

        String saveAttchInfoUrl = "http://localhost:10019/mdvn-file-sapi/files";
        LOG.info("========保存附件信息开始========, URL 为：{}", saveAttchInfoUrl);
        ResponseEntity<RestResponse<AttchInfo>> responseEntity = null;
        try {
            ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<RestResponse<AttchInfo>>() {
            };
            //调用SAPI保存
            responseEntity = restTemplate.exchange(saveAttchInfoUrl, HttpMethod.POST, new HttpEntity<>(attch), parameterizedTypeReference);
        } catch (Exception ex) {
            LOG.error("保存信息失败:{}", ex.getLocalizedMessage());
        }
        LOG.info("========保存成功========{}", responseEntity.getBody());
        return responseEntity.getBody().getResponseBody();
    }

}
