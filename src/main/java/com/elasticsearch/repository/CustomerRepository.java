package com.elasticsearch.repository;

import com.elasticsearch.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    /**
     * Get customer by age.
     * Page start with 0
     *
     * @param age
     * @param pageable
     * @return
     */
    Page<Customer> findByAge(int age, Pageable pageable);
}
