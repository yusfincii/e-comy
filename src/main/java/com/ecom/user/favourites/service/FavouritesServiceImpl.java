package com.ecom.user.favourites.service;

import com.ecom.user.favourites.api.dto.FavouritesDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public class FavouritesServiceImpl implements FavouritesService{

    @Override
    public List<FavouritesDTO> findByUserId(UUID userId) {
        return List.of();
    }

    @Override
    public List<FavouritesDTO> findByProductId(UUID productId) {
        return List.of();
    }

    @Override
    @Transactional
    public void createFavourite(FavouritesDTO createDTO) {


    }

    @Override
    @Transactional
    public void deleteFavourite(FavouritesDTO deleteDTO) {

    }

}
