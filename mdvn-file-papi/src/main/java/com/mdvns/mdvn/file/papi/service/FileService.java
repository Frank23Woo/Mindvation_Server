package com.mdvns.mdvn.file.papi.service;

import com.mdvns.mdvn.file.papi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.papi.domain.UploadFileRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface FileService {
//    ResponseEntity<?> getFiles(String belongTo);
    ResponseEntity<?> uploadFiles(HttpServletRequest request, List<MultipartFile> mFiles, String creatorId) throws IOException;
    ResponseEntity<?> uploadFile(HttpServletRequest request, MultipartFile mFile, String creatorId) throws IOException;


    ResponseEntity<?> update(UpdateAttchRequest updateAttchRequest);

    ResponseEntity<?> delete(Integer attchId);

    ResponseEntity<?> retrieve(Integer id);

    ResponseEntity<?> retrieve(String ids);

   /* //创建时保存附件信息
    List<AttachUrl> saveAttchUrls(List<AttachUrl> request);
    //更改时保存附件信息
    List<AttachUrl> updateAttchUrls(List<AttachUrl> attchUrls);*/

}
