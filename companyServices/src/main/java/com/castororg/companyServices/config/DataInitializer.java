package com.castororg.companyServices.config;

import com.castororg.companyServices.entities.CastorActivity;
import com.castororg.companyServices.entities.Position;
import com.castororg.companyServices.entities.RequestStatus;
import com.castororg.companyServices.repositories.ICastorActivityRepository;
import com.castororg.companyServices.repositories.IPositionRepository;
import com.castororg.companyServices.repositories.IRequestStatusRepository;
import com.castororg.companyServices.services.ICastorActivityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initPositionsDatabase(IPositionRepository positionRepository) {
        return args -> {
            positionRepository.save(new Position(1, "Scrum Master"));
            positionRepository.save(new Position(2, "Desarrollador"));
            positionRepository.save(new Position(3, "QA"));
            positionRepository.save(new Position(4, "PO"));
        };
    }

    @Bean
    CommandLineRunner initRequestStatusDatabase(IRequestStatusRepository requestStatusRepository) {
        return args -> {
            requestStatusRepository.save(new RequestStatus(1, "Pendiente"));
            requestStatusRepository.save(new RequestStatus(2, "En progreso"));
            requestStatusRepository.save(new RequestStatus(3, "Finalizado"));
        };
    }

    @Bean
    CommandLineRunner initCastorActivityDatabase(ICastorActivityRepository castorActivityRepository) {
        return args -> {
            castorActivityRepository.save(new CastorActivity(1, 1, "Atencion al cliente"));
            castorActivityRepository.save(new CastorActivity(2, 2, "Tecnico"));
            castorActivityRepository.save(new CastorActivity(3, 3, "Ventas"));
        };
    }
}
