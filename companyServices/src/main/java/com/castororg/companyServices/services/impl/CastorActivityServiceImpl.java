package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.dtos.CastorActivityDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.entities.CastorActivity;
import com.castororg.companyServices.exceptions.DoesNotExistEntityException;
import com.castororg.companyServices.exceptions.InvalidRequestException;
import com.castororg.companyServices.repositories.ICastorActivityRepository;
import com.castororg.companyServices.services.ICastorActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastorActivityServiceImpl implements ICastorActivityService {

    @Autowired
    private ICastorActivityRepository castorAttentionRepository;

    public CastorActivityServiceImpl(ICastorActivityRepository castorAttentionRepository) {
        this.castorAttentionRepository = castorAttentionRepository;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> createCastorAttention(CastorActivityDTO castorAttentionDTO) {

        validateCastorActivityDTO(castorAttentionDTO);

        CastorActivity castorAttentionToSave = CastorActivity.castorActivityDTOToCastorActivity(castorAttentionDTO);

        castorAttentionRepository.save(castorAttentionToSave);

        return new ResponseEntity<SimpleMessageResponseDTO>(new SimpleMessageResponseDTO("Attention " + castorAttentionDTO.name() + " has been successfully created"), HttpStatus.CREATED);
    }



    @Override
    public ResponseEntity<CastorActivityDTO> getCastorActivityById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> updateCastorActivityById(Integer id, CastorActivityDTO castorAttentionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> deleteCastorActivityById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CastorActivityDTO>> getAllCastorActivities() {
        return null;
    }

    public void validateCastorActivityDTO(CastorActivityDTO castorAttentionDTO) {
        if (castorAttentionDTO.name() == null || castorAttentionDTO.name().isBlank()) {
            throw new InvalidRequestException("The position name can not be empty or null", HttpStatus.BAD_REQUEST.name());
        }
    }
    public CastorActivity isCastorActivityPresent(Integer id) {
        return castorAttentionRepository.findById(id).orElseThrow(
                () -> new DoesNotExistEntityException("The activity Castor with ID " + id + " does not exists", HttpStatus.NOT_FOUND.name()));
    }
}
