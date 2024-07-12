package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.EmployeeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idNumber;
    private String name;
    private String photoPath;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfEntry;
    @ManyToOne
    @JoinColumn(name = "id_position", referencedColumnName = "idPosition")
    private Position position;

    public Employee(Long idNumber, String name, String photoPath, LocalDate dateOfEntry, Position position) {
        this.idNumber = idNumber;
        this.name = name;
        this.photoPath = photoPath;
        this.dateOfEntry = dateOfEntry;
        this.position = position;
    }

    public static Employee EmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Position position = new Position(employeeDTO.position(),"");
        return new Employee(employeeDTO.idNumber(), employeeDTO.name(), employeeDTO.photoPath(), employeeDTO.dateOfEntry(), position);
    }

    public static EmployeeDTO EmployeToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.idNumber, employee.name, employee.photoPath, employee.dateOfEntry, employee.position.getIdPosition());
    }
}
