package com.lawagency.lawly.repository;

import com.lawagency.lawly.model.Offer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    default Offer findOne(UUID id) {
        return findById(id).orElse(null);
    }
}
