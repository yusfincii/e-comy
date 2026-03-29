package com.ecom.user.addresses.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AddressResponseDTO {

    private UUID userId;

    private String address;

    private String status;
}
