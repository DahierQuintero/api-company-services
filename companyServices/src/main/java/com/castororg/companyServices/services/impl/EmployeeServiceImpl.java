package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.dtos.EmployeeDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.entities.Employee;
import com.castororg.companyServices.entities.Position;
import com.castororg.companyServices.exceptions.DoesNotExistEntityException;
import com.castororg.companyServices.exceptions.InvalidRequestException;
import com.castororg.companyServices.repositories.IEmployeeRepository;
import com.castororg.companyServices.repositories.IPositionRepository;
import com.castororg.companyServices.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IPositionRepository iPositionRepository;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository, IPositionRepository iPositionRepository) {
        this.employeeRepository = employeeRepository;
        this.iPositionRepository = iPositionRepository;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> createEmployee(@Valid EmployeeDTO employeeDTO) {

        isEmployeePresent(employeeDTO.idNumber());

        validateEmployeeDTO(employeeDTO);

        saveEmployee(employeeDTO);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("The employee " + employeeDTO.name() + " has been successfully created"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(Long id) {
        EmployeeDTO employeeFound = Employee.EmployeToEmployeeDTO(findEmployeeById(id));

        return new ResponseEntity<>(employeeFound,HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> updateEmployeeById(Long id, @Valid EmployeeDTO employeeDTO) {

        Employee employeeToUpdate = (findEmployeeById(id));

        validateEmployeeDTO(employeeDTO);

        Employee employeeRequest = Employee.EmployeeDTOToEmployee(employeeDTO);


        employeeRequest.setPosition(isValidPositionNumber(employeeRequest.getPosition()));

        employeeToUpdate.setIdNumber(employeeRequest.getIdNumber());
        employeeToUpdate.setName(employeeRequest.getName());
        employeeToUpdate.setPhotoPath(employeeRequest.getPhotoPath());
        employeeToUpdate.setDateOfEntry(employeeRequest.getDateOfEntry());
        employeeToUpdate.setPosition(employeeRequest.getPosition());

        updateEmployee(employeeToUpdate);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("The employee with ID " + id + " has been successfully updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> deleteEmployeById(Long id) {

        Employee employeeToDelete = (findEmployeeById(id));

        deleteEmployee(employeeToDelete.getId());

        return new ResponseEntity<>(new SimpleMessageResponseDTO("The employee with ID " + id + " has been successfully deleted"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (employeeDTO.idNumber() == null || employeeDTO.idNumber().describeConstable().isEmpty()) {
            throw new InvalidRequestException("ID number cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (employeeDTO.name() == null || employeeDTO.name().isBlank()) {
            throw new InvalidRequestException("Name cannot be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (employeeDTO.photoPath() == null || employeeDTO.photoPath().isBlank() || employeeDTO.photoPath().contains(" ")) {
            throw new InvalidRequestException("Photo path cannot be empty, null or have spaces", HttpStatus.BAD_REQUEST.name());
        }
        if (employeeDTO.dateOfEntry() == null) {
            throw new InvalidRequestException("Entry date must not be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (!isDateNotFuture(employeeDTO.dateOfEntry())) {
            throw new InvalidRequestException("Entry date must not be after to current date ", HttpStatus.BAD_REQUEST.name());
        }
        if (employeeDTO.position() == null) {
            throw new InvalidRequestException("Position ID must not be empty or null", HttpStatus.BAD_REQUEST.name());
        }
        if (iPositionRepository.findById(employeeDTO.position()).isEmpty()) {
            throw new InvalidRequestException("Position with ID " + employeeDTO.position() + " does not exist", HttpStatus.BAD_REQUEST.name());
        }
    }

    public void isEmployeePresent(Long id) {
        if (employeeRepository.findById(id).isPresent())
            throw new InvalidRequestException("Employee with ID " + id + " already exists", HttpStatus.BAD_REQUEST.name());
    }

    private void saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(Employee.EmployeeDTOToEmployee(employeeDTO));
    }

    private void updateEmployee(Employee employeeToUpdate) {
        employeeRepository.save(employeeToUpdate);
    }

    private void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new DoesNotExistEntityException("Employee with ID " + id + " is not registered", HttpStatus.NOT_FOUND.name()));
    }

    public Position isValidPositionNumber(Position position) {
        return iPositionRepository.findById(position.getIdPosition()).orElseThrow(
                () -> new InvalidRequestException("Position with ID " + position.getIdPosition() + " does not exist", HttpStatus.BAD_REQUEST.name())
        );
    }

    public boolean isDateNotFuture(LocalDate dateToValidate) {
        LocalDate currentDate = LocalDate.now();
        return (dateToValidate.isBefore(currentDate) || dateToValidate.equals(currentDate));
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        Integer positionId = employee.getPosition().getIdPosition();
        return new EmployeeDTO(
                employee.getIdNumber(),
                employee.getName(),
                employee.getPhotoPath(),
                employee.getDateOfEntry(),
                positionId
        );
    }
}
