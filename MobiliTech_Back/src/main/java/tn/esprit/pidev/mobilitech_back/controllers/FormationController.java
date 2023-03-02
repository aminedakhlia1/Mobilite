package tn.esprit.pidev.mobilitech_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitech_back.entities.Formation;
import tn.esprit.pidev.mobilitech_back.repositories.FormationRepository;
import tn.esprit.pidev.mobilitech_back.services.FormationService;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    FormationService formationService;

    @Autowired
    FormationRepository formationRepository;

    @RequestMapping(value = "/saveFormation", method = RequestMethod.POST)
    public Formation saveFormation(@RequestBody Formation formation) {
        return formationService.saveFormation(formation);
    }

    @RequestMapping(value = "/allformations", method = RequestMethod.GET)
    public List<Formation> getformations ()  {

        return  formationService.getFormations();
    }

    /*@RequestMapping(value = "/updateFormation", method = RequestMethod.PUT)
    public Formation updateFormation(Long id, Formation formation){
        formation.setId(id);
        return formationService.saveFormation(formation);
    }*/

    @PutMapping("/updateFormation")
    public Formation updateFormation(@RequestBody Formation f) {
        Formation formation= formationService.updateFormation(f);
        return formation;
    }

    /*@RequestMapping(value = "/deleteFormation", method = RequestMethod.DELETE)
    public void  deleteFormation(Long id){

        formationService.deleteFormation(id);
    }*/

    @DeleteMapping("/deleteFormation/{formation-id}")
    public void deleteFormation(@PathVariable("formation-id") Long id) {

        formationService.deleteFormation(id);
    }

}
