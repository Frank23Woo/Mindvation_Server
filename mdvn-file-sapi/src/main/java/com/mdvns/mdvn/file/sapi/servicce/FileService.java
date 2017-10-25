package com.mdvns.mdvn.file.sapi.servicce;

import com.mdvns.mdvn.file.sapi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.sapi.domain.entity.AttchInfo;
import org.springframework.http.ResponseEntity;

public interface FileService {

    ResponseEntity<?> createAttchInfo(AttchInfo attchInfo);

    ResponseEntity<?> updateAttch(UpdateAttchRequest updateAttchRequest);
}
