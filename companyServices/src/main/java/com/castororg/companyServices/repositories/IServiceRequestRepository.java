package com.castororg.companyServices.repositories;

import com.castororg.companyServices.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
}
