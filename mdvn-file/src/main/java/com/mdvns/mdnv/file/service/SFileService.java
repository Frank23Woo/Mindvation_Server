package com.mdvns.mdnv.file.service;

import com.mdvns.mdnv.file.domain.entity.AttachUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface SFileService {
    ResponseEntity<?> getFiles(String belongTo);
    ResponseEntity<?> uploadFiles(HttpServletRequest request, MultipartFile[] files) throws IOException;

    //创建时保存附件信息
    List<AttachUrl> saveAttchUrls(List<AttachUrl>  request);
    //更改时保存附件信息
    List<AttachUrl> updateAttchUrls(List<AttachUrl> attchUrls);

}
