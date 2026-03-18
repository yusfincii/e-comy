package com.ecom.user.users.mapper;

import com.ecom.user.users.api.dto.UsersRequestDTO;
import com.ecom.user.users.api.dto.UsersResponseDTO;
import com.ecom.user.users.enumeration.UserType;
import com.ecom.user.users.persistence.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersResponseDTO toResponseDTO(Users users);

    @Mapping(source = "type", target = "userType", qualifiedByName = "stringToUserType")
    Users toEntity(UsersRequestDTO dto);

    List<UsersResponseDTO> toResponseDTOList(List<Users> usersList);


    @Named("stringToUserType")
    default UserType stringToUserType(String type) {
        if (type == null) {
            return null;
        }
        
        return Arrays.stream(UserType.values())
                .filter(userType -> userType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}
