package com.lawagency.lawly.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EntityRepository<T> extends JpaRepository<T, Long> {
    default T findOne(Long id) {
        return findById(id).orElse(null);
    }

}
