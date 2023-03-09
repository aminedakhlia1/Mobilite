package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.Favoris;
import com.example.mobilite_internationale.entities.Publication;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.PublicationInterface;
import com.example.mobilite_internationale.repositories.CommentaireRepository;
import com.example.mobilite_internationale.repositories.Favorisrepository;
import com.example.mobilite_internationale.repositories.PublicationRepository;
import com.example.mobilite_internationale.repositories.UserRepository;
import com.example.mobilite_internationale.services.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    @Autowired
    PublicationInterface PublicationService;
@Autowired
    Favorisrepository favorisrepository;
@Autowired
    UserRepository userRepository;
@Autowired
    PublicationRepository pubrepo;
@Autowired
CommentaireRepository commentaireRepository;
    //ajout
    @PostMapping("/add-publication")
    public Publication addPublication(@RequestBody Publication rsv){
        //return PublicationService.addPublication(rsv);
        Publication Publication= PublicationService.addPublication(rsv);
        return  Publication;
    }

    @GetMapping("/retrieve-all-Publication")
    public List<Publication> retrieveAllResevations() {
        List<Publication> Publications = PublicationService.retrieveAllPublications();
        return Publications;
    }

    @GetMapping("/retrieve-Publication/{id-Publication}")
    public Publication retrievePublicationById(@PathVariable("id-Publication") Integer idPublication) {
        Publication pub = PublicationService.retrievePublication(idPublication);
        Favoris favoris = favorisrepository.getFavorisByPub(idPublication);
        System.out.println("xxxxxxxx"+favoris.getIdFavoris());
        if (favoris.getIdFavoris()>0)
        {
            favoris.setLastTimeSeen(LocalDateTime.now());
            favorisrepository.save(favoris);
            System.out.println(favoris.getLastTimeSeen());
            System.out.println(Instant.now());
        }
        return PublicationService.retrievePublication(idPublication);
    }

    @PutMapping("/update-Publication")
    public Publication updatePublication(@RequestBody Publication pub) {
        Publication Publication = PublicationService.updatePublication(pub);
        return Publication;
    }

    @DeleteMapping("/remove-Publication/{id-Publication}")
    public void removePublication(@PathVariable("id-Publication") Integer idPublication) {
        PublicationService.removePublication(idPublication);
    }

    @PostMapping("/add-Favoris-publication/{id-user}/{id-Publication}")
    public String addFavorisPublication(@PathVariable("id-user") Long idUser,
                                             @PathVariable("id-Publication") Integer idPublication){
        User user = userRepository.findById(idUser).orElse(null);
        Publication pub= PublicationService.retrievePublication(idPublication);
        Favoris favorie = new Favoris();
        favorie.setPublication(pub);
        favorie.setUser(user);
        favorisrepository.save(favorie);
return "ok";
    }
    @GetMapping("/Favoris-Publication/{id-user}/{id-Publication}")
    public ResponseEntity<String> PublicationFavories(@PathVariable("id-user") Long idUser,
                                                      @PathVariable("id-Publication") Integer idPublication) {
        String UnseenCommentString="";
        Publication pub = pubrepo.findById(idPublication).orElse(null);
        Favoris favoris = favorisrepository.getFavorisByPub(idPublication);
        System.out.println("******************");
        System.out.println();
        System.out.println("******************");
        List<Commentaire> UnseenComment = commentaireRepository.getCommentRecent(pub.getIdPub());
for (Commentaire cmnt:UnseenComment)
        {
            if (cmnt.getDateCmnt().isAfter(favoris.getLastTimeSeen()))
             UnseenCommentString += cmnt.getContenu()+"\n";

    }
        if (!UnseenComment.isEmpty())
       return ResponseEntity.status(HttpStatus.OK)
               .body("Publication :"+pub.getTitle()+" has new comments :\n"+
                       "User :"+pub.getUser().getUsername()+" : "+UnseenCommentString+" \n");

        return ResponseEntity.status(HttpStatus.OK)
                .body("No new Comment !");
    }


}
