package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.ReactType;
import com.example.mobilite_internationale.interfaces.CommentaireInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Commentaire")
public class CommentaireController {
    @Autowired
    CommentaireInterface CommentaireService;

    //ajout
    @PostMapping("/add-Commentaire")
    public Commentaire addCommentaire(@RequestBody Commentaire rsv){
        //return CommentaireService.addCommentaire(rsv);
        Commentaire Commentaire= CommentaireService.addCommentaire(rsv);
        return  Commentaire;
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
