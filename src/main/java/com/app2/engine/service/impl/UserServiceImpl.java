package com.app2.engine.service.impl;

import com.app2.engine.entity.app.AppUser;
import com.app2.engine.entity.app.MemberType;
import com.app2.engine.model.EmployeeDTO;
import com.app2.engine.repository.EmployeeRepository;
import com.app2.engine.repository.custom.MemberTypeRepositoryCustom;
import com.app2.engine.service.UserService;
import com.app2.engine.util.AuthorizeUtil;
import com.app2.engine.util.CustomException;
import com.app2.engine.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthorizeUtil authorizeUtil;

    @Autowired
    private MemberTypeRepositoryCustom memberTypeRepositoryCustom;

    @Override
    @Transactional
    public AppUser register(EmployeeDTO employeeDTO) {

        //validate duplicate user
        if (employeeRepository.findByUsername(employeeDTO.getUsername()) != null) {
            throw new CustomException("Information entered is incorrect.", "202", "Username has already been used.");
        }

        AppUser appUser = modelMapper.map(employeeDTO, AppUser.class);
        appUser.setRefCode(this.generateRefCode(DateUtil.getCurrentDate(), appUser.getPhoneNumber()));
        appUser.setPassword(bcryptEncoder.encode(employeeDTO.getPassword()));

        Pattern digit = Pattern.compile("[0-9]{10}");
        if (!digit.matcher(appUser.getPhoneNumber()).find()) {
            throw new CustomException("Information entered is incorrect.", "202", "Phone number is 0-9 and 10 digit.");
        }
        //validate duplicate user
        if (employeeRepository.findByPhoneNumber(appUser.getPhoneNumber()) != null) {
            throw new CustomException("Information entered is incorrect.", "202", "Phone number has already been used.");
        }

        //validate
        if (appUser.getSalary() < 15000) {
            throw new CustomException("Salary is lower than required.", "201", "Salary below 15000.");
        }
        String memberTypeCode;
        if(appUser.getSalary()>50000){
            memberTypeCode="01"; //Platinum
        }else if(appUser.getSalary()>30000){
            memberTypeCode="02"; //Gold
        }else{
            memberTypeCode="03"; //Silver
        }

        MemberType memberType= memberTypeRepositoryCustom.findByCode(memberTypeCode);
        appUser.setMemberType(memberType);

        employeeRepository.save(appUser);
        return appUser;
    }

    @Override
    public AppUser getUser() {
        String user = authorizeUtil.getUsername();
        log.debug("User : {}", user);
        return employeeRepository.findByUsername(user);
    }

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.US);

    private String generateRefCode(Date registerDate, String phoneNumber) {
        String result = DATE_FORMAT.format(registerDate) + phoneNumber.substring(phoneNumber.length() - 4);
        return result;
    }
}
