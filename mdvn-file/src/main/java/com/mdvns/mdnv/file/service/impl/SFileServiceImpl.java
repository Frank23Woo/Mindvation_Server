package com.mdvns.mdnv.file.service.impl;

import com.mdvns.mdnv.file.domain.entity.AttchUrl;
import com.mdvns.mdnv.file.repository.AttchUrlRepository;
import com.mdvns.mdnv.file.service.SFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private AttchUrlRepository attchUrlRepository;

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
}
