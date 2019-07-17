package com.ea.ecommerceintegrationapi.controller;

import com.ea.ecommerceintegrationapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@CrossOrigin
public class ECommerceIntegrationController {

    @Autowired
    private RestTemplate restTemplate;

    private String order_service_url = "http://order-service:8081/rest/order/";
    private String product_service_url = "http://product-service:8084/rest/product/";
    private String account_service_url = "http://account-service:8085/rest/account/test";
    private String cart_service_url = "http://cart-service:8083/rest/cart";

    /*
    * GetProducts
    * */
    @GetMapping("/product")
    public String getProducts(Model model){
        List<Product> products = restTemplate.exchange(product_service_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){}).getBody();
        model.addAttribute("products", products);
        for (Product p : products) {
            System.out.println(p.getProductName());
        }

        return "shop/shop-full";
    }

    /*
    * AddToCart
    * */
    @PostMapping("/addToCart")
    public void addToCart(@RequestParam Product product, @RequestParam Integer quantity, @RequestParam Long cartId, Model model){
        Cart cart = restTemplate.getForObject(cart_service_url + "/addToCart/" + product.getId() + "/" + quantity + "/" + cartId, Cart.class);
        model.addAttribute("Cart",cart);
    }

    /*
    * CheckOut
    * */
    @PostMapping("/checkOutCart")
    public String checkOut(@RequestParam Cart cart, Model model){
        model.addAttribute("Cart", cart);
        return "cart_page";
    }

    /*
    * Order
    * */
    // Todo figure out how to pass payment type - id
    @PostMapping("/create")
    public String createOrder(@RequestParam Long accountId, @RequestParam Long cartId, @RequestParam Integer tax,
                                @RequestParam Long shippingId, Model model){
        Order order = restTemplate.getForObject(order_service_url + "/create/" + accountId + "/" + cartId + "/" + tax
                + "/" + shippingId, Order.class);
        model.addAttribute("Order", order);
        return "order_detail";
    }


    /*
    * GetReviewByAccountId
    * */

    /*
    * GetCardsByAccount
    * */

    /*
    * GetAccountInformationByUserName
    * */
    @GetMapping("/account")
    public String getAccountInformationByUserName(@RequestParam String userName, Model model){
        Account account = restTemplate.getForObject(account_service_url + "/" + userName, Account.class);
        model.addAttribute("account", account);

        System.out.println(account.getEmail() + " " + account.getUserName());
        // Order history
        return "accountDetail";
    }

    @RequestMapping(value = "/product-details" , method = RequestMethod.GET)   
    public String productDetails(){
        return "product/details";
    }
    @RequestMapping(value = "/blog" , method = RequestMethod.GET)   
    public String blog(){
        return "blog/blog";
    }

    @RequestMapping(value = "/blog-details" , method = RequestMethod.GET)   
    public String blogDetails(){
        return "blog/blog-details";
    }

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

    @RequestMapping(value = "/cart" , method = RequestMethod.GET)   
    public String cart(){
        return "cart/cart";
    }
}
