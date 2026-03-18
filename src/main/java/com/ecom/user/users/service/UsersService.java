package com.ecom.user.users.service;

import com.ecom.user.users.api.dto.UsersDTO;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    List<UsersDTO> getAllUsers();

    UsersDTO getUserById(Long id);

    UsersDTO createUser(UsersDTO usersDTO, UUID createdBy);

    UsersDTO updateUser(Long id, UsersDTO usersDTO, UUID updatedBy);

    void deleteUser(Long id, UUID deletedBy);

}
