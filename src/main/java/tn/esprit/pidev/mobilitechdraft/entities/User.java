package tn.esprit.pidev.mobilitechdraft.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

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


}
