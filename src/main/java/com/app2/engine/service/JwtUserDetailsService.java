package com.app2.engine.service;


import com.app2.engine.entity.app.AppUser;
import com.app2.engine.repository.EmployeeRepository;
import com.app2.engine.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new CustomException("User not found with username: " + username,"203");
        }
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

}