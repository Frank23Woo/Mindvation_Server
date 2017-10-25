package com.mdvns.mdvn.file.papi.web;

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
     * @param request
     * @param files
     * @return
     */
    @PostMapping(value = "/uploadFiles/{creatorId}")
    public ResponseEntity<?> uploads(HttpServletRequest request, @RequestParam List<MultipartFile> files, @PathVariable String creatorId) throws IOException {
        LOG.info("Contrller 开始执行:{}",files.size());
        return this.fileService.uploadFiles(request, files, creatorId);
    }

    @PostMapping(value = "/healthCheck/{checkCode}")
    public ResponseEntity<?> healthCheck(@PathVariable String checkCode) {
        return this.fileService.healthCheck(checkCode);
    }
    /*
        @PostMapping(value = "/{belongTo}")
        public ResponseEntity<?> getFiles(@PathVariable String belongTo) {
            return this.fileService.getFiles(belongTo);
        }

        /**
         * 保存附件信息(创建)
         * @param request
         * @return
         */
   /*
    @PostMapping(value="/saveAttchUrls")
    public List<AttachUrl> saveAttchUrls(@RequestBody List<AttachUrl> request){
        List<AttachUrl> attchUrls = fileService.saveAttchUrls(request);
        return attchUrls;
    }

    /**
     * 更改附件信息
     * @param attchUrls
     * @return
     */
  /*
   @PostMapping(value="/updateAttchUrls")
    public List<AttachUrl> updateAttchUrls(@RequestBody List<AttachUrl> attchUrls){
        List<AttachUrl> attchUrlList = this.fileService.updateAttchUrls(attchUrls);
        return attchUrlList;
    }

   @RequestMapping("/fqdn")
    public String generUrl() throws UnknownHostException {
        return this.fileService.genUrl();
    }*/

}
