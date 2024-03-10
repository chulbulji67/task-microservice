package com.example.tasksubmissionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private long id;

    private String email;
    private String fullName;
    private String password;
    private String role;
}
