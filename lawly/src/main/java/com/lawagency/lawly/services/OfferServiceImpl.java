package com.lawagency.lawly.services;

import com.lawagency.lawly.model.Offer;
import com.lawagency.lawly.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService{
    private final OfferRepository repository;

    @Autowired
    public OfferServiceImpl(OfferRepository repository) {
        this.repository = repository;
    }

    public List<Offer> findAll(){
        return repository.findAll();
    }

    public Offer findOne(UUID id) {return repository.findOne(id);}
}
