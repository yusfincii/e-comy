package com.ecom.user.addresses.api.dto;

import com.ecom.user.addresses.enumeration.AddressStatus;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressRequestDTO {

    private UUID userId;

    @Size(min = 3, max = 512, message = "Address can not be larger than 512 characters!")
    @NotBlank(message = "Address can not be blank!")
    private String address;

    // ignore whitespace characters on leading and following address
    @JsonSetter
    public void setAddress(String address) {
        this.address = address != null ? address.trim() : null;
    }

    private AddressStatus status;
}
