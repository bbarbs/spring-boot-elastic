package com.elasticsearch.web;

import com.elasticsearch.model.Customer;
import com.elasticsearch.service.CustomerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * Get list of customers.
     *
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class)
    })
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    public Iterable<Customer> getAllCustomers() {
        return this.service.getAllCustomers();
    }

    /**
     * Add customer.
     *
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = Customer.class)
    })
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return this.service.addCustomer(customer);
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class)
    })
    @GetMapping(
            value = "/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    public Customer getCustomerById(@PathVariable(name = "customerId") String customerId) {
        return this.service.getCustomerById(customerId);
    }

    /**
     * Update customer by id.
     *
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = Customer.class)
    })
    @PutMapping(
            value = "/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Customer updateCustomerById(@PathVariable(name = "customerId") String customerId, @RequestBody Customer customer) {
        return this.service.updateCustomerById(customerId, customer);
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT", response = Customer.class)
    })
    @DeleteMapping(
            value = "/{customerId}",
            produces = TEXT_PLAIN_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCustomerById(@PathVariable(name = "customerId") String customerId) {
        this.service.deleteCustomerById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get customers by age in pagination style.
     * Page start with 0.
     *
     * @param age
     * @param page
     * @param size
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class)
    })
    @GetMapping(
            params = {"age", "page", "size"},
            produces = APPLICATION_JSON_VALUE
    )
    public Page<Customer> getCustomersByAge(@RequestParam(name = "age") int age, @RequestParam(name = "page") int page,
                                            @RequestParam(name = "size") int size) {
        return this.service.getCustomersByAge(age, PageRequest.of(page, size));
    }
}
