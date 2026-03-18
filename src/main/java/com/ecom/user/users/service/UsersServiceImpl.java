package com.ecom.user.users.service;

import com.ecom.user.users.api.dto.UsersDTO;
import com.ecom.user.users.exception.UserNotFoundException;
import com.ecom.user.users.mapper.UsersMapper;
import com.ecom.user.users.persistence.Users;
import com.ecom.user.users.persistence.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;
    private UsersMapper mapper;

    @Override
    public List<UsersDTO> getAllUsers() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public UsersDTO getUserById(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    @Transactional
    public UsersDTO createUser(UsersDTO usersDTO, UUID createdBy) {
        Users created = mapper.toEntity(usersDTO);
        created.setCreateTime(LocalDateTime.now());
        created.setCreatedBy(createdBy);
        return mapper.toDTO(repository.save(created));
    }

    @Override
    @Transactional
    public UsersDTO updateUser(Long id, UsersDTO usersDTO, UUID updatedBy) {
        Users updated = repository.findById(id).orElseThrow(UserNotFoundException::new);
        updated = mapper.toEntity(usersDTO);
        updated.setUpdateTime(LocalDateTime.now());
        updated.setUpdatedBy(updatedBy);
        return mapper.toDTO(repository.save(updated));
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UUID userId) {
        Users user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setIsDeleted(true);
        user.setUpdateTime(LocalDateTime.now());
        user.setUpdatedBy(userId);
        repository.save(user);
    }
}
