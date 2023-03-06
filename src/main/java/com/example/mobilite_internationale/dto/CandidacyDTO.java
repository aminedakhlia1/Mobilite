package com.example.mobilite_internationale.dto;

import com.example.mobilite_internationale.entities.Speciality;
import com.example.mobilite_internationale.entities.Status;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidacyDTO {

    //private Integer id;
    private float average1Year;
    private float average2Year;
    private float average3Year;
    private float score;
    private Speciality speciality;
    private Status status;
    private String opportunityName;

}
