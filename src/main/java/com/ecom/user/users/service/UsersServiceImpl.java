package com.ecom.user.users.service;

import com.ecom.user.users.api.dto.UsersRequestDTO;
import com.ecom.user.users.api.dto.UsersResponseDTO;
import com.ecom.user.users.enumeration.UserType;
import com.ecom.user.users.exception.InvalidUserTypeException;
import com.ecom.user.users.exception.UserFoundWithEmailException;
import com.ecom.user.users.exception.UserNotFoundException;
import com.ecom.user.users.mapper.UsersMapper;
import com.ecom.user.users.persistence.Users;
import com.ecom.user.users.persistence.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public List<UsersResponseDTO> getAllUsers() {
        return mapper.toResponseDTOList(repository.findAll());
    }

    @Override
    public UsersResponseDTO getUserById(UUID id) {
        return mapper.toResponseDTO(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    // Fields checks according to the business logic
    private void userCreateControls(UsersRequestDTO usersDTO){
        // user type check
        if(!UserType.getNames().contains(usersDTO.getUserType().trim().toUpperCase())){
            throw new InvalidUserTypeException();
        }

        // exist e-mail check
        if(repository.existsUsersByEmail(usersDTO.getEmail().trim())){
            throw new UserFoundWithEmailException();
        }
    }

    @Override
    @Transactional
    public UsersResponseDTO createUser(UsersRequestDTO usersDTO, UUID createdBy) {
        userCreateControls(usersDTO);
        Users created = mapper.toEntity(usersDTO, encoder);
        created.setCreateTime(LocalDateTime.now());
        created.setCreatedBy(createdBy);
        return mapper.toResponseDTO(repository.save(created));
    }

    // Fields checks according to the business logic and existing user
    private void userUpdateControls(UsersRequestDTO usersDTO, Users entity){
        // user type check
        if(!UserType.getNames().contains(usersDTO.getUserType().trim().toUpperCase())){
            throw new InvalidUserTypeException();
        }

        // not exist e-mail check
        if(!entity.getEmail().equals(usersDTO.getEmail().trim())){ // case - mail address update
            if(repository.existsUsersByEmail(usersDTO.getEmail().trim())){
                throw new UserFoundWithEmailException();
            }
        }
    }

    @Override
    @Transactional
    public UsersResponseDTO updateUser(UUID id, UsersRequestDTO usersDTO, UUID updatedBy) {
        Users updated = repository.findById(id).orElseThrow(UserNotFoundException::new);
        userUpdateControls(usersDTO, updated);
        mapper.updateEntityFromDto(usersDTO, updated, encoder);
        updated.setUpdateTime(LocalDateTime.now());
        updated.setUpdatedBy(updatedBy);
        return mapper.toResponseDTO(repository.save(updated));
    }

    @Override
    @Transactional
    public void deleteUser(UUID id, UUID userId) {
        Users user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setIsDeleted(true);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdatedBy(userId);
        repository.save(user);
    }
}
