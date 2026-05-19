package com.ec.app.microservices.province.repository;

import com.ec.app.microservices.config.IQueryDslBaseRepository;
import com.ec.app.microservices.province.ProvinceDTO;
import com.ec.app.microservices.province.entity.ProvinceEntity;

import java.util.List;

/**
 * @author arobayo
 */
public interface IProvinceRepository extends IQueryDslBaseRepository<ProvinceEntity> {
     List<ProvinceDTO> findAllProvinces();
}
