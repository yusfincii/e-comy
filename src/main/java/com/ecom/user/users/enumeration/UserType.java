package com.ecom.user.users.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    ADMIN(1L, 1L),
    CUSTOMER(2L, 2L),
    SELLER(3L, 3L);

    private final Long id;
    private final Long code;
}
