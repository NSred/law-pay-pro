package com.lawagency.lawly.dtos;

import com.lawagency.lawly.model.Offer;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private UUID id;
    private String name;
    private Long price;

    public OfferDTO(Offer offer) {
        id = offer.getId();
        name = offer.getName();
        price = offer.getPrice();
    }
}
