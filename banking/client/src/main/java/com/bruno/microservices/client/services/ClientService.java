package com.bruno.microservices.client.services;

import com.bruno.microservices.client.dto.ClientDTO;
import com.bruno.microservices.client.entities.Address;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.feignclients.AddressFeignClient;
import com.bruno.microservices.client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final AddressFeignClient addressFeignClient;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client createNewClient(ClientDTO clientDTO) {
        Address address = Address.builder()
                .addressID(UUID.randomUUID().toString())
                .publicArea(clientDTO.getPublicArea())
                .addressNumber(clientDTO.getAddressNumber())
                .complement(clientDTO.getClientName())
                .neighborhood(clientDTO.getNeighborhood())
                .zipCode(clientDTO.getZipCode())
                .city(clientDTO.getCity())
                .state(clientDTO.getState())
                .build();
        addressFeignClient.createNewAddress(address);

        Client client = Client.builder()
                .clientID(UUID.randomUUID().toString())
                .clientName(clientDTO.getClientName())
                .clientEmail(clientDTO.getClientEmail())
                .clientPhone(clientDTO.getClientPhone())
                .addressID(address.getAddressID())
                .build();
        return clientRepository.save(client);
    }

    public Client findClientById(String clientID) {
        return clientRepository.findById(clientID).get();
    }

    public Client updateClient(Client client, String clientID) {
        Client entity = clientRepository.findById(clientID).get();
        entity.setClientEmail(client.getClientEmail());
        entity.setClientPhone(client.getClientPhone());
        return clientRepository.save(entity);
    }

    public void deleteClientById(String clientID) {
        clientRepository.deleteById(clientID);
    }
}
