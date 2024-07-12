package com.castororg.companyServices.controllers;

import com.castororg.companyServices.dtos.ServiceRequestDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.services.impl.ServiceRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dilan.com/api/v1/servicerequest")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestServiceImpl serviceRequestService;

    public ServiceRequestController(ServiceRequestServiceImpl serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleMessageResponseDTO> createServiceRequest(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        return serviceRequestService.createServiceRequest(serviceRequestDTO);
    }
}
