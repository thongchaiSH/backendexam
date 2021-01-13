package com.app2.engine.service;

import com.app2.engine.entity.app.AppUser;
import com.app2.engine.model.EmployeeDTO;

public interface UserService {
    AppUser register(EmployeeDTO employeeDTO);
    AppUser getUser();
}
