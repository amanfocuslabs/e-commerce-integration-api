package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {
    private Long id;
    private Long accountId;
    private String orderNumber;
    private List<OrderLine> orderLines;
    private Date orderDate;
    private Long paymentId;
    private Double tax;
    private Long shippingId;
}
