package com.bruno.microservices.address.services;

import com.bruno.microservices.address.entities.Address;
import com.bruno.microservices.address.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Address createNewAddress(Address address) {
        Address entity = Address.builder()
                .publicArea(address.getPublicArea())
                .addressNumber(address.getAddressNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .state(address.getState())
                .build();
        return addressRepository.save(entity);
    }

    public Address findAddressById(UUID addressID) {
        return addressRepository.findById(addressID).get();
    }

    public Address updateAddress(Address address, UUID addressID) {
        Address entity = addressRepository.findById(addressID).get();
        entity.setPublicArea(address.getPublicArea());
        entity.setAddressNumber(address.getAddressNumber());
        entity.setComplement(address.getComplement());
        entity.setNeighborhood(address.getNeighborhood());
        entity.setZipCode(address.getZipCode());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        return addressRepository.save(entity);
    }

    public void deleteAddressById(UUID addressID) {
        addressRepository.deleteById(addressID);
    }
}
