package com.ecom.user.favourites.mapper;

import com.ecom.user.favourites.api.dto.FavouritesDTO;
import com.ecom.user.favourites.persistence.entity.Favourites;
import com.ecom.user.favourites.persistence.entity.FavouritesId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouritesMapper {

    @Mapping(source = "id.productId", target = "productId")
    FavouritesDTO toDTO(Favourites favourites);

    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "id.userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Favourites toEntity(FavouritesDTO dto);

    List<FavouritesDTO> toDTOList(List<Favourites> favouritesList);

    List<Favourites> toEntityList(List<FavouritesDTO> dtoList);
}

