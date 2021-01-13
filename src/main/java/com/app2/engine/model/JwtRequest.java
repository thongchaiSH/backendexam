package com.app2.engine.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest {

    private String username;
    private String password;
}