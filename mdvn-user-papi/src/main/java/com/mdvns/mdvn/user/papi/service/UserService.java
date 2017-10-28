package com.mdvns.mdvn.user.papi.service;

import com.mdvns.mdvn.user.papi.domain.LonginRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LonginRequest loginRequest);
}
