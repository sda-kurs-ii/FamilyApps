package com.sda.group.family_apps.user;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class UserAddress {
    private String zipCode;
    private String city;
    private Countries country;
    private String street;
}
