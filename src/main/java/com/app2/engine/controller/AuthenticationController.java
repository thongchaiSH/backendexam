package com.app2.engine.controller;


import com.app2.engine.aop.annotation.Loggable;
import com.app2.engine.model.JwtRequest;
import com.app2.engine.model.ResponseModel;
import com.app2.engine.service.AuthenticateService;
import com.app2.engine.service.JwtUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    JwtUserDetailsService userDetailsService;

    static final String TOKEN_PREFIX = "Bearer";

    @Loggable
    @PostMapping("/login")
    public ResponseModel login(@RequestBody JwtRequest request) {
        Map result = new HashMap();
        result.put("username", request.getUsername());
        result.put("access_token", TOKEN_PREFIX + " " + authenticateService.authenticate(request.getUsername(), request.getPassword()));
        return new ResponseModel("Generate Access successfully.", result);
    }
}
