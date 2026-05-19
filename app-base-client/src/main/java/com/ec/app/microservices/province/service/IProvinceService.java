package com.ec.app.microservices.province.service;

import com.ec.app.microservices.province.ProvinceDTO;

import java.util.List;

/**
 * @author arobayo
 */
public interface IProvinceService {
    List<ProvinceDTO> findAllProvinces();
}
