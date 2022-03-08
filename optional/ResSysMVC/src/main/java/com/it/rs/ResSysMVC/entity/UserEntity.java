package com.it.rs.ResSysMVC.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "userid")
    private String userId;
    private String password;
    private String name;
    private String city;
    private String email;
    private String phone;

}
