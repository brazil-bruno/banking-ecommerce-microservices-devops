package com.bruno.microservices.address.controllers;

import com.bruno.microservices.address.entities.Address;
import com.bruno.microservices.address.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Address> findAllAddresses() {
        return addressService.findAllAddresses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createNewAddress(@RequestBody Address address) {
        return addressService.createNewAddress(address);
    }

    @GetMapping("/{addressID}")
    public Address findAddressById(@PathVariable UUID addressID) {
        return addressService.findAddressById(addressID);
    }

    @PutMapping("/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Address updateAddress(@RequestBody Address address, @PathVariable UUID addressID) {
        return addressService.updateAddress(address, addressID);
    }

    @DeleteMapping("/{addressID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@PathVariable UUID addressID) {
        addressService.deleteAddressById(addressID);
    }
}
