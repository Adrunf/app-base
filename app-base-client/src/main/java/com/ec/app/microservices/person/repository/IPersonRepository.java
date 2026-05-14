package com.ec.app.microservices.person.repository;

import com.ec.app.microservices.config.IQueryDslBaseRepository;
import com.ec.app.microservices.person.PersonVO;
import com.ec.app.microservices.person.entity.PersonEntity;

import java.util.List;

/**
 * @author arobayo
 */
public interface IPersonRepository extends IQueryDslBaseRepository<PersonEntity> {
    List<PersonVO> findAll();
}
