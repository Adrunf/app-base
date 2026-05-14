package com.ec.app.microservices.person.repository;

import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.ec.app.microservices.person.PersonVO;
import com.ec.app.microservices.person.entity.PersonEntity;
import com.ec.app.microservices.person.entity.QPersonEntity;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author arobayo
 */
@Lazy
@Repository
public class PersonRepository extends JPAQueryDslBaseRepository<PersonEntity> implements IPersonRepository {

    public PersonRepository() {
        super(PersonEntity.class);
    }

    @Override
    public List<PersonVO> findAll() {
        QPersonEntity personEntity = new QPersonEntity("personEntity");
        return from(personEntity)
                .select(com.querydsl.core.types.Projections.bean(PersonVO.class,
                        personEntity.personId,
                        personEntity.name,
                        personEntity.gender,
                        personEntity.age,
                        personEntity.identification,
                        personEntity.status))
                .fetch();
    }
}
