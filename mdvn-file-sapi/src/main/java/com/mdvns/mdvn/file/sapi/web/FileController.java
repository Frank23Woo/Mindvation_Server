package com.mdvns.mdvn.file.sapi.web;

import com.mdvns.mdvn.file.sapi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.sapi.domain.entity.AttchInfo;
import com.mdvns.mdvn.file.sapi.servicce.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/files", "/v1.0/files"})
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 添加附件
     * @param attchInfo
     * @return
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttchInfo attchInfo) {

        return this.fileService.create(attchInfo);
    }


    public ResponseEntity<?> update(@RequestBody UpdateAttchRequest updateAttchRequest) {
        return this.fileService.update(updateAttchRequest);
    }

    /**
     * 删除附件
     * @param id
     * @return
     */
    @PutMapping(value = "/file/{attchId}")
    public ResponseEntity<?> delete(@PathVariable("attchId") Integer id) {
        return this.fileService.delete(id);
    }


    /**
     * 查询指定Id的附件详情
     * @param id
     * @return
     */
    @GetMapping(value = "/file/{attchId}")
    public ResponseEntity<?> retrieve(@PathVariable("attchId") Integer id) {
        return this.fileService.retrieve(id);
    }


    @GetMapping(value = "/{ids}")
    public ResponseEntity<?> retrieve(@PathVariable String ids) {
        return this.fileService.retrieve(ids);
    }
}
