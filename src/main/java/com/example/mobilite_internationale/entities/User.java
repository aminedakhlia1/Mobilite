package com.example.mobilite_internationale.entities;

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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    public  String Firstname;

    public  String Lastname;

    public  String Email;

    public  String Login;

    public  String Password;

    public  String Photo;

    public  String Universityname;

    public  String Societyname;

    @Enumerated(EnumType.STRING)
    private Role role;

    //Associations



}
