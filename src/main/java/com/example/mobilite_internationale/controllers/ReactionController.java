package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Reaction;
import com.example.mobilite_internationale.interfaces.ReactionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reaction")
public class ReactionController {
    @Autowired
    ReactionInterface ReactionService;

    //ajout
    @PostMapping("/add-Reaction")
    public Reaction addReaction(@RequestBody Reaction react){
        //return ReactionService.addReaction(rsv);
        Reaction Reaction= ReactionService.addReaction(react);
        return  Reaction;
    }

    @GetMapping("/retrieve-all-Reaction")
    public List<Reaction> retrieveAllResevations() {
        List<Reaction> Reactions = ReactionService.retrieveAllReactions();
        return Reactions;
    }

    @GetMapping("/retrieve-Reaction/{id-Reaction}")
    public Reaction retrieveReactionById(@PathVariable("id-Reaction") Integer idReaction) {
        return ReactionService.retrieveReaction(idReaction);
    }

    @PutMapping("/update-Reaction")
    public Reaction updateReaction(@RequestBody Reaction react) {
        Reaction Reaction = ReactionService.updateReaction(react);
        return Reaction;
    }

    @DeleteMapping("/remove-Reaction/{id-Reaction}")
    public void removeReaction(@PathVariable("id-Reaction") Integer idReaction) {
        ReactionService.removeReaction(idReaction);
    }

}
