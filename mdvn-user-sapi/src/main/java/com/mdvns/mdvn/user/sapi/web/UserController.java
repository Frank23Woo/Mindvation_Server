package com.mdvns.mdvn.user.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.user.sapi.domain.User;
import com.mdvns.mdvn.user.sapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/users", "/v1.0/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return this.userService.findById(id);
    }

    @GetMapping(value = "/user/{account}")
    public ResponseEntity<?> findByAccount(@PathVariable String account) {
        return this.userService.findByAccount(account);
    }

}
