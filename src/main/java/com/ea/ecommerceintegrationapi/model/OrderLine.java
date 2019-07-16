package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderLine implements Serializable {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Order order;
}
