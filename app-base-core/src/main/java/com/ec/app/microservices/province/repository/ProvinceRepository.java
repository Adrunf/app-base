package com.ec.app.microservices.province.repository;

import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.ec.app.microservices.province.ProvinceDTO;
import com.ec.app.microservices.province.entity.ProvinceEntity;
import com.ec.app.microservices.province.entity.QProvinceEntity;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author arobayo
 */
@Lazy
@Repository
public class ProvinceRepository extends JPAQueryDslBaseRepository<ProvinceEntity> implements IProvinceRepository{

    public ProvinceRepository() {
        super(ProvinceEntity.class);
    }

    @Override
    public List<ProvinceDTO> findAllProvinces() {
        QProvinceEntity provinceEntity = new QProvinceEntity("provinceEntity");
        return from(provinceEntity)
                .select(Projections.bean(ProvinceDTO.class,
                                provinceEntity.provinceId,
                                provinceEntity.name,
                                provinceEntity.population
                        )
                )
                .fetch();
    }
}
