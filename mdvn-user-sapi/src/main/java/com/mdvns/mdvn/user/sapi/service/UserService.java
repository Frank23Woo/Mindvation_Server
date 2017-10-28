package com.mdvns.mdvn.user.sapi.service;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> findById(Integer id);

    ResponseEntity<?> findByAccount(String accouont);
}
