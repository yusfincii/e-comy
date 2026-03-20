package com.ecom.user.users.service;

import com.ecom.user.users.api.dto.UsersRequestDTO;
import com.ecom.user.users.api.dto.UsersResponseDTO;
import com.ecom.user.users.enumeration.UserType;
import com.ecom.user.users.exception.UserNotFoundException;
import com.ecom.user.users.mapper.UsersMapper;
import com.ecom.user.users.persistence.Users;
import com.ecom.user.users.persistence.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapper;

    @Override
    public List<UsersResponseDTO> getAllUsers() {
        return mapper.toResponseDTOList(repository.findAll());
    }

    @Override
    public UsersResponseDTO getUserById(UUID id) {
        return mapper.toResponseDTO(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    @Transactional
    public UsersResponseDTO createUser(UsersRequestDTO usersDTO, UUID createdBy) {
        Users created = mapper.toEntity(usersDTO);
        created.setCreateTime(LocalDateTime.now());
        created.setCreatedBy(createdBy);
        return mapper.toResponseDTO(repository.save(created));
    }

    @Override
    @Transactional
    public UsersResponseDTO updateUser(UUID id, UsersRequestDTO usersDTO, UUID updatedBy) {
        Users updated = repository.findById(id).orElseThrow(UserNotFoundException::new);
        updated.setUserType(UserType.valueOf(usersDTO.getUserType().toUpperCase(Locale.ROOT)));
        updated.setEmail(usersDTO.getEmail());
        updated.setPassword(usersDTO.getPassword());
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
