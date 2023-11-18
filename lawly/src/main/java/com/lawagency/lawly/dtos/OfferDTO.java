package com.lawagency.lawly.dtos;

import com.lawagency.lawly.model.Offer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    private String name;
    private Long price;

    public OfferDTO(Offer offer) {
        id = offer.getId();
        name = offer.getName();
        price = offer.getPrice();
    }
}
