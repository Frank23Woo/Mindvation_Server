package com.mdvns.mdvn.user.papi.web;


import com.mdvns.mdvn.user.papi.domain.LonginRequest;
import com.mdvns.mdvn.user.papi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/users","/v1.0/users"})
@CrossOrigin
public class UserController {

    /*注入UserService*/
	@Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LonginRequest loginRequest) {
    	System.out.print(loginRequest);
        return this.userService.login(loginRequest);
    }


    @PostMapping(value = "/user/healthCheck")
    public String healthCheck() {
        return "Hello World!";
    }

}
