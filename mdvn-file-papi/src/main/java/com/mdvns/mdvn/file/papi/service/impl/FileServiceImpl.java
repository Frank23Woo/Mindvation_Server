package com.mdvns.mdvn.file.papi.service.impl;


import com.mdvns.mdvn.file.papi.config.WebConfig;
import com.mdvns.mdvn.file.papi.domain.AttchInfo;
import com.mdvns.mdvn.file.papi.domain.UploadFileRequest;
import com.mdvns.mdvn.file.papi.service.FileService;
import com.mdvns.mdvn.file.papi.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    /*注入RestTemplate*/
//    private RestTemplate restTemplate;

    /*附件保存目录*/
    @Value("${web.upload-path}")
    private String uploadDir;

    @Autowired
    private AttchInfo attch;

   /* @Autowired
    private AttchUrlRepository attchUrlRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AttachUrl attchUrl;

    @Override
    public ResponseEntity<?> getFiles(String belongTo) {
        List<AttachUrl> attchUrls = new ArrayList<AttachUrl>();
        attchUrls.add(new AttachUrl("S2","http://ahgo/fagjlqwltji.jpg"));
        attchUrls.add(new AttachUrl("S2","http://ahgo/fagjlqwltji.jpg"));
        return ResponseEntity.ok(attchUrls);
    }*/

    /**
     *
     * @param request
     * @param files
     * @param creatorId
     * @return
     * @throws IOException
     */
    @Transactional
    @Override
    public ResponseEntity<?> uploadFiles(HttpServletRequest request, List<MultipartFile> files, String creatorId) throws IOException {

        //保存成功的附件信息Id使用List保存
        List<AttchInfo> attchs = new ArrayList<AttchInfo>();

        //文件依次上传
        try {
            for (MultipartFile file : files) {
                attchs.add(uploadFile(request, file, creatorId));
            }
        } catch (Exception ex) {
            LOG.info("文件上传失败:{}", ex.getLocalizedMessage());
            throw new IOException("文件上传失败");
        }

        return ResponseEntity.ok(attchs);
    }

    @Override
    public ResponseEntity<?> healthCheck(String checkCode) {
        ResponseEntity<?> responseEntity = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.postForEntity("http://localhost:10021/mdvn-file-sapi/files/healthCheck/haha", checkCode, String.class);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return responseEntity;
    }

    /**
     * @param request
     * @param mFile   文件对象
     * @return 文件信息保存后的Id
     * @throws IOException
     */
    private AttchInfo uploadFile(HttpServletRequest request, MultipartFile mFile, String creatorId) throws IOException {

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
        attch.setOriginName(fileOrigName);
        attch.setCreatorId(creatorId);
        attch.setUrl(url);
        //调用SAPI保存实例化AttchInfo对象
        String saveAttchInfoUrl = "http://localhost:10021/mdvn-file-sapi/files";
        LOG.info("========保存附件信息开始========, URL 为：{}", saveAttchInfoUrl);
        ResponseEntity<AttchInfo> responseEntity = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.postForEntity(saveAttchInfoUrl, attch, AttchInfo.class);
        } catch (Exception ex) {
            LOG.error("保存信息失败:{}",ex.getLocalizedMessage());
        }
        LOG.info("========保存成功========{}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    /**
     * 创建时保存附件信息
     *
     * @param request
     * @return
     */
   /* @Override
    public List<AttachUrl> saveAttchUrls(List<AttachUrl> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<AttachUrl> attchUrls = attchUrlRepository.save(request);
        return attchUrls;
    }*/
    /**
     * 更改附件信息
     *
     * @param list
     * @return
     */
  /*  @Override
    public List<AttachUrl> updateAttchUrls(List<AttachUrl> list) {
        LOG.info("start executing updateAttchUrls()方法.", this.CLASS);
        if (list == null || list.size() <= 0) {
            throw new NullPointerException("getAttchUrls List is empty");
        }
        List<String> urlList = new ArrayList();
        String belongTo = null;
        //将数据库中没有的插入
        for (int i = 0; i < list.size(); i++) {
            belongTo = list.get(i).getBelongTo();
            urlList.add(list.get(i).getUrl());
            attchUrl = this.attchUrlRepository.findByBelongToAndUrl(belongTo, list.get(i).getUrl());
            //不存在的加上
            if (attchUrl == null) {
                list.get(i).setBelongTo(belongTo);
                list.get(i).setIsDeleted(0);
                this.attchUrlRepository.saveAndFlush(list.get(i));
            } else {
                //之前是附件后来改掉，数据库中存在记录，但是is_deleted为1，需要修改成0
                if (attchUrl.getIsDeleted().equals(1)) {
                    String sql = "UPDATE attch_url SET is_deleted= 0 WHERE belong_to=" + "\"" + belongTo + "\"" + "AND url =" + "\"" + list.get(i).getUrl() + "\"" + "";
                    this.jdbcTemplate.update(sql);
                }
            }
        }
        //将数据库中将要删除的附件信息修改is_deleted状态
        //数组转化为字符串格式
        StringBuffer attchUrls = new StringBuffer();
        for (int i = 0; i < urlList.size(); i++) {
            attchUrls.append("\"" + urlList.get(i) + "\"");
            attchUrls.append(",");
        }
        String aUrls = attchUrls.substring(0, attchUrls.length() - 1);
        String sql = "UPDATE attch_url SET is_deleted= 1 WHERE belong_to= " + "\"" + belongTo + "\"" + " AND url NOT IN (" + aUrls + ")";
        this.jdbcTemplate.update(sql);
        //查询数据库中有效的附件
        List<AttachUrl> attchUrlList = this.attchUrlRepository.findAttchUrls(belongTo);
        LOG.info("finish executing updateAttchUrls()方法.", this.CLASS);
        return attchUrlList;
    }*/

//    public void setUploadDir(String uploadDir) {
//        this.uploadDir = uploadDir;
//    }
}
