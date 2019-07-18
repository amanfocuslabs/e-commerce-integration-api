package com.ea.ecommerceintegrationapi.controller;

import com.ea.ecommerceintegrationapi.dto.ReviewDto;
import com.ea.ecommerceintegrationapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
@Scope("session")
public class ECommerceIntegrationController {

    @Autowired
    private RestTemplate restTemplate;

    private String order_service_url = "http://order-service:8081/rest/order/";
    private String product_service_url = "http://product-service:8084/rest/product/";
    private String review_service_url = "http://review-service:8089/rest/review/";
    private String account_service_url = "http://account-service:8085/rest/account/";
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
        Cart cart = restTemplate.postForObject(cart_service_url + "/addToCart/" + product.getId() + "/" + quantity + "/" + cartId, null, Cart.class);
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
    @GetMapping("/create")
    public String createOrder(HttpServletRequest request , Model model){
        Account account = (Account)request.getSession().getAttribute("user");
        Long cartId = (Long)request.getSession().getAttribute("cartId");
        //Long tax = (Long)request.getSession().getAttribute("grand");
        //tax = tax / 10;
        System.out.println(account.getId() + " + " + cartId);
        Order order = restTemplate.postForObject(order_service_url + "create/" + account.getId() + "/" + cartId + "/" + 56
                + "/" + 1 , null, Order.class);
        model.addAttribute("Order", order);
        return "redirect:shop/shop-full";
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
    @PostMapping("/login")
    public String getAccountInformationByUserName(@Valid Account account, BindingResult result, Model model, HttpServletRequest request){
        if (result.hasErrors()) {
            return "account/login";
        }
        else {
            Account returned = restTemplate.getForObject(account_service_url + "getByUserName/" + account.getUserName(), Account.class);
            model.addAttribute("account", account);
            if (returned.getUserName().equals(account.getUserName()) && returned.getPassword().equals(account.getPassword())) {
                request.getSession().setAttribute("user",returned);
                return "home/index";
            } else {
                return "errorpages/404";
            }
        }
    }
    @PostMapping("/addAccount")
    public String addAccount(Account account, RedirectAttributes attr) {
        HttpEntity<Account> request = new HttpEntity<>(account);
        Account added = restTemplate.postForObject(account_service_url+"add", request, Account.class);
        attr.addFlashAttribute("account",account);

        return "accountDetail";
    }
    @GetMapping("/account")
    public String myAccount() {
        return "account/my-account";
    }
    @GetMapping("/index")
    public String index() {
        return "home/index";
    }

    /*
    * Product Detail
    * */
    @GetMapping("/product-details")
    public String productDetails(@RequestParam String productId, Model model){
//        Todo - To create a rest controller pass a DTO of the combination of product, review and account
        Product product = restTemplate.getForObject(product_service_url + "/" + productId, Product.class);
        model.addAttribute("product", product);

        List<Review> reviews = restTemplate.exchange(review_service_url + "/getReviewByProduct/" + productId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>(){}).getBody();
        model.addAttribute("reviews", reviews.stream()
                .map(review ->
                        new ReviewDto(review.getId(), review.getDate(), review.getContent(), review.getRating(),
                                restTemplate.getForObject(account_service_url + "/" + review.getAccountId(), Account.class).getUserName()))
                .collect(Collectors.toList()));
        return "product/product-details";
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
    public String login(Model model){
        Account account=new Account();
        model.addAttribute("account",account);
        return "account/login";
    }

    @RequestMapping(value = "/checkout" , method = RequestMethod.GET)   
    public String checkout(Model model , HttpServletRequest session){

        Long cartId = (Long)session.getSession().getAttribute("cartId");
        //System.out.println("******************* :" + cartId);

        List<Product> products = restTemplate.exchange(cart_service_url + "/getAllProducts/" + cartId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){}).getBody();
        Long subtotal = new Long(0) ;
        model.addAttribute("products", products);
        for (Product p : products) {
            subtotal += p.getPrice();
        }
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("grand", subtotal);

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

    @RequestMapping(value = "/cart" ,method = RequestMethod.GET)
    public String cart(Model model , HttpServletRequest session){

        Long cartId = (Long)session.getSession().getAttribute("cartId");
        //System.out.println("******************* :" + cartId);

        List<Product> products = restTemplate.exchange(cart_service_url + "/getAllProducts/" + cartId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){}).getBody();
        Long subtotal = new Long(0) ;
        model.addAttribute("products", products);
        for (Product p : products) {
            subtotal += p.getPrice();
        }
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("grand", subtotal);
        
        return "shop/cart";
    }
}
