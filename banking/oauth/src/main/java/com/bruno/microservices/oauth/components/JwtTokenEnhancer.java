package com.bruno.microservices.oauth.components;

import com.bruno.microservices.oauth.entities.Client;
import com.bruno.microservices.oauth.feignclients.ClientFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenEnhancer implements TokenEnhancer {

    private final ClientFeignClient clientFeignClient;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Client client = clientFeignClient.findClientByEmail(authentication.getName());

        Map<String, Object> map = new HashMap<>();

        map.put("clientName", client.getClientName());
        map.put("clientID", client.getClientID());

        DefaultOAuth2AccessToken toke = (DefaultOAuth2AccessToken) accessToken;
        toke.setAdditionalInformation(map);

        return accessToken;

    }

}
