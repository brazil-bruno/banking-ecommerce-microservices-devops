package com.bruno.microservices.oauth.services;

import com.bruno.microservices.oauth.entities.Client;
import com.bruno.microservices.oauth.feignclients.ClientFeignClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientFeignClient clientFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientFeignClient.findClientByEmail(username);
        if (client == null) {
            logger.error("Email not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email found: " + username);
        return client;
    }
}


