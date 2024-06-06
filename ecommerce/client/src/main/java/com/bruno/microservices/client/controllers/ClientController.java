package com.bruno.microservices.client.controllers;

import com.bruno.microservices.client.dto.ClientDTO;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findAllClients() {
        return clientService.findAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createNewClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createNewClient(clientDTO);
    }

    @GetMapping("/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    public Client findClientById(@PathVariable String clientID) {
        return clientService.findClientById(clientID);
    }

    @PutMapping("/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Client updateClient(@RequestBody Client client, @PathVariable String clientID) {
        return clientService.updateClient(client, clientID);
    }

    @DeleteMapping("/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable String clientID) {
        clientService.deleteClientById(clientID);
    }
}
