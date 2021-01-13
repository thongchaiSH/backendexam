package com.app2.engine.repository;

import com.app2.engine.entity.app.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    AppUser findByRefCode(String refCode);

    AppUser findByPhoneNumber(String phoneNumber);
}
