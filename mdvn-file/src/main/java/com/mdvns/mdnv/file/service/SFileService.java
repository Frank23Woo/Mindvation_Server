package com.mdvns.mdnv.file.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface SFileService {
    ResponseEntity<?> getFiles(String belongTo);
    ResponseEntity<?> uploadFiles(HttpServletRequest request, MultipartFile[] files) throws IOException;
}
