package com.ec.app.microservices.person.service;

import com.ec.app.microservices.person.PersonDTO;
import com.ec.app.microservices.person.repository.IPersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author arobayo
 */
@Lazy
@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class PersonService implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonDTO findByIdentification(String identification) {
        return personRepository.findById(identification);
    }
}
