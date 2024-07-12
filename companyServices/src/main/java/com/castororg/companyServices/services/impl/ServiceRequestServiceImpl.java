package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.dtos.ServiceRequestDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.entities.CastorActivity;
import com.castororg.companyServices.entities.Customer;
import com.castororg.companyServices.entities.RequestStatus;
import com.castororg.companyServices.entities.ServiceRequest;
import com.castororg.companyServices.repositories.IServiceRequestRepository;
import com.castororg.companyServices.services.IServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements IServiceRequestService {

    @Autowired
    private IServiceRequestRepository iServiceRequestRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private RequestStatusServiceImpl requestStatusService;
    @Autowired
    private CastorActivityServiceImpl castorActivityService;

    public ServiceRequestServiceImpl(IServiceRequestRepository iServiceRequestRepository, CustomerServiceImpl customerService, RequestStatusServiceImpl requestStatusService, CastorActivityServiceImpl castorActivityService) {
        this.iServiceRequestRepository = iServiceRequestRepository;
        this.customerService = customerService;
        this.requestStatusService = requestStatusService;
        this.castorActivityService = castorActivityService;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> createServiceRequest(ServiceRequestDTO serviceRequestDTO) {

        Customer customerRequest = customerService.findCustomerById(serviceRequestDTO.idCustomerApplicant());

        CastorActivity castorActivity = castorActivityService.isCastorActivityPresent(serviceRequestDTO.castorActivity());

        ServiceRequest serviceRequestToSave = serviceRequestDTOToServiceRequest(serviceRequestDTO);

        iServiceRequestRepository.save(serviceRequestToSave);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Service request for " + customerRequest.getName() + " has been successfully created"), HttpStatus.CREATED);
    }

    public ServiceRequest serviceRequestDTOToServiceRequest(ServiceRequestDTO serviceRequestDTO) {

        Customer customer = isCustomerPresent(serviceRequestDTO.idCustomerApplicant());

        RequestStatus requestStatus = new RequestStatus(1,"");

        CastorActivity castorActivity = castorActivityService.isCastorActivityPresent(serviceRequestDTO.castorActivity());

        CastorActivity castorActivityToSave = new CastorActivity(serviceRequestDTO.castorActivity(), "");

        return new ServiceRequest(customer, requestStatus, castorActivityToSave);
    }

    @Override
    public ResponseEntity<ServiceRequestDTO> getServiceRequestById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> updateServiceRequestById(Long id, ServiceRequestDTO serviceRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> deleteServiceRequestById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ServiceRequestDTO>> getAllEmployees() {
        return null;
    }

    public Customer isCustomerPresent(Long id) {
        return customerService.findCustomerById(id);
    }
}
