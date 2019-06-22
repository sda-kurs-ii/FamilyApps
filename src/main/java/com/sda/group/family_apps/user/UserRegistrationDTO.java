package com.sda.group.family_apps.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationDTO {

    @Size(min=3, max=15, message = "imie powinno miec od 3 do 15 znakow")
    private String firstName;
    private String lastName; //TODO uzupełnić walidację
    private String birthDate;
//    @Pattern(regexp = "\\d{11}")
    private String pesel;
    @Email
    private String username;
    private String password;
    private String phone;
    private boolean preferEmails;
    private String zipCode;
    private String city;
    private Countries country;
    private String street;
}
