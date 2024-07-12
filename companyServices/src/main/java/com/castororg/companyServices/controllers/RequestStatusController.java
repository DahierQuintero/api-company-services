package com.castororg.companyServices.controllers;

import com.castororg.companyServices.services.impl.RequestStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dilan.com/api/v1/requeststatus")
public class RequestStatusController {

    @Autowired
    private RequestStatusServiceImpl requestStatusService;

    public RequestStatusController(RequestStatusServiceImpl requestStatusService) {
        this.requestStatusService = requestStatusService;
    }
}
