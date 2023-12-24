package com.lawagency.lawly.services;

import com.lawagency.lawly.model.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    public List<Offer> findAll();
    public Offer findOne(UUID id);
}
