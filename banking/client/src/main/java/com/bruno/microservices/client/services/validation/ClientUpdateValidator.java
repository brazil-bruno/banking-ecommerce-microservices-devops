package com.bruno.microservices.client.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bruno.microservices.client.controllers.exceptions.FieldMessage;
import com.bruno.microservices.client.dto.ClientUpdateDTO;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdateValid, ClientUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdateValid ann) {
    }

    @Override
    public boolean isValid(ClientUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UUID clientID = UUID.fromString(uriVars.get("clientID"));

        List<FieldMessage> list = new ArrayList<>();

        Client client = clientRepository.findByEmail(dto.getEmail());
        if (client != null && clientID != client.getClientID()) {
            list.add(new FieldMessage("email", "Exists email!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
