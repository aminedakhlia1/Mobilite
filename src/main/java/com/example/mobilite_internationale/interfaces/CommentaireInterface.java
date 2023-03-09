package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.ReactType;

import java.util.List;

public interface CommentaireInterface {
    public Commentaire addCommentaire (Commentaire Commentaire);
    public List<Commentaire> retrieveAllCommentaires();
    public Commentaire retrieveCommentaire (Integer  idCommentaire);
    public Commentaire updateCommentaire (Commentaire Commentaire);
    public void removeCommentaire(Integer idCommentaire);

    public List<Commentaire> getCommentaireDesc();
    public List<Commentaire> getCommentaireReactMost(ReactType reactionType);
}
