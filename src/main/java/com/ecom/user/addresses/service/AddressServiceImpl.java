package com.ecom.user.addresses.service;

import com.ecom.user.addresses.api.dto.AddressRequestDTO;
import com.ecom.user.addresses.api.dto.AddressResponseDTO;
import com.ecom.user.addresses.exception.AddressNotFoundException;
import com.ecom.user.addresses.mapper.AddressMapper;
import com.ecom.user.addresses.persistence.repository.AddressRepository;
import com.ecom.user.users.exception.UserNotFoundException;
import com.ecom.user.users.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;
    private final UsersService usersService;

    @Override
    public AddressResponseDTO getAddressById(UUID addressId) {
        return mapper.toResponseDTO(repository.findById(addressId)
        .orElseThrow(AddressNotFoundException::new));
    }

    @Override
    public List<AddressResponseDTO> getAddressByUserId(UUID userId) {
        if(usersService.isExistUserById(userId)){
            throw new UserNotFoundException();
        }
        return mapper.toResponseDTOList(repository.findByUserId(userId));
    }

    @Override
    @Transactional
    public AddressResponseDTO createAddress(AddressRequestDTO createDTO, UUID createdBy) {
        return mapper.toResponseDTO(repository.save(mapper.toAddress(createDTO)));
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(UUID addressId, AddressRequestDTO updateDTO, UUID updatedBy) {
        return null;
    }

    @Override
    @Transactional
    public void deleteAddress(UUID addressId, UUID deletedBy) {
        int deletedCount = repository.deleteIfExistsById(addressId);
        if (deletedCount == 0) {
            throw new AddressNotFoundException();
        }
    }
}
