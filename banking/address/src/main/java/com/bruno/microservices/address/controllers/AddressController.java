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

    @GetMapping(value = "/find-all")
    @ResponseStatus(HttpStatus.OK)
    public Page<AddressDTO> findAllAddresses(Pageable pageable) {
        return addressService.findAllAddresses(pageable);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO createNewAddress(@RequestBody @Valid AddressDTO addressDTO) {
        return addressService.createNewAddress(addressDTO);
    }

    @GetMapping(value = "/find-by-id/{addressID}")
    public AddressDTO findAddressById(@PathVariable UUID addressID) {
        return addressService.findAddressById(addressID);
    }

    @GetMapping(value = "/find-address-by-client-id/{clientID}")
    public AddressDTO findAddressByClientId(@PathVariable UUID clientID) {
        return addressService.findAddressByClientId(clientID);
    }

    @PutMapping(value = "/update/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AddressDTO updateAddress(@RequestBody @Valid AddressDTO addressDTO, @PathVariable UUID addressID) {
        return addressService.updateAddress(addressDTO, addressID);
    }

    @DeleteMapping(value = "/delete/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@PathVariable UUID addressID) {
        addressService.deleteAddressById(addressID);
    }

    @DeleteMapping(value = "/delete-address-by-client-id/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressByClientId(@PathVariable UUID clientID) {
        addressService.deleteAddressByClientId(clientID);
    }
}
