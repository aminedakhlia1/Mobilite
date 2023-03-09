package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.Publication;
import com.example.mobilite_internationale.entities.ReactType;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.CommentaireInterface;
import com.example.mobilite_internationale.repositories.UserRepository;
import com.example.mobilite_internationale.services.PublicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Commentaire")
public class CommentaireController {
    @Autowired
    CommentaireInterface CommentaireService;

    //ajout
    @Autowired
    UserRepository userrepo;
    @Autowired
    PublicationServiceImpl pubService;
    //ajout
    @PostMapping("/add-Commentaire/{id-user}/{id-pub}")
    public ResponseEntity<String> addCommentaire(@RequestBody Commentaire cmnt, @PathVariable("id-user") Long idUser,
                                                 @PathVariable("id-pub") Integer idpub){
        //return CommentaireService.addCommentaire(rsv);
        User u=userrepo.findById(idUser).orElse(null);
        Publication pub=pubService.retrievePublication(idpub);
        if (pub.getBloquercmnt())
            return ResponseEntity.badRequest()
                    .body("Comments are disabled by author !");
        cmnt.setUser(u);
        cmnt.setPublication(pub);
        cmnt.setDateCmnt(LocalDateTime.now());
        CommentaireService.addCommentaire(cmnt);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Comment added successfully !");
    }

    @GetMapping("/retrieve-all-Commentaire")
    public List<Commentaire> retrieveAllResevations() {
        List<Commentaire> Commentaires = CommentaireService.retrieveAllCommentaires();
        return Commentaires;
    }

    @GetMapping("/retrieve-Commentaire/{id-Commentaire}")
    public Commentaire retrieveCommentaireById(@PathVariable("id-Commentaire") Integer idCommentaire) {
        return CommentaireService.retrieveCommentaire(idCommentaire);
    }

    @PutMapping("/update-Commentaire")
    public Commentaire updateCommentaire(@RequestBody Commentaire cmnt) {
        Commentaire Commentaire = CommentaireService.updateCommentaire(cmnt);
        return Commentaire;
    }

    @DeleteMapping("/remove-Commentaire/{id-Commentaire}")
    public void removeCommentaire(@PathVariable("id-Commentaire") Integer idCommentaire) {
        CommentaireService.removeCommentaire(idCommentaire);
    }

    @GetMapping("/recents")
    public List<Commentaire> getCommentairesRecents() {
        return CommentaireService.getCommentaireDesc();
    }
    @GetMapping("/MostReacted")
    public List<Commentaire> getCommentairesMostReacted(@RequestParam("type") ReactType reactionType) {
        return CommentaireService.getCommentaireReactMost(reactionType);
    }

}
