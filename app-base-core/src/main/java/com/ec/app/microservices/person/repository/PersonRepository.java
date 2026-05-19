package com.ec.app.microservices.person.repository;

import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.person.PersonDTO;
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

    public PersonRepository(){
        super(PersonEntity.class);
    }

    @Override
    public List<PersonDTO> findAll() {
        QPersonEntity personEntity = new QPersonEntity("personEntity");
        return from(personEntity)
                .select(Projections.bean(PersonDTO.class,
                        personEntity.personId,
                        personEntity.name,
                        personEntity.gender,
                        personEntity.age,
                        personEntity.identification,
                        personEntity.status))
                .fetch();
    }

    @Override
    public PersonDTO findById(String identification) {
        QPersonEntity personEntity = new QPersonEntity("personEntity");
        return from(personEntity)
                .select(Projections.bean(PersonDTO.class,
                        personEntity.personId,
                        personEntity.name,
                        personEntity.gender,
                        personEntity.age,
                        personEntity.identification,
                        personEntity.status))
                .where(personEntity.identification.eq(identification))
                .fetchOne();
    }

    @Override
    public List<PersonDTO> findByStatus(Integer status) {
        QPersonEntity personEntity = new QPersonEntity("personEntity");
        return from(personEntity)
                .select(Projections.bean(PersonDTO.class,
                        personEntity.personId,
                        personEntity.name,
                        personEntity.gender,
                        personEntity.age,
                        personEntity.identification,
                        personEntity.status))
                .where(personEntity.status.eq(true))
                .fetch();
    }
}
