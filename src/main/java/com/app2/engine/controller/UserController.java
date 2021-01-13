package com.app2.engine.controller;

import com.app2.engine.aop.annotation.Loggable;
import com.app2.engine.entity.app.AppUser;
import com.app2.engine.model.EmployeeDTO;
import com.app2.engine.model.ResponseModel;
import com.app2.engine.service.UserService;
import com.app2.engine.util.AuthorizeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthorizeUtil authorizeUtil;

    @Autowired
    UserService userService;

    @Loggable
    @PostMapping("/register")
    public ResponseModel register(@RequestBody EmployeeDTO user) {
        AppUser appUser = userService.register(user);
        return new ResponseModel("Register success.", appUser);
    }

    @GetMapping
    public ResponseModel getUser() {
        return new ResponseModel("success.", userService.getUser());
    }

}
