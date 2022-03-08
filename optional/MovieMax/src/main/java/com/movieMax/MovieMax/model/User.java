package com.movieMax.MovieMax.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class User {
    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "[a-zA-Z]+", message = "Only alphabetic characters allowed")
    @Size(max = 25, message = "Maximum size is 25")
    private String name;

    @NotNull(message = "You must enter an email")
    @Email
    private String email;

    @NotNull(message = "You must enter a phone number")
    @Size(min = 10, max = 10, message = "Should be length 10")
    private String phoneNumber;

    @NotNull(message = "You must enter a city")
    @Pattern(regexp = "[a-zA-Z]+", message = "Only alphabetic characters allowed")
    @Size(max = 15, message = "Maximum size is 15")
    private String city;
}
