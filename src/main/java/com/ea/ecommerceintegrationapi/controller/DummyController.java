package com.ea.ecommerceintegrationapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DummyController{
    @RequestMapping(value = "/" , method = RequestMethod.GET)   
    public String home(){
        return "home/index";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)   
    public String login(){
        return "account/login";
    }

    @RequestMapping(value = "/checkout" , method = RequestMethod.GET)   
    public String checkout(){
        return "shop/checkout";
    }
    @RequestMapping(value = "/compare" , method = RequestMethod.GET)   
    public String compare(){
        return "product/compare";
    }
    @RequestMapping(value = "/wishlist" , method = RequestMethod.GET)   
    public String wishlist(){
        return "account/wishlist";
    }
    @RequestMapping(value = "/contact" , method = RequestMethod.GET)   
    public String contact(){
        return "about/contact";
    }
    @RequestMapping(value = "/about" , method = RequestMethod.GET)   
    public String about(){
        return "about/about";
    }
    @RequestMapping(value = "/shop" , method = RequestMethod.GET)   
    public String shop(){
        return "shop/shop-full";
    }
    @RequestMapping(value = "/blog" , method = RequestMethod.GET)   
    public String blog(){
        return "blog/blog";
    }

    @RequestMapping(value = "/blog-details" , method = RequestMethod.GET)   
    public String blogDetails(){
        return "blog/blog-details";
    }

    @RequestMapping(value = "/my-account" , method = RequestMethod.GET)   
    public String myAccount(){
        return "account/my-account";
    }

   

    @RequestMapping(value = "/product-details" , method = RequestMethod.GET)   
    public String productDetails(){
        return "product/details";
    }

}
