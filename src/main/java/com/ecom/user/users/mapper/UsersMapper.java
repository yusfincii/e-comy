package com.ecom.user.users.mapper;

import com.ecom.user.users.api.dto.UsersDTO;
import com.ecom.user.users.enumeration.UserType;
import com.ecom.user.users.persistence.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(source = "userType", target = "type", qualifiedByName = "userTypeToLong")
    UsersDTO toDTO(Users users);

    @Mapping(source = "type", target = "userType", qualifiedByName = "longToUserType")
    Users toEntity(UsersDTO dto);

    List<UsersDTO> toDTOList(List<Users> usersList);

    List<Users> toEntityList(List<UsersDTO> dtoList);

    @Named("userTypeToLong")
    default Long userTypeToLong(UserType userType) {
        if (userType == null) {
            return null;
        }
        return userType.getCode();
    }

    @Named("longToUserType")
    default UserType longToUserType(Long code) {
        if (code == null) {
            return null;
        }
        
        return Arrays.stream(UserType.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    default UserType findUserTypeByEnumId(Long id) {
        if (id == null) {
            return null;
        }
        
        return Arrays.stream(UserType.values())
                .filter(type -> type.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
