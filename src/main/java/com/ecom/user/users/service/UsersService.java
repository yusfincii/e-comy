package com.ecom.user.users.service;

import com.ecom.user.users.api.dto.UsersRequestDTO;
import com.ecom.user.users.api.dto.UsersResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    List<UsersResponseDTO> getAllUsers();

    UsersResponseDTO getUserById(UUID id);

    UsersResponseDTO createUser(UsersRequestDTO usersDTO, UUID createdBy);

    UsersResponseDTO updateUser(UUID id, UsersRequestDTO usersDTO, UUID updatedBy);

    void deleteUser(UUID id, UUID deletedBy);

}
