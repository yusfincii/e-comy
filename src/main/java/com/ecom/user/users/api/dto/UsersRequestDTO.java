package com.ecom.user.users.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    private UUID id;
    private String type;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Please enter a valid password!")
    @Size(max = 255, message = "Email can not be larger than 255 char!")
    private String email;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 8, max = 72, message = "Password length must be between 8-72 character!")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;
}
