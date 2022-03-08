package com.it.rs.ResSysMVC.model;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class User {
    @NotNull(message = "UserId must not be blank")
    @Size(min = 4, max = 15, message = "UserId must be between 4 to 15 Characters.")
    private String userId;

    @NotNull(message = "Password must not be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 Characters.")
    private String password;

    @NotNull(message = "Name must not be blank")
    @Size(min = 4, max = 15, message = "Name must be between 4 to 15 Characters.")
    private String name;

    @NotNull(message = "City must not be blank.")
    private String city;

    @NotNull(message = "Email must not be blank." )
    @Email
    private String email;

    @NotNull(message = "PhoneNumber must not be blank")
    @Size(min = 10, max = 10, message = "PhoneNumber must be 10 digits")
    private String phone;


}
