package com.ecom.user.users.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersErrorType {

    USER_NOT_FOUND(1L);

    private final Long code;
}
