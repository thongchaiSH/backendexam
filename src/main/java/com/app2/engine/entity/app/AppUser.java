package com.app2.engine.entity.app;

import com.app2.engine.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
public class AppUser extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    private String firstName;   //ชื่อ
    @NotNull
    private String lastName;    //นามสกุล
    private String address;     //ที่อยู่

    @Email
    @NotNull
    private String email;       //Email

    @NotNull
    @Column(unique = true,length = 10)
    private String phoneNumber; //เบอร์โทร

    @Positive
    @NotNull
    private Double salary;      //เงินเดือน

    @NotNull
    @Column(unique = true,length = 12)
    private String refCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberType")
    private MemberType memberType;

}
