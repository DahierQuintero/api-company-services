package com.castororg.companyServices.services;

import com.castororg.companyServices.dtos.EmployeeDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IEmployeeService {
    ResponseEntity<SimpleMessageResponseDTO> createEmployee(EmployeeDTO employeeDTO);
    ResponseEntity<EmployeeDTO> getEmployeeById(Long id);
    ResponseEntity<SimpleMessageResponseDTO> updateEmployeeById(Long id, EmployeeDTO employeeDTO);
    ResponseEntity<SimpleMessageResponseDTO> deleteEmployeById(Long id);
    ResponseEntity<List<EmployeeDTO>> getAllEmployees();

}
