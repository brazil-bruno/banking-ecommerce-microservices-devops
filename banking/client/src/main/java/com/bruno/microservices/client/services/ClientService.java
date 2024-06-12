package com.bruno.microservices.client.services;

import com.bruno.microservices.client.dto.ClientDTO;
import com.bruno.microservices.client.dto.ClientNewDTO;
import com.bruno.microservices.client.entities.Address;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.feignclients.AddressFeignClient;
import com.bruno.microservices.client.repositories.ClientRepository;
import com.bruno.microservices.client.services.exceptions.DataBaseException;
import com.bruno.microservices.client.services.exceptions.ResourceNotFoundException;
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
public class ClientService {

    private final ClientRepository clientRepository;

    private final AddressFeignClient addressFeignClient;

    public Page<ClientNewDTO> findAllClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> new ClientNewDTO(client));
    }

    public ClientDTO createNewClient(ClientDTO clientDTO) {
        Address address = Address.builder().publicArea(clientDTO.getPublicArea()).addressNumber(clientDTO.getAddressNumber()).complement(clientDTO.getClientName()).neighborhood(clientDTO.getNeighborhood()).zipCode(clientDTO.getZipCode()).city(clientDTO.getCity()).state(clientDTO.getState()).build();
        address = addressFeignClient.createNewAddress(address);

        Client client = Client.builder().clientName(clientDTO.getClientName()).clientEmail(clientDTO.getClientEmail()).clientPhone(clientDTO.getClientPhone()).addressID(address.getAddressID()).createdAt(new Date()).build();
        clientRepository.save(client);
        return new ClientDTO(client, address);
    }

    public ClientNewDTO findClientById(UUID clientID) {
        Optional<Client> client = clientRepository.findById(clientID);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Client id not found!"));
        return new ClientNewDTO(entity);
    }

    public ClientNewDTO updateClient(ClientNewDTO clientNewDTO, UUID clientID) {
        try {
            Client client = clientRepository.getOne(clientID);
            copyDtoToEntity(clientNewDTO, client);
            client = clientRepository.save(client);
            return new ClientNewDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client id not found: " + clientID);
        }
    }

    public void deleteClientById(UUID clientID) {
        Optional<Client> client = clientRepository.findById(clientID);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Client id not found!"));
        addressFeignClient.deleteAddressById(entity.getAddressID());
        clientRepository.deleteById(clientID);
    }

    private void copyDtoToEntity(ClientNewDTO clientNewDTO, Client client) {
        client.setClientEmail(clientNewDTO.getClientEmail());
        client.setClientPhone(clientNewDTO.getClientPhone());
        client.setUpdatedAt(new Date());
    }
}
