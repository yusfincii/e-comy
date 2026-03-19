package com.ecom.user.favourites.mapper;

import com.ecom.user.favourites.api.dto.FavouritesDTO;
import com.ecom.user.favourites.persistence.entity.Favourites;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouritesMapper {

    @Mapping(source = "id.productId", target = "productId")
    FavouritesDTO toDTO(Favourites favourites);

    @Mapping(target = "id.productId", source = "productId")
    Favourites toEntity(FavouritesDTO dto);

    List<FavouritesDTO> toDTOList(List<Favourites> favouritesList);

    List<Favourites> toEntityList(List<FavouritesDTO> dtoList);
}

