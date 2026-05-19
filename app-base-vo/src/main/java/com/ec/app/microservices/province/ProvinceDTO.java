package com.ec.app.microservices.province;

import lombok.Data;

/**
 * @author arobayo
 */
@Data
public class ProvinceDTO {
    private Integer provinceId;
    private String name;
    private Integer population;
}
