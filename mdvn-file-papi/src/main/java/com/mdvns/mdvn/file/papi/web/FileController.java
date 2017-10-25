package com.mdvns.mdvn.file.papi.web;

import com.mdvns.mdvn.file.papi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.papi.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = {"/files", "/V1.0/files"})
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    /*注入SFileService*/
    @Autowired
    private FileService fileService;

    /**
     * 多文件上传处理
     *
     * @param request
     * @param mFile
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public ResponseEntity<?> uploadFile(HttpServletRequest request, @RequestParam String subjectId, @RequestParam MultipartFile mFile, @RequestParam String creatorId) throws IOException {
        LOG.info("Contrller 开始执行:{}");
        return this.fileService.uploadFile(request, subjectId, mFile, creatorId);
    }

    @PostMapping(value = "/healthCheck/{checkCode}")
    public ResponseEntity<?> healthCheck(@PathVariable String checkCode) {
        return this.fileService.healthCheck(checkCode);
    }

    /**
     * 更改附件信息(删除)
     *
     * @param
     * @return
     */
    @PostMapping(value = "/deleteAttch")
    public ResponseEntity<?> updateAttch(@RequestBody UpdateAttchRequest updateAttchRequest) {

        return this.fileService.updateAttch(updateAttchRequest);
    }
}
