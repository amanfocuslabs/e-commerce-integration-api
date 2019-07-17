package com.ea.ecommerceintegrationapi.controller;

import com.ea.ecommerceintegrationapi.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/ecommerce")
@CrossOrigin
public class ECommerceIntegrationController {

    @Autowired
    private RestTemplate restTemplate;

    private String order_service_url = "http://order-service/api/posts";
    private String product_service_url = "http://product-service/api/posts";
    private String account_service_url = "http://account-service:8085/rest/account/getByUserName";

    /*
    * GetProducts
    * */

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
        model.addAttribute("account",account);
        // Order history
        return "accountDetail";
    }

}
