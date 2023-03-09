package tn.esprit.pidev.mobilitechdraft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.Formation;
import tn.esprit.pidev.mobilitechdraft.services.FormationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formations")
public class FormationController {

    @Autowired
    FormationService formationService;


    @RequestMapping(value = "/create-formation", method = RequestMethod.POST)
    public ResponseEntity<String> createFormation(@Valid @RequestBody Formation formation) {

        formationService.createFormation(formation);
        return ResponseEntity.ok("formation created successfully");
    }


    @GetMapping("/get-formation/{formationId}")
    public Optional<Formation> getFormation(@PathVariable("formationId") Long formationId) {

        return formationService.getFormation(formationId);
    }


    @RequestMapping(value = "/get-all-formation", method = RequestMethod.GET)
    public List<Formation> getAllFormation ()  {

        return  formationService.getAllFormation();
    }


    @PutMapping("/update-formation/{formationId}")
    public Formation updateFormation(@PathVariable("formationId") Long formationId, @RequestBody Formation formation) {

        formation.setFormationId(formationId);
        return formationService.updateFormation(formation);
    }


    @DeleteMapping("/delete-formation/{formationId}")
    public void deleteFormation(@PathVariable("formationId") Long formationId) {

        formationService.deleteFormation(formationId);
    }
}
