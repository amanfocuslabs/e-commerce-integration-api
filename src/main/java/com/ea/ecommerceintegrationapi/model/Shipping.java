package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shipping implements Serializable {
    private Long id;
    private Address shippingAddress;
    private Double price;
    private Tracking tracking;
    private Long orderId;
}
