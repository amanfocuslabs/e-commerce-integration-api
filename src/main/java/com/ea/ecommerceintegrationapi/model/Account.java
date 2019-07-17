package com.ea.ecommerceintegrationapi.model;

import com.ea.ecommerceintegrationapi.Utils.Membership;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Account implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Membership membership;
    private List<Address> address;
    private List<Card> cards;


}
