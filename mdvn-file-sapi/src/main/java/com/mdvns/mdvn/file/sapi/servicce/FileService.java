package com.mdvns.mdvn.file.sapi.servicce;

import com.mdvns.mdvn.file.sapi.domain.UpdateAttchRequest;
import com.mdvns.mdvn.file.sapi.domain.entity.AttchInfo;
import org.springframework.http.ResponseEntity;

public interface FileService {

    ResponseEntity<?> create(AttchInfo attchInfo);

    ResponseEntity<?> update(UpdateAttchRequest updateAttchRequest);

    ResponseEntity<?> delete(Integer attchId);

    ResponseEntity<?> retrieve(Integer id);

    ResponseEntity<?> retrieve(String ids);

    AttchInfo rtrvAttachInfo(Integer id);
}
