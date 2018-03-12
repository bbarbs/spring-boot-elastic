package com.elasticsearch.service;

import com.elasticsearch.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomerService {

    /**
     * Get all list of customers.
     *
     * @return
     */
    Iterable<Customer> getAllCustomers();

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    Customer addCustomer(Customer customer);

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    Customer getCustomerById(String customerId);

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    Customer updateCustomerById(String customerId, Customer customer);

    /**
     * Delete customer.
     *
     * @param customerId
     */
    void deleteCustomerById(String customerId);

    /**
     * Get customers by age.
     *
     * @param age
     * @param pageRequest
     * @return
     */
    Page<Customer> getCustomersByAge(int age, PageRequest pageRequest);
}
