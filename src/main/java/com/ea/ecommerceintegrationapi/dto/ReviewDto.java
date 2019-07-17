package com.ea.ecommerceintegrationapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {
    private Long id;
    private Date date;
    private String content;
    private Double rating;
    private String accountName;

    public ReviewDto(Long id, Date date, String content, Double rating, String accountName) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.rating = rating;
        this.accountName = accountName;
    }
}
