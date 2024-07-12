package com.castororg.companyServices.controllers;

import com.castororg.companyServices.dtos.EmployeeDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.services.impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dilan.com/api/v1/employee")
@Tag(name = "Employee Management", description = "Operations pertaining to employee management in Castor")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeServiceImpl = employeeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Add a new employee", description = "Provide employee details to add a new employee to Castor")
    public ResponseEntity<SimpleMessageResponseDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeServiceImpl.createEmployee(employeeDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Provide an ID to look up a specific employee from Castor")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") Long id) {
        return employeeServiceImpl.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee", description = "Provide an ID and new employee details to update an existing employee in Castor")
    public ResponseEntity<SimpleMessageResponseDTO> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeServiceImpl.updateEmployeeById(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee by ID", description = "Provide an ID to delete a specific employee from Castor")
    public ResponseEntity<SimpleMessageResponseDTO> deleteEmployee(@PathVariable("id") Long id) {
        return employeeServiceImpl.deleteEmployeById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }
}
