package com.mdvns.mdvn.file.papi.web;

import com.mdvns.mdvn.file.papi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.papi.service.FileService;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ResponseEntity<?> uploadFile(HttpServletRequest request, @RequestParam MultipartFile mFile, @RequestParam String creatorId) throws IOException {
        LOG.info("Contrller 开始执行:{}");
        return this.fileService.uploadFile(request, mFile, creatorId);
    }

    /**
     * 删除附件
     * @param id
     * @return
     */
    @PostMapping(value = "/delete/{attchId}")
    public ResponseEntity<?> delete(@PathVariable("attchId") Integer id) {
        return this.fileService.delete(id);
    }

    /**
     * 获取制定Id的附件的详情
     * @param id
     * @return
     */
    @PostMapping(value = "/rtrvAttch/{attchId}")
    public ResponseEntity<?> retrieve(@PathVariable("attchId") Integer id) {
        return this.fileService.retrieve(id);
    }


    @PostMapping(value = "/rtrvAttchList/{attchIds}")
    public ResponseEntity<?> retrieve(@PathVariable("attchIds") @NotBlank(message = "id不能为空") String ids  ) {

        return this.fileService.retrieve(ids);
    }


    /**
     * 更改附件信息
     *
     * @param
     * @return
     */
    public ResponseEntity<?> update(@RequestBody UpdateAttchRequest updateAttchRequest) {

        return this.fileService.update(updateAttchRequest);
    }
}
