package com.dhosiolux.brainbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String town;
    private Long postalCode;
    private String country;

}
