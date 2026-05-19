package com.ec.app.microservices.person.repository;

import com.ec.app.microservices.config.IQueryDslBaseRepository;
import com.ec.app.microservices.person.PersonDTO;
import com.ec.app.microservices.person.entity.PersonEntity;

import java.util.List;

/**
 * @author arobayo
 */
public interface IPersonRepository extends IQueryDslBaseRepository<PersonEntity> {
    List<PersonDTO> findAll();

    PersonDTO findById(String identification);

    List<PersonDTO> findByStatus(Integer status);
}
