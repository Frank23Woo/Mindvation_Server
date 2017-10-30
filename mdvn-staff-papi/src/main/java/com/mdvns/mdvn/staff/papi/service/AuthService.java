package com.mdvns.mdvn.staff.papi.service;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvAuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest);

    ResponseEntity<?> rtrvAuth(RtrvAuthRequest rtrvAuthRequest);
}
