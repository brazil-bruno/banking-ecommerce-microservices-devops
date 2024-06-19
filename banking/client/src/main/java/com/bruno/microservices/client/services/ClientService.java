package com.bruno.microservices.client.services;

import com.bruno.microservices.client.dto.*;
import com.bruno.microservices.client.entities.Address;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.entities.Role;
import com.bruno.microservices.client.feignclients.AddressFeignClient;
import com.bruno.microservices.client.repositories.ClientRepository;
import com.bruno.microservices.client.repositories.RoleRepository;
import com.bruno.microservices.client.services.exceptions.ResourceNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AddressFeignClient addressFeignClient;

    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;

    public Page<ClientDTO> findAllClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> new ClientDTO(client));
    }

    public ClientDTO createNewClient(ClientInsertDTO clientInsertDTO) {
        Client client = new Client();
        copyDtoToEntityNew(clientInsertDTO, client);
        client.setPassword(bCryptPasswordEncoder.encode(clientInsertDTO.getPassword()));
        client.setCreatedAt(new Date());
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    public ClientAddressDTO findClientById(UUID clientID) {
        Optional<Client> client = clientRepository.findById(clientID);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        try {
            Address address = addressFeignClient.findAddressByClientId(entity.getClientID());
            return new ClientAddressDTO(entity, address);
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Address not found");
        }
    }

    public Client findClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return client;
    }

    public ClientDTO updateClient(ClientUpdateDTO clientUpdateDTO, UUID clientID) {
        try {
            Client client = clientRepository.getOne(clientID);
            copyDtoToEntity(clientUpdateDTO, client);
            client.setPassword(bCryptPasswordEncoder.encode(clientUpdateDTO.getPassword()));
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client not found");
        }
    }

    public void deleteClientById(UUID clientID) {
        Optional<Client> client = clientRepository.findById(clientID);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        try {
            addressFeignClient.deleteAddressByClientId(entity.getClientID());
        } catch (FeignException e) {
            throw new ResourceNotFoundException("Address not found");
        }
        clientRepository.deleteById(entity.getClientID());
    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client client) {
        client.setEmail(clientDTO.getEmail());
        client.setClientPhone(clientDTO.getClientPhone());
        client.setUpdatedAt(new Date());

        client.getRoles().clear();
        for (RoleDTO roleDTO : clientDTO.getRoles()) {
            Role role = roleRepository.getOne(roleDTO.getRoleID());
            client.getRoles().add(role);
        }
    }

    private void copyDtoToEntityNew(ClientDTO clientDTO, Client client) {
        client.setClientName(clientDTO.getClientName());
        client.setEmail(clientDTO.getEmail());
        client.setClientPhone(clientDTO.getClientPhone());

        client.getRoles().clear();
        for (RoleDTO roleDTO : clientDTO.getRoles()) {
            Role role = roleRepository.getOne(roleDTO.getRoleID());
            client.getRoles().add(role);
        }
    }
}


