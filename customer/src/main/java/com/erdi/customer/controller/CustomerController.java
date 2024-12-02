package com.erdi.customer.controller;

import com.erdi.customer.model.CustomerRequest;
import com.erdi.customer.service.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public void registerCustomer (@RequestBody CustomerRequest customerRequest){
        log.info("New customer registered : " + customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
