package com.castororg.companyServices.services;

import com.castororg.companyServices.dtos.PositionDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPositionService {

    ResponseEntity<SimpleMessageResponseDTO> createPosition(PositionDTO positionDTO);
    ResponseEntity<PositionDTO> getPositionById(Integer id);
    ResponseEntity<SimpleMessageResponseDTO> updatePositionById(Integer id, PositionDTO positionRequest);
    ResponseEntity<SimpleMessageResponseDTO> deletePositionById(Integer id);
    ResponseEntity<List<PositionDTO>> getAllPositions();
}
