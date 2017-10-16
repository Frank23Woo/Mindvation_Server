package com.mdvns.mdnv.file.web;

import com.mdvns.mdnv.file.service.SFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping(value = {"/files", "/V1.0/files"})
public class SFileController {

    /*注入SFileService*/
    @Autowired
    private SFileService fileService;


    /**
     * 多文件上传处理
     * @param request
     * @param files
     * @return
     */
    public ResponseEntity<?> uploads(HttpServletRequest request, MultipartFile[] files) {

        return this.fileService.uploadFiles(request, files);
    }

    @PostMapping(value = "/{belongTo}")
    public ResponseEntity<?> getFiles(@PathVariable String belongTo) {
        return this.fileService.getFiles(belongTo);
    }

}
