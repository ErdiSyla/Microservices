package com.erdi.fraud.controller;

import com.erdi.fraud.service.FraudCheckService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/fraud-check/")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public ResponseEntity<String> isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulent = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud-check request for customer : " + customerId);
        if (isFraudulent){
            return new ResponseEntity<>("Customer is a fraud", HttpStatus.FORBIDDEN);
            
        } else {
            return new ResponseEntity<>("Customer is allowed access", HttpStatus.OK);
        }
    }
}
