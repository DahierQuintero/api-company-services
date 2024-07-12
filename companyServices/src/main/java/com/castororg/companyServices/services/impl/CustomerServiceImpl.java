package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.dtos.CustomerDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.entities.Customer;
import com.castororg.companyServices.exceptions.DoesNotExistEntityException;
import com.castororg.companyServices.exceptions.InvalidRequestException;
import com.castororg.companyServices.repositories.ICustomerRepository;
import com.castororg.companyServices.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public ResponseEntity<SimpleMessageResponseDTO> createCustomer(CustomerDTO customerDTO) {

        isCustomerPresent(customerDTO.id());

        validateCustomerDTO(customerDTO);

        Customer customerToSave = Customer.customerDTOToCustomer(customerDTO);

        saveCustomer(customerToSave);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("The customer " + customerDTO.name() + " has been successfully created"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Long id) {

        CustomerDTO customerToFind = Customer.customerToCustomerDTO(findCustomerById(id));

        return new ResponseEntity<>(new CustomerDTO(customerToFind.id(), customerToFind.name(), customerToFind.email(), customerToFind.phoneNumber()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> updateCustomerById(Long id, CustomerDTO customerDTO) {

        Customer customerToUpdate = findCustomerById(id);

        validateCustomerDTO(customerDTO);

        Customer customerRequest = Customer.customerDTOToCustomer(customerDTO);

        customerToUpdate.setId(customerRequest.getId());
        customerToUpdate.setName(customerRequest.getName());
        customerToUpdate.setEmail(customerRequest.getEmail());
        customerToUpdate.setPhoneNumber(customerRequest.getPhoneNumber());

        updateCustomer(customerToUpdate);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Customer with ID " + id + " has been successfully updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> deleteCustomerById(Long id) {

        Customer customerToUpdate = findCustomerById(id);

        deleteCustomer(id);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Customer with ID " + id + " has been successfully deleted"), HttpStatus.OK);
    }

    public void validateCustomerDTO(CustomerDTO customerDTO) {
        if (customerDTO.id().toString().isBlank()) {
            throw new InvalidRequestException("The ID number cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (customerDTO.name() == null || customerDTO.name().isBlank()) {
            throw new InvalidRequestException("The name cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (customerDTO.email() == null || customerDTO.email().isBlank()) {
            throw new InvalidRequestException("The email cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (!isValidEmail(customerDTO.email())) {
            throw new InvalidRequestException("You must provide a valid email address", HttpStatus.BAD_REQUEST.name());
        }
        if (customerDTO.phoneNumber() == null || customerDTO.phoneNumber().describeConstable().isEmpty()) {
            throw new InvalidRequestException("The phone number cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new DoesNotExistEntityException("The Customer with ID " + id + " does not exist", HttpStatus.BAD_REQUEST.name()));
    }

    public void isCustomerPresent(Long id) {
        if (customerRepository.findById(id).isPresent())
            throw new InvalidRequestException("The Customer with ID " + id + " already exists", HttpStatus.BAD_REQUEST.name());
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
