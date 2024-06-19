package com.bruno.microservices.client.controllers;

import com.bruno.microservices.client.dto.ClientAddressDTO;
import com.bruno.microservices.client.dto.ClientInsertDTO;
import com.bruno.microservices.client.dto.ClientUpdateDTO;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import com.bruno.microservices.client.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(value = "/find-all")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientDTO> findAllClients(Pageable pageable) {
        return clientService.findAllClients(pageable);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createNewClient(@RequestBody @Valid ClientInsertDTO clientInsertDTO) {
        return clientService.createNewClient(clientInsertDTO);
    }

    @GetMapping(value = "/find-by-id/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    public ClientAddressDTO findClientById(@PathVariable UUID clientID) {
        return clientService.findClientById(clientID);
    }

    @GetMapping(value = "/find-by-email")
    @ResponseStatus(HttpStatus.OK)
    public Client findClientByEmail(@RequestParam String email) {
        return clientService.findClientByEmail(email);
    }

    @PutMapping(value = "/update/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ClientDTO updateClient(@RequestBody @Valid ClientUpdateDTO clientUpdateDTO, @PathVariable UUID clientID) {
        return clientService.updateClient(clientUpdateDTO, clientID);
    }

    @DeleteMapping(value = "/delete/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable UUID clientID) {
        clientService.deleteClientById(clientID);
    }
}
