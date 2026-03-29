package com.ecom.user.addresses.api;

import com.ecom.user.addresses.service.AddressService;
import com.ecom.user.addresses.api.dto.AddressRequestDTO;
import com.ecom.user.addresses.api.dto.AddressResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> getByAddressId(@PathVariable UUID addressId){
        return new ResponseEntity<>(service.getAddressById(addressId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponseDTO>> getByUserId(@PathVariable UUID userId){
        return new ResponseEntity<>(service.getAddressByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody @Validated AddressRequestDTO createDTO,
                                                            UUID createdBy){
        return new ResponseEntity<>(service.createAddress(createDTO, createdBy), HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable UUID addressId,
                                                            @RequestBody @Validated AddressRequestDTO updateDTO,
                                                            UUID updatedBy){
        return new ResponseEntity<>(service.updateAddress(addressId, updateDTO, updatedBy), HttpStatus.OK);

    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID addressId,
                                                            UUID deletedBy){
        service.deleteAddress(addressId, deletedBy);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
