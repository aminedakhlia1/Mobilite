package com.example.mobilite_internationale.services;


import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.interfaces.CommentaireInterface;
import com.example.mobilite_internationale.repositories.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireInterface {

    @Autowired
    CommentaireRepository Commentairerepo;

    @Override
    public Commentaire addCommentaire(Commentaire Commentaire) {
        return Commentairerepo.save(Commentaire);
    }

    @Override
    public List<Commentaire> retrieveAllCommentaires() {
        return Commentairerepo.findAll();
    }

    @Override
    public Commentaire retrieveCommentaire(Integer idCommentaire) {
        return Commentairerepo.findById(idCommentaire).orElse(null);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire Commentaire) {


        return Commentairerepo.save(Commentaire);
    }

    @Override
    public void removeCommentaire(Integer idCommentaire) {
        Commentairerepo.deleteById(idCommentaire);
    }
}
