package com.mdvns.mdvn.file.sapi.web;

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

    @PostMapping
    public ResponseEntity<?> createAttchInfo(@RequestBody AttchInfo attchInfo) {

        return this.fileService.createAttchInfo(attchInfo);
    }

    @PostMapping(value = "/healthCheck/{checkCode}")
    public ResponseEntity<?> healthCheck(@PathVariable String checkCode) {
        return ResponseEntity.ok(checkCode);
    }


}
