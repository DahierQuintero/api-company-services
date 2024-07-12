package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.RequestStatusDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatus {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String statusName;

    public RequestStatus(String statusName) {
        this.statusName = statusName;
    }

    public static RequestStatusDTO requestStatusToRequestStatusDTO(RequestStatus requestStatus) {
        return new RequestStatusDTO(requestStatus.id, requestStatus.statusName);
    }

    public static RequestStatus requestStatusDTOToRequestStatus(RequestStatusDTO requestStatusDTO) {
        return new RequestStatus(requestStatusDTO.id(), requestStatusDTO.statusName());
    }
}
