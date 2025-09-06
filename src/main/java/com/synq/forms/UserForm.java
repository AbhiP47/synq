package com.synq.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min=3, message = "Min 3 characters are required")
    private String name;
    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "password is required")
    @Size(message= "minimun 6 characters required")
    private String password;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    @NotBlank(message = "About is required")
    @Size(max=250, message = "maximum character limit is 250")
    private String about;
}
