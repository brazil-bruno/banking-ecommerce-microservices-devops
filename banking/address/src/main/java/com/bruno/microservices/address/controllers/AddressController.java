package com.bruno.microservices.address.controllers;

import com.bruno.microservices.address.dto.AddressDTO;
import com.bruno.microservices.address.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AddressDTO> findAllAddresses(Pageable pageable) {
        return addressService.findAllAddresses(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO createNewAddress(@RequestBody @Valid AddressDTO addressDTO) {
        return addressService.createNewAddress(addressDTO);
    }

    @GetMapping("/{addressID}")
    public AddressDTO findAddressById(@PathVariable UUID addressID) {
        return addressService.findAddressById(addressID);
    }

    @PutMapping("/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AddressDTO updateAddress(@RequestBody @Valid AddressDTO addressDTO, @PathVariable UUID addressID) {
        return addressService.updateAddress(addressDTO, addressID);
    }

    @DeleteMapping("/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@PathVariable UUID addressID) {
        addressService.deleteAddressById(addressID);
    }
}
