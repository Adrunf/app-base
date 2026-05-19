package com.ec.app.microservices.controllers;

import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.constants.PersonConstants;
import com.ec.app.microservices.person.PersonDTO;
import com.ec.app.microservices.province.ProvinceDTO;
import com.ec.app.microservices.province.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author arobayo
 */
@RestController
@RequestMapping("province")
@Lazy
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/findAllProvinces")
    public ResponseEntity<Response<List<ProvinceDTO>>> findAllPersons() {
        return new ResponseEntity<>(Response.<List<ProvinceDTO>>builder()
                .data(provinceService.findAllProvinces())
                .message(PersonConstants.SEARCHED_MESSAGE)
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
