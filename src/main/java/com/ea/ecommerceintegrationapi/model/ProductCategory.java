package com.ea.ecommerceintegrationapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCategory {
    private Long Id;
    private CategoryName categoryName;
    private List<Product> productList =new ArrayList<>();
}
