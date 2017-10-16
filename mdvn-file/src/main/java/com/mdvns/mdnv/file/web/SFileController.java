package com.mdvns.mdnv.file.web;

import com.mdvns.mdnv.file.domain.entity.AttchUrl;
import com.mdvns.mdnv.file.service.SFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<?> uploads(HttpServletRequest request, MultipartFile[] files) throws IOException {

        return this.fileService.uploadFiles(request, files);
    }

    @PostMapping(value = "/{belongTo}")
    public ResponseEntity<?> getFiles(@PathVariable String belongTo) {
        return this.fileService.getFiles(belongTo);
    }

    /**
     * 保存附件信息(创建)
     * @param request
     * @return
     */
    @PostMapping(value="/saveAttchUrls")
    public List<AttchUrl> saveAttchUrls(@RequestBody List<AttchUrl> request){
        List<AttchUrl> attchUrls = fileService.saveAttchUrls(request);
        return attchUrls;
    }

    /**
     * 更改附件信息
     * @param attchUrls
     * @return
     */
    @PostMapping(value="/updateAttchUrls")
    public List<AttchUrl> updateAttchUrls(@RequestBody List<AttchUrl> attchUrls){
        List<AttchUrl> attchUrlList = this.fileService.updateAttchUrls(attchUrls);
        return attchUrlList;
    }

}
