package com.ec.app.microservices.controllers;

import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.constants.PersonConstants;
import com.ec.app.microservices.person.PersonVO;
import com.ec.app.microservices.person.service.IPersonService;
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
@RequestMapping("person")
@Lazy
public class PersonController {
    @Lazy
    @Autowired
    private IPersonService personService;

    @GetMapping("/findAllPersons")
    public ResponseEntity<Response<List<PersonVO>>> findAllPersons() {
        return new ResponseEntity<>(Response.<List<PersonVO>>builder()
                .data(personService.findAll())
                .message(PersonConstants.SEARCHED_MESSAGE)
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
