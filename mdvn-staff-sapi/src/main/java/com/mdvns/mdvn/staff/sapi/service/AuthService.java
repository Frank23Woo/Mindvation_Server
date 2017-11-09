package com.mdvns.mdvn.staff.sapi.service;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvStaffAuthInfoRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest);

    ResponseEntity<?> rtrvAuth(RtrvStaffAuthInfoRequest rtrvAuthRequest);


    ResponseEntity<?> removeAllAuth(String projId, String hierarchyId);
}
