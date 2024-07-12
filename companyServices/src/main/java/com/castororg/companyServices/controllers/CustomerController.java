package com.castororg.companyServices.controllers;

import com.castororg.companyServices.dtos.CustomerDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.services.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dilan.com/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleMessageResponseDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessageResponseDTO> updateCustomerById(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomerById(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageResponseDTO> deleteCustomerById(@PathVariable("id") Long id) {
        return customerService.deleteCustomerById(id);
    }
}
