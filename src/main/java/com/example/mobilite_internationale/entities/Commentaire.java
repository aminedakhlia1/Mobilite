package com.example.mobilite_internationale.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComment;
    private  String contenu;
    @Temporal(TemporalType.DATE)
    private Date dateCmnt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPub")
    private Publication publication;

    @OneToMany(mappedBy = "commentaire", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Reaction> reactions;
}
