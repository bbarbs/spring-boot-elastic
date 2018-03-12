package com.elasticsearch.service.impl;

import com.elasticsearch.model.Customer;
import com.elasticsearch.repository.CustomerRepository;
import com.elasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public Iterable<Customer> getAllCustomers() {
        return this.repository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        AtomicReference<Customer> customer = new AtomicReference<>(new Customer());
        this.repository.findById(customerId).ifPresent(res -> customer.set(res));
        return customer.get();
    }

    @Override
    public Customer updateCustomerById(String customerId, Customer customer) {
        AtomicReference<String> id = null;
        this.repository.findById(customerId).ifPresent(res -> id.set(res.getId()));
        customer.setId(id.get());
        return this.repository.save(customer);
    }

    @Override
    public void deleteCustomerById(String customerId) {
        this.repository.deleteById(customerId);
    }

    @Override
    public Page<Customer> getCustomersByAge(int age, PageRequest pageRequest) {
        return this.repository.findByAge(age, pageRequest);
    }
}
