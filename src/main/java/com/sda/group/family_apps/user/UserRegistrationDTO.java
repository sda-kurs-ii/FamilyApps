package com.sda.group.family_apps.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationDTO {

    @Size(min=3, max=15, message = "Nick powinien miec od 3 do 15 znakow")
    private String username;
    @Email(message = "Niepoprawny e-mail")
    private String preferEmails;
    @ValidPassword
    private String password;
    @Pattern(regexp = "([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}", message = "Użyj formatu dd/mm/yyyy")
    private String birthDate;
    @Pattern(regexp = "([0-9]{2}-[0-9]{3})", message = "Błędny kod")
    private String zipCode;
    private String city;
    private String street;
    private Countries country;
    private String avatar;
}
