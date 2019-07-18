package com.ea.ecommerceintegrationapi.model;

import com.ea.ecommerceintegrationapi.Utils.CardType;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import java.io.Serializable;
import java.util.Date;

@Data
public class Card implements Serializable {
    private Long id;

    private String nameOnCard;

    private String cardNumber;

    private String expirationDate;

    private CardType cardType;
}
