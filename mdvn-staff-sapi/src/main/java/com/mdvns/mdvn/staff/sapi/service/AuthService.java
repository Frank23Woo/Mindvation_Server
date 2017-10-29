package com.mdvns.mdvn.staff.sapi.service;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest);
}
