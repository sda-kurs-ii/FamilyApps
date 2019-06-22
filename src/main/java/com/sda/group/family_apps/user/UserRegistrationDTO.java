package com.sda.group.family_apps.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationDTO {

    @Size(min=3, max=15, message = "imie powinno miec od 3 do 15 znakow")
    private String username;
    @Email
    private String preferEmails;
    private String password;
    private String birthDate;
    private String zipCode;
    private String city;
    private String street;
    private Countries country;
    private String avatar;
}
