package com.ecom.user.users.api.dto;

import com.ecom.user.users.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {

    private UUID id;
    private UserType type;
    private String email;
}
