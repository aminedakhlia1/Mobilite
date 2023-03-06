package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.dto.CandidacyDTO;
import com.example.mobilite_internationale.entities.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface MobiliteInterface {

    /*-------------- Opportunity --------------*/
    public Opportunity addOpportunityAndAssignToUser(Opportunity opportunity, Integer idUser);
    public Opportunity addOpportunity (Opportunity opportunity);
    public List<Opportunity> retrieveAllOpportunites();
    public Opportunity retrieveOpportunity (Integer idOpportunity);
    public Opportunity updateOpportunity (Opportunity opportunity);
    public void removeOpportunity(Integer idOpportunity);
    public List<Opportunity> searchOpportunityBySpeciality(Speciality speciality);
    public List<Opportunity> searchOpportunityByType(TypeOpportunity typeOpportunity);
    public List<Opportunity> getAvailableOpportunities();
    public List<Opportunity> sortOpportunitiesByStartDateDesc();
    public byte[] generateQRCodeForOpportunity(Integer idOpportunity) throws Exception;

    /*-------------- Candidacy --------------*/
    public Candidacy addCandidacy (Candidacy candidacy);
    public Candidacy addCandidacyWithFileAndAssignToOpportunityAndUser(Candidacy candidacy, Integer idOpportunity,
                                                                       MultipartFile multipartFile, Integer idUser);
    public List<Candidacy> retrieveAllCandidacies();
    public Candidacy retrieveCandidacy (Integer idCandidacy);
    public Candidacy updateCandidacy (Candidacy candidacy);
    public void removeCandidacy(Integer idCandidacy);
    public List<Candidacy> findCandidaciesByOpportunity(Integer idOpportunity);
    public Integer countCandidaciesByOpportunity(Integer idOpportunity);
    public float calculateCandidacyScoreByOpportunity(Integer idOpportunity, Integer idCandidacy);
    public void updateCandidacyStatus_ByOpportunity(Integer idOpportunity);
    public List<CandidacyDTO> getCandidacyHistoryForStudent(Integer idUser);


    /*-------------- File --------------*/
    public File retrieveFile (Integer idFile);
    //public void saveFile(MultipartFile multipartFile) throws IOException;
    public File saveFile(MultipartFile multipartFile) throws IOException;

    /*-------------- Mailing --------------*/
    public void sendOpportunityEmailToStudents() throws MessagingException;


}
