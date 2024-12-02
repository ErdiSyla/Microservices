package com.erdi.customer.service;

import java.util.regex.Pattern;

import com.erdi.customer.model.Customer;
import com.erdi.customer.model.CustomerRequest;
import com.erdi.customer.repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<String> registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        if (isValidEmail(customer) && !usedEmail(customer)) {
            customerRepository.save(customer);
            return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);
        } else if (!isValidEmail(customer)) {
            return new ResponseEntity<>("Invalid email", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Email is already taken", HttpStatus.CONFLICT);
        }

    }

    private boolean isValidEmail(Customer customer) {
        String combinedRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(combinedRegex);
        return pattern.matcher(customer.getEmail()).matches();
    }

    public boolean usedEmail(Customer customer) {
        return customerRepository.findByEmail(customer.getEmail()).isPresent();
    }

}
