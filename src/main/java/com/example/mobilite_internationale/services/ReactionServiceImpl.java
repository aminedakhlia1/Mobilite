package com.example.mobilite_internationale.services;


import com.example.mobilite_internationale.entities.Reaction;
import com.example.mobilite_internationale.interfaces.ReactionInterface;
import com.example.mobilite_internationale.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionInterface {

    @Autowired
    ReactionRepository Reactionrepo;

    @Override
    public Reaction addReaction(Reaction Reaction) {
        return Reactionrepo.save(Reaction);
    }

    @Override
    public List<Reaction> retrieveAllReactions() {
        return Reactionrepo.findAll();
    }

    @Override
    public Reaction retrieveReaction(Integer idReaction) {
        return Reactionrepo.findById(idReaction).orElse(null);
    }

    @Override
    public Reaction updateReaction(Reaction Reaction) {


        return Reactionrepo.save(Reaction);
    }

    @Override
    public void removeReaction(Integer idReaction) {
        Reactionrepo.deleteById(idReaction);
    }
}
