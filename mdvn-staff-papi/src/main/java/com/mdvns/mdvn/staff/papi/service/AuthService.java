package com.mdvns.mdvn.staff.papi.service;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RemoveAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvStaffAuthInfoRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest);

    ResponseEntity<?> rtrvAuth(RtrvStaffAuthInfoRequest rtrvAuthRequest);

    ResponseEntity<?> removeAuth(RemoveAuthRequest removeAuthRequest);
}
