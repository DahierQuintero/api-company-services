package com.castororg.companyServices.repositories;

import com.castororg.companyServices.entities.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestStatusRepository extends JpaRepository<RequestStatus, Integer> {
}
