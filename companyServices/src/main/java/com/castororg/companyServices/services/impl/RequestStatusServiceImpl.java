package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.repositories.IRequestStatusRepository;
import com.castororg.companyServices.services.IRequestStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestStatusServiceImpl implements IRequestStatusService {

    @Autowired
    private IRequestStatusRepository requestStatusRepository;

    public RequestStatusServiceImpl(IRequestStatusRepository requestStatusRepository) {
        this.requestStatusRepository = requestStatusRepository;
    }
}
