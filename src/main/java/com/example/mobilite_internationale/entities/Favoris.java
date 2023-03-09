package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Favoris implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idFavoris;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "publication_id")
        private  Publication Publication;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @Column()
        private LocalDateTime lastTimeSeen;
    }
