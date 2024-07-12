package com.castororg.companyServices.services;

import com.castororg.companyServices.dtos.CustomerDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import org.springframework.http.ResponseEntity;


public interface ICustomerService {

    ResponseEntity<SimpleMessageResponseDTO> createCustomer(CustomerDTO customerDTO);
    ResponseEntity<CustomerDTO> getCustomerById(Long id);
    ResponseEntity<SimpleMessageResponseDTO> updateCustomerById(Long id, CustomerDTO customerDTO);
    ResponseEntity<SimpleMessageResponseDTO> deleteCustomerById(Long id);
}
