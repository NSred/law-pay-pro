package com.lawagency.lawly.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
}
