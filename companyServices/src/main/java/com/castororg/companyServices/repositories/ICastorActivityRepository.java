package com.castororg.companyServices.repositories;

import com.castororg.companyServices.entities.CastorActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICastorActivityRepository extends JpaRepository<CastorActivity, Integer> {
}
