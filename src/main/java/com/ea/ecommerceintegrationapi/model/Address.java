package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String state;
}
