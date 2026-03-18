package com.ecom.user.users.persistence.repository;

import com.ecom.user.users.persistence.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    List<Users> findAll();

    Optional<Users> findById(UUID id);
}
