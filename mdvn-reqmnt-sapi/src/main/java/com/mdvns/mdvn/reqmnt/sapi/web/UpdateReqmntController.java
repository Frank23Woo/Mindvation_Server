package com.mdvns.mdvn.reqmnt.sapi.web;

import com.mdvns.mdvn.reqmnt.sapi.domain.UpdateReqmntInfoRequest;
import com.mdvns.mdvn.reqmnt.sapi.service.IUpdateReqmntService;
import com.mdvns.mdvn.reqmnt.sapi.service.impl.UpdateReqmntServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateReqmntController {

    @Autowired
    private IUpdateReqmntService updateReqmntService;

    @PostMapping("/updateReqmntInfo")
    private ResponseEntity<?> updateReqmntInfo(UpdateReqmntInfoRequest request) throws Exception{
        Boolean result = updateReqmntService.updateReqmntInfo(request);
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
