package com.ea.ecommerceintegrationapi.model;

import com.ea.ecommerceintegrationapi.Utils.ShippingState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Tracking implements Serializable {
    private ShippingState shippingState;
}
