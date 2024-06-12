package com.bruno.microservices.client.controllers;

import com.bruno.microservices.client.dto.ClientDTO;
import com.bruno.microservices.client.dto.ClientNewDTO;
import com.bruno.microservices.client.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientNewDTO> findAllClients(Pageable pageable) {
        return clientService.findAllClients(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createNewClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.createNewClient(clientDTO);
    }

    @GetMapping("/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    public ClientNewDTO findClientById(@PathVariable UUID clientID) {
        return clientService.findClientById(clientID);
    }

    @PutMapping("/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ClientNewDTO updateClient(@RequestBody @Valid ClientNewDTO clientNewDTO, @PathVariable UUID clientID) {
        return clientService.updateClient(clientNewDTO, clientID);
    }

    @DeleteMapping("/{clientID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable UUID clientID) {
        clientService.deleteClientById(clientID);
    }
}
