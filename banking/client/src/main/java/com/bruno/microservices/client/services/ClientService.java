package com.bruno.microservices.client.services;

import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client createNewClient(Client client) {
        Client entity = Client.builder()
                .clientID(UUID.randomUUID().toString())
                .clientName(client.getClientName())
                .clientEmail(client.getClientEmail())
                .clientPhone(client.getClientPhone())
                .build();
        return clientRepository.save(entity);
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
