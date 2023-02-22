package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restoration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResotration;
    private Boolean typeResotration;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Reservation reservation;
}
