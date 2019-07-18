package com.ea.ecommerceintegrationapi.model;

import com.ea.ecommerceintegrationapi.Utils.Membership;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class Account implements Serializable {
    private Long id;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private String email;
    private Membership membership;
    private Address address;
    private Card cards;


}
