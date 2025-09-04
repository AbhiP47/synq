package com.synq.forms;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;
}
