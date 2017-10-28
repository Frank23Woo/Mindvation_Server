package com.mdvns.mdvn.user.sapi.service.impl;

import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.user.sapi.domain.User;
import com.mdvns.mdvn.user.sapi.repository.UserRepository;
import com.mdvns.mdvn.user.sapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> findById(Integer id) {
        User user = this.userRepository.findOne(id);
        return RestResponseUtil.successResponseEntity(user);
    }

    @Override
    public ResponseEntity<?> findByAccount(String accouont) {
        User user = this.userRepository.findByAccount(accouont);

        return RestResponseUtil.successResponseEntity(user);
    }
}
