package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.dto.CandidacyDTO;
import com.example.mobilite_internationale.dto.SpecialityDTO;
import com.example.mobilite_internationale.entities.*;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Mobilite")
public class MobiliteController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    /*-------------- Opportunity --------------*/

    /*@PostMapping("affect-opportunity-to-user/{id-opportunity}/{id-user}")
    public void AffectOpportunityToUser(@PathVariable("id-opportunity") Integer idOpportunity,
                                        @PathVariable("id-user") Integer idUser) {
        mobiliteInterface.AffectOpportunityToUser(idOpportunity, idUser);
    }*/
    @PostMapping("addOpportunityAndAssignToUser/{id-user}")
    public ResponseEntity<Opportunity> addOpportunityAndAssignToUser(@RequestBody Opportunity opportunity,
                                                                     @PathVariable("id-user") Integer idUser) {
        Opportunity opportunity1 = mobiliteInterface.addOpportunityAndAssignToUser(opportunity, idUser);
        return ResponseEntity.ok(opportunity1);
    }

    @PostMapping("/add-opportunity")
    public Opportunity addOpportunity(@RequestBody Opportunity opp) {
        Opportunity opportunity = mobiliteInterface.addOpportunity(opp);
        return opportunity;
    }

    @GetMapping("/retrieve-all-opportunities")
    public List<Opportunity> retrieveAllOpportunites() {
        List<Opportunity> opportunities = mobiliteInterface.retrieveAllOpportunites();
        return opportunities;
    }

    @GetMapping("/retrieve-opportunity/{id-opportunity}")
    public Opportunity retrieveOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        return mobiliteInterface.retrieveOpportunity(idOpportunity);
    }

    @PutMapping("/update-opportunity")
    public Opportunity updateOpportunity(@RequestBody Opportunity opp) {
        Opportunity opportunity = mobiliteInterface.updateOpportunity(opp);
        return opportunity;
    }

    @DeleteMapping("/remove-opportunity/{id-opportunity}")
    public void removeContrat(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.removeOpportunity(idOpportunity);
    }

    @GetMapping("search-opportunity-by-speciality/{speciality}")
    public ResponseEntity<List<Opportunity>> searchOpportunityBySpeciality(@PathVariable String speciality) {
        Speciality spec = Speciality.valueOf(speciality);
        List<Opportunity> opportunities = mobiliteInterface.searchOpportunityBySpeciality(spec);
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("search-opportunity-by-type/{typeOpportunity}")
    public ResponseEntity<List<Opportunity>> searchOpportunityByType(@PathVariable String typeOpportunity) {
        TypeOpportunity typeOpp = TypeOpportunity.valueOf(typeOpportunity);
        List<Opportunity> opportunities = mobiliteInterface.searchOpportunityByType(typeOpp);
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("get-available-opportunities")
    public ResponseEntity<List<Opportunity>> getAvailableOpportunities() {
        List<Opportunity> availableOpportunities = mobiliteInterface.getAvailableOpportunities();
        return ResponseEntity.ok().body(availableOpportunities);
    }

    @GetMapping("sort-opportunities-by-startDate-Desc")
    public ResponseEntity<List<Opportunity>> sortOpportunitiesByStartDateDesc() {
        List<Opportunity> opportunities = mobiliteInterface.sortOpportunitiesByStartDateDesc();
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("generateQRCodeForOpportunity/{id-opportunity}")
    public ResponseEntity<byte[]> generateQRCodeForOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        try {
            byte[] qrCode = mobiliteInterface.generateQRCodeForOpportunity(idOpportunity);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stat-get-popular-specialities")
    public List<SpecialityDTO> getSpecialityCounts() {
        return mobiliteInterface.getPopularSpecialties();
    }

    /*-------------- Candidacy --------------*/
    @PostMapping("/add-candidacy")
    public Candidacy addCandidacy(@RequestBody Candidacy c, BindingResult result) {
        Candidacy candidacy = mobiliteInterface.addCandidacy(c);
        return candidacy;
    }

    @PostMapping(value = "/add-candidacy-file-and-assign-to-opportunity-and-user/{idOpportunity}/{idUser}", consumes = {"multipart/form-data"})
    public ResponseEntity<Candidacy> addCandidacyWithFile(@PathVariable("idOpportunity") Integer idOpportunity,
                                                          @PathVariable("idUser") Integer idUser,
                                                          @Valid @RequestParam("candidacy") String candidacyJson,
                                                          @RequestParam("file") MultipartFile file
    ) {

        //Convertir JSON string un objet
        ObjectMapper objectMapper = new ObjectMapper();
        Candidacy candidacy = null;
        try {
            candidacy = objectMapper.readValue(candidacyJson, Candidacy.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Candidacy savedCandidacy = mobiliteInterface.addCandidacyWithFileAndAssignToOpportunityAndUser(candidacy, idOpportunity, file, idUser);
        return new ResponseEntity<>(savedCandidacy, HttpStatus.CREATED);
    }


    @GetMapping("/retrieve-all-candidacies")
    public List<Candidacy> retrieveAllCandidacies() {
        List<Candidacy> candidacies = mobiliteInterface.retrieveAllCandidacies();
        return candidacies;
    }

    @GetMapping("/retrieve-candidacy/{id-candidacy}")
    public Candidacy retrieveCandidacy(@PathVariable("id-candidacy") Integer idCandidacy) {
        return mobiliteInterface.retrieveCandidacy(idCandidacy);
    }

    @PutMapping("/update-candidacy")
    public Candidacy updateCandidacy(@RequestBody Candidacy c) {
        Candidacy candidacy = mobiliteInterface.updateCandidacy(c);
        return candidacy;
    }

    @DeleteMapping("/remove-candidacy/{id-candidacy}")
    public void removeCandidacy(@PathVariable("id-candidacy") Integer idCandidacy) {
        mobiliteInterface.removeCandidacy(idCandidacy);
    }

    @GetMapping("/find-candicacies-by-opportunity/{id-opportunity}")
    public List<Candidacy> findCandidaciesByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        List<Candidacy> candidacies = mobiliteInterface.findCandidaciesByOpportunity(idOpportunity);
        return candidacies;
    }


    @GetMapping("/count-candidacies-by-opportunities/{id-opportunity}")
    public Integer countCandidaciesByOpportunityId(@PathVariable("id-opportunity") Integer idOpportunity) {
        return mobiliteInterface.countCandidaciesByOpportunity(idOpportunity);
    }

    @GetMapping("/calculateCandidacyScoreByOpportunity/{id-opportunity}/{id-candidacy}")
    public ResponseEntity<Float> calculateCandidacyScoreByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity,
                                                                      @PathVariable("id-candidacy") Integer idCandidacy) {
        float score = mobiliteInterface.calculateCandidacyScoreByOpportunity(idOpportunity, idCandidacy);
        return ResponseEntity.ok(score);
    }

    @PutMapping("/update-candidacy-status_By_Opportunity/{id-opportunity}")
    public void updateCandidacyStatus_ByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.updateCandidacyStatus_ByOpportunity(idOpportunity);
    }

    @GetMapping("/get-candidacy-history/{id-user}")
    public List<CandidacyDTO> getCandidacyHistory(@PathVariable("id-user") Integer idUser) {
        return mobiliteInterface.getCandidacyHistoryForStudent(idUser);
    }

    /*-------------- File --------------*/
    @GetMapping("/retrieve-file/{id-file}")
    public File retrieveFile(@PathVariable("id-file") Integer idFile) {
        return mobiliteInterface.retrieveFile(idFile);
    }


    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        mobiliteInterface.saveFile(file);
        return ResponseEntity.ok("File uploaded successfully");
    }

    /*-------------- Mailing --------------*/
    @PostMapping("/send-opportunity-email-to-students")
    public ResponseEntity<?> sendOpportunityEmailToStudents() {
        try {
            mobiliteInterface.sendOpportunityEmailToStudents();
            return ResponseEntity.ok("E-mails envoyés avec succès !");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi des e-mails : " + e.getMessage());
        }
    }

    @PostMapping("/send-email-for-new-opportunity/{id-opportunity}")
    public void sendEmailsForNewOpportunities(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.sendEmailsForNewOpportunities(idOpportunity);
    }

    @PostMapping("/send-email-to-candidacies/{id-opportunity}")
    public void sendEmailsToCandidacy(@PathVariable("id-opportunity") Integer idOpportunity){
        mobiliteInterface.sendEmailsToCandidacy(idOpportunity);
    }
}


