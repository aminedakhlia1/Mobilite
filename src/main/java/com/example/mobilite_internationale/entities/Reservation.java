package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    @Temporal(TemporalType.DATE)
    private Date checkinDate;
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;
    private Double totalPrice;
    @JsonIgnore
    @OneToOne(mappedBy = "reservation")
    private  Accommodation accommodation;
    @JsonIgnore
    @OneToOne(mappedBy = "reservation")
    private Restoration restoration;
    /**
     * @ManyToOne
     * private User user;
     * */
}
