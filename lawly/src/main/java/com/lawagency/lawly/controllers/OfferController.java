package com.lawagency.lawly.controllers;

import com.lawagency.lawly.dtos.OfferDTO;
import com.lawagency.lawly.model.Offer;
import com.lawagency.lawly.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Collection<OfferDTO>> getOffers() {
        Collection<Offer> offers = offerService.findAll();
        List<OfferDTO> dtos = offers.stream()
                                     .map(OfferDTO::new)
                                        .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
