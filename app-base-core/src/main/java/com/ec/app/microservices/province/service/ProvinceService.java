package com.ec.app.microservices.province.service;

import com.ec.app.microservices.person.repository.IPersonRepository;
import com.ec.app.microservices.person.service.IPersonService;
import com.ec.app.microservices.province.ProvinceDTO;
import com.ec.app.microservices.province.repository.IProvinceRepository;
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
public class ProvinceService implements IProvinceService{

    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public List<ProvinceDTO> findAllProvinces() {
        return provinceRepository.findAllProvinces();
    }
}
