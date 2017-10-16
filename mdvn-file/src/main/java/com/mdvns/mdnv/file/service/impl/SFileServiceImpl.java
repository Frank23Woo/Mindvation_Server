package com.mdvns.mdnv.file.service.impl;

import com.mdvns.mdnv.file.domain.entity.AttchUrl;
import com.mdvns.mdnv.file.repository.AttchUrlRepository;
import com.mdvns.mdnv.file.service.SFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SFileServiceImpl implements SFileService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(SFileServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private AttchUrlRepository attchUrlRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AttchUrl attchUrl;

    @Override
    public ResponseEntity<?> getFiles(String belongTo) {
        List<AttchUrl> attchUrls = new ArrayList<AttchUrl>();
        attchUrls.add(new AttchUrl("S2","http://ahgo/fagjlqwltji.jpg"));
        attchUrls.add(new AttchUrl("S2","http://ahgo/fagjlqwltji.jpg"));
        return ResponseEntity.ok(attchUrls);
    }

    @Override
    public ResponseEntity<?> uploadFiles(HttpServletRequest request, MultipartFile[] files) throws IOException {
        //上传目录
        String uploadDir = request.getSession().getServletContext().getRealPath("/") + "/upload";
        //如果目录不存在，自动创建文件夹
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i] != null) {
                this.executeUpload(uploadDir, files[i]);
            }
        }

        return null;
    }


    /**
     * 文件上传公共方法
     * @param uploadDir 文件上传目录
     * @param mFile 文件对象
     */
    private String executeUpload(String uploadDir, MultipartFile mFile) throws IOException {

        //原文件名
        String fileOrigName = mFile.getOriginalFilename();
        //文件后缀名
        String suffix = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        //重命名上传文件
        //String fileName = UUID.randomUUID()+suffix;
        String fileName = System.nanoTime()+suffix;
        File file = new File(uploadDir, fileName);
        //将上传的文件写入到服务器端文件内
        mFile.transferTo(file);
        return fileName;
    }

    /**
     * 创建时保存附件信息
     *
     * @param request
     * @return
     */
    @Override
    public List<AttchUrl> saveAttchUrls(List<AttchUrl> request) {
        for (int i = 0; i < request.size(); i++) {
            request.get(i).setIsDeleted(0);
        }
        List<AttchUrl> attchUrls = attchUrlRepository.save(request);
        return attchUrls;
    }
    /**
     * 更改附件信息
     *
     * @param list
     * @return
     */
    @Override
    public List<AttchUrl> updateAttchUrls(List<AttchUrl> list) {
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
        List<AttchUrl> attchUrlList = this.attchUrlRepository.findAttchUrls(belongTo);
        LOG.info("finish executing updateAttchUrls()方法.", this.CLASS);
        return attchUrlList;
    }
}
