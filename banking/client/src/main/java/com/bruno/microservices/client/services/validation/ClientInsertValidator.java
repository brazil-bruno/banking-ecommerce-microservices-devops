package com.bruno.microservices.client.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bruno.microservices.client.controllers.exceptions.FieldMessage;
import com.bruno.microservices.client.dto.ClientInsertDTO;
import com.bruno.microservices.client.entities.Client;
import com.bruno.microservices.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator implements ConstraintValidator<ClientInsertValid, ClientInsertDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsertValid ann) {
    }

    @Override
    public boolean isValid(ClientInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Client client = clientRepository.findByEmail(dto.getEmail());

        if (client != null) {
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
