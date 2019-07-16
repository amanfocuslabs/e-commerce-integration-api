package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Cart implements Serializable {
    private Long id;
    private List<Long> orderLineIdList;
}
