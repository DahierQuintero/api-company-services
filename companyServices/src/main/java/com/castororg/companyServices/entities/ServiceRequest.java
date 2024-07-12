package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.ServiceRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customerApplicant;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfApplication;
    @ManyToOne
    @JoinColumn(name = "id_request_status", referencedColumnName = "id")
    private RequestStatus requestStatus;
    @ManyToOne
    @JoinColumn(name = "id_castor_activity", referencedColumnName = "id")
    private CastorActivity castorActivity;

    public ServiceRequest(Customer customerApplicant, RequestStatus requestStatus, CastorActivity castorActivity) {
        this.customerApplicant = customerApplicant;
        this.requestStatus = requestStatus;
        this.castorActivity = castorActivity;
        dateOfApplication = LocalDate.now();
    }

    public static ServiceRequestDTO serviceRequestToServiceRequestDTO(ServiceRequest serviceRequest) {
        return new ServiceRequestDTO(serviceRequest.id, serviceRequest.castorActivity.getId());
    }

}
