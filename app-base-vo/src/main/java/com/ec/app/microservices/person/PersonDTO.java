package com.ec.app.microservices.person;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author arobayo
 */
@Data
public class PersonDTO {
    private Integer personId;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private Boolean status;
}
