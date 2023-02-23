package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Reaction;

import java.util.List;

public interface ReactionInterface {
    public Reaction addReaction (Reaction Reaction);
    public List<Reaction> retrieveAllReactions();
    public Reaction retrieveReaction (Integer  idReaction);
    public Reaction updateReaction (Reaction Reaction);
    public void removeReaction(Integer idReaction);
}
