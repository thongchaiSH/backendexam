package com.app2.engine.service.impl;

import com.app2.engine.service.AuthenticateService;
import com.app2.engine.service.JwtUserDetailsService;
import com.app2.engine.spring.jwt.JwtTokenUtil;
import com.app2.engine.util.CustomException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Override
    public String authenticate(String username, String password) {
        this.authen(username, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @SneakyThrows
    private void authen(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new CustomException("USER_DISABLED", "202");
        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid credentials.", "201","Username or password incorrect.");
        }
    }
}
