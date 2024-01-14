package com.dhosiolux.brainbox.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String town;
    private Long postalCode;
    private String country;

}
