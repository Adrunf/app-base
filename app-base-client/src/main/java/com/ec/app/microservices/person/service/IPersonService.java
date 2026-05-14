package com.ec.app.microservices.person.service;

import com.ec.app.microservices.person.PersonVO;

import java.util.List;

/**
 * @author arobayo
 */
public interface IPersonService {
    List<PersonVO> findAll();
}
