package com.castororg.companyServices.services.impl;

import com.castororg.companyServices.dtos.PositionDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.entities.Position;
import com.castororg.companyServices.exceptions.DoesNotExistEntityException;
import com.castororg.companyServices.exceptions.InvalidRequestException;
import com.castororg.companyServices.repositories.IPositionRepository;
import com.castororg.companyServices.services.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository iPositionRepository;

    public PositionServiceImpl(IPositionRepository iPositionRepository) {
        this.iPositionRepository = iPositionRepository;
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> createPosition(PositionDTO positionDTO) {

        validatePositionDTO(positionDTO);

        Position positionToSave = Position.positionDTOToPosition(positionDTO);

        iPositionRepository.save(positionToSave);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Position " + positionDTO.name() + " has been successfully created"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PositionDTO> getPositionById(Integer id) {

        Position positionToFind =  findPositionById(id);

        PositionDTO positionToResponse = Position.positionToPositionDTO(positionToFind);

        return new ResponseEntity<>(new PositionDTO(positionToResponse.idPosition(), positionToResponse.name()), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> updatePositionById(Integer id, PositionDTO positionRequest) {

        Position positionToUpdate = findPositionById(id);

        validatePositionDTO(positionRequest);

        positionToUpdate.setName(positionRequest.name());

        updatePosition(positionToUpdate);

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Position with ID " + id + " has been successfully updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SimpleMessageResponseDTO> deletePositionById(Integer id) {

        Position positionToDelete = findPositionById(id);

        deletePosition(positionToDelete.getIdPosition());

        return new ResponseEntity<>(new SimpleMessageResponseDTO("Position with ID " + id + " has been successfully deleted"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PositionDTO>> getAllPositions() {

        List<Position> positionsDb = iPositionRepository.findAll();

        return new ResponseEntity<>(positionsDb.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    public Position findPositionById(Integer id) {
        return iPositionRepository.findById(id).orElseThrow(
                () -> new DoesNotExistEntityException("The position ID " + id + " does not exist", HttpStatus.BAD_REQUEST.name()));
    }

    public void validatePositionDTO(PositionDTO positionDTO) {
        if (positionDTO.name() == null || positionDTO.name().isBlank()) {
            throw new InvalidRequestException("The position name can not be empty or null", HttpStatus.BAD_REQUEST.name());
        }
    }

    public void updatePosition(Position positionToUpdate) {
        iPositionRepository.save(positionToUpdate);
    }

    public void deletePosition(Integer id) {
        iPositionRepository.deleteById(id);
    }

    private PositionDTO convertToDTO(Position position) {
        return new PositionDTO(position.getIdPosition(), position.getName());
    }
}
