package com.bruno.microservices.address.services;

import com.bruno.microservices.address.dto.AddressDTO;
import com.bruno.microservices.address.entities.Address;
import com.bruno.microservices.address.repositories.AddressRepository;
import com.bruno.microservices.address.services.exceptions.DataBaseException;
import com.bruno.microservices.address.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Page<AddressDTO> findAllAddresses(Pageable pageable) {
        Page<Address> addresses = addressRepository.findAll(pageable);
        return addresses.map(address -> new AddressDTO(address));
    }

    public AddressDTO createNewAddress(AddressDTO addressDTO) {
        Address address = Address.builder()
                .publicArea(addressDTO.getPublicArea())
                .addressNumber(addressDTO.getAddressNumber())
                .complement(addressDTO.getComplement())
                .neighborhood(addressDTO.getNeighborhood())
                .zipCode(addressDTO.getZipCode())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .createdAt(new Date())
                .build();
        addressRepository.save(address);
        return new AddressDTO(address);
    }

    public AddressDTO findAddressById(UUID addressID) {
        Optional<Address> address = addressRepository.findById(addressID);
        Address entity = address.orElseThrow(() -> new ResourceNotFoundException("Address id not found!"));
        return new AddressDTO(entity);
    }

    public AddressDTO updateAddress(AddressDTO addressDTO, UUID addressID) {
        try {
            Address address = addressRepository.getOne(addressID);
            copyDtoToEntity(addressDTO, address);
            address = addressRepository.save(address);
            return new AddressDTO(address);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Address id not found: " + addressID);
        }
    }

    public void deleteAddressById(UUID addressID) {
        try {
            addressRepository.deleteById(addressID);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Address id not found: " + addressID);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation!.");
        }
    }

    private void copyDtoToEntity(AddressDTO addressDTO, Address address) {
        address.setPublicArea(addressDTO.getPublicArea());
        address.setAddressNumber(addressDTO.getAddressNumber());
        address.setComplement(addressDTO.getComplement());
        address.setNeighborhood(addressDTO.getNeighborhood());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(address.getCity());
        address.setState(addressDTO.getState());
        address.setUpdatedAt(new Date());
    }
}
