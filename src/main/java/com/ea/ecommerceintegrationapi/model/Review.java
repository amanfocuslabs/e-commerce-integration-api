package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    private Long id;
    private Date date;
    private String content;
    private Double rating;
    private Long accountId;
    private Long productId;

}
