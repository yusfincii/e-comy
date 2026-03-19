package com.ecom.user.favourites.service;

import com.ecom.user.favourites.api.dto.FavouritesDTO;
import com.ecom.user.favourites.exception.FavouriteNotFoundException;
import com.ecom.user.favourites.mapper.FavouritesMapper;
import com.ecom.user.favourites.persistence.entity.Favourites;
import com.ecom.user.favourites.persistence.repository.FavouritesRepository;
import com.ecom.user.users.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class FavouritesServiceImpl implements FavouritesService{

    private final FavouritesRepository repository;
    private final FavouritesMapper mapper;

    @Override
    public List<FavouritesDTO> findByUserId(UUID userId) {
        return mapper.toDTOList(repository.findByUserId(userId));
    }

    // can use for favourite rate for a product - quantity
    @Override
    public List<FavouritesDTO> findByProductId(UUID productId) {
        return mapper.toDTOList(repository.findByProductId(productId));
    }

    @Override
    @Transactional
    public void createFavourite(FavouritesDTO createDTO, UUID userId) {
        Favourites created = mapper.toEntity(createDTO);
        created.getId().setUserId(userId);
        created.setCreateTime(LocalDateTime.now());
        repository.save(created);
    }

    @Override
    @Transactional
    public void deleteFavourite(UUID userId, UUID productId) {
        Favourites deleted = repository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(FavouriteNotFoundException::new);
        repository.delete(deleted);
    }

}
