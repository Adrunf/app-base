package com.ec.app.microservices.person.service;

import com.ec.app.microservices.person.PersonDTO;

import java.util.List;

/**
 * @author arobayo
 */
public interface IPersonService {
    List<PersonDTO> findAll();
    PersonDTO findByIdentification(String identification);
}
