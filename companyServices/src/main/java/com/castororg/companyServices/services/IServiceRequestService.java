package com.castororg.companyServices.services;

import com.castororg.companyServices.dtos.ServiceRequestDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceRequestService {
    ResponseEntity<SimpleMessageResponseDTO> createServiceRequest(ServiceRequestDTO serviceRequestDTO);
    ResponseEntity<ServiceRequestDTO> getServiceRequestById(Long id);
    ResponseEntity<SimpleMessageResponseDTO> updateServiceRequestById(Long id, ServiceRequestDTO serviceRequestDTO);
    ResponseEntity<SimpleMessageResponseDTO> deleteServiceRequestById(Long id);
    ResponseEntity<List<ServiceRequestDTO>> getAllEmployees();
}
