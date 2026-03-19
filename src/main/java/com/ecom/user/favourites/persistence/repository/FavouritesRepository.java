package com.ecom.user.favourites.persistence.repository;

import com.ecom.user.favourites.persistence.entity.Favourites;
import com.ecom.user.favourites.persistence.entity.FavouritesId;
import com.ecom.user.users.persistence.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavouritesRepository extends JpaRepository<Favourites, FavouritesId> {

    List<Favourites> findByUserId(UUID userId);

    List<Favourites> findByProductId(UUID productId);

    Optional<Favourites> findByUserIdAndProductId(UUID userId, UUID productId);

    UUID user(Users user);
}
