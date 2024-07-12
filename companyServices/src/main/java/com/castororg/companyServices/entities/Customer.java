package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.CustomerDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;

    public static CustomerDTO customerToCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.id, customer.name, customer.email, customer.phoneNumber);
    }

    public static Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.id(), customerDTO.name(), customerDTO.email(), customerDTO.phoneNumber());
    }
}
