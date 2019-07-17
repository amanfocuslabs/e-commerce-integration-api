package com.ea.ecommerceintegrationapi.controller;

import com.ea.ecommerceintegrationapi.model.Cart;
import com.ea.ecommerceintegrationapi.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class ECommerceIntegrationRestContoller{


    @Autowired
    private RestTemplate restTemplate;

    private String order_service_url = "http://order-service:8081/rest/order/";
    private String product_service_url = "http://product-service:8084/rest/product/";
    private String account_service_url = "http://account-service:8085/rest/account/test";
    private String cart_service_url = "http://cart-service:8083/rest/cart";

    /*
    * AddToCart
    * */
    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody int id){
        
        Cart c =  restTemplate.postForObject(cart_service_url + "/addToCart/" + id + "/" + 1 + "/" + 1, null, Cart.class);
        System.out.println(c.getId());   
        return c;
    }
}
