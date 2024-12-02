package com.erdi.customer.service;

import com.erdi.customer.model.Customer;
import com.erdi.customer.model.CustomerRequest;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public void registerCustomer(CustomerRequest customerRequest) {
       Customer customer = Customer.builder()
       .firstName(customerRequest.firstName())
       .lastName(customerRequest.lastName())
       .email(customerRequest.email())
       .build();

       // todo: check if email valid
       // todo: check if email taken
       // todo: store customer in db
    }

}
