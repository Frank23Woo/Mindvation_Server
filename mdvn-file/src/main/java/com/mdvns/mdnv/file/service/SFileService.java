package com.mdvns.mdnv.file.service;

import com.mdvns.mdnv.file.domain.entity.AttchUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface SFileService {
    ResponseEntity<?> getFiles(String belongTo);
    ResponseEntity<?> uploadFiles(HttpServletRequest request, MultipartFile[] files) throws IOException;

    //创建时保存附件信息
    List<AttchUrl> saveAttchUrls(List<AttchUrl>  request);
    //更改时保存附件信息
    List<AttchUrl> updateAttchUrls(List<AttchUrl> attchUrls);
}
