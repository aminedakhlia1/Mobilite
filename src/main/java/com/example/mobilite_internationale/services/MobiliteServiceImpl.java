package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.*;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import com.example.mobilite_internationale.repositories.CandidacyRepository;
import com.example.mobilite_internationale.repositories.FileRepository;
import com.example.mobilite_internationale.repositories.OpportunityRepository;
import com.example.mobilite_internationale.repositories.UserRepository;
import com.example.mobilite_internationale.utils.FileUploadUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MobiliteServiceImpl implements MobiliteInterface {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    CandidacyRepository candidacyRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    /*-------------- Opportunity --------------*/
    @Override
    public Opportunity addOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @Override
    public List<Opportunity> retrieveAllOpportunites() {
        return opportunityRepository.findAll();
    }

    @Override
    public Opportunity retrieveOpportunity(Integer idOpportunity) {
        return opportunityRepository.findById(idOpportunity).orElse(null);
    }

    @Override
    public Opportunity updateOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @Override
    public void removeOpportunity(Integer idOpportunity) {
        opportunityRepository.deleteById(idOpportunity);
    }


    //Services avancés
    @Override
    public List<Opportunity> searchOpportunityBySpeciality(Speciality speciality) {
        return opportunityRepository.findBySpeciality(speciality);
    }

    @Override
    public List<Opportunity> searchOpportunityByType(TypeOpportunity typeOpportunity) {
        return opportunityRepository.findByTypeOpportunity(typeOpportunity);
    }

    @Override
    public List<Opportunity> getAvailableOpportunities() {
        LocalDate currentDate = LocalDate.now();
        return opportunityRepository.findByEndDateGreaterThanEqual(currentDate);
        /* List<Opportunity> availableOpportunities = new ArrayList<>();
        for (Opportunity opportunity : opportunityRepository.findAll()){
            if(opportunity.getEndDate().isAfter(currentDate) || opportunity.getEndDate().isEqual(currentDate)) {
                availableOpportunities.add(opportunity);
            }
        }
        return availableOpportunities;*/
    }

    @Override
    public List<Opportunity> sortOpportunitiesByStartDateDesc() {
        return opportunityRepository.findAllByStartDateDesc();
    }

    /*-------------- QR CODE --------------*/
    public byte[] generateQRCodeForOpportunity(Integer idOpportunity) throws Exception {
        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        if (opportunity == null) {
            throw new Exception("Opportunity not found");
        }

        // Création du contenu du QR code
        String qrCodeContent =
                  "\nName Opportunity: " + opportunity.getNameOpportunity()
                + "\nDescription: " + opportunity.getDescription()
                + "\nStart date: " + opportunity.getStartDate()
                + "\nEnd date: " + opportunity.getEndDate()
                + "\nSpeciality: " + opportunity.getSpeciality()
                + "\nCapacity: " + opportunity.getCapacity()
                + "\nType: " + opportunity.getTypeOpportunity()
                + "\nFormula: " + opportunity.getFormula();

        // Génération du QR code en utilisant la bibliothèque QRGen
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);
        return baos.toByteArray();
    }


    /*-------------- Candidacy --------------*/
    @Override
    public Candidacy addCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public Candidacy addCandidacyWithFileAndAssignToOpportunity(Candidacy candidacy, Integer idOpportunity, MultipartFile multipartFile) throws IOException {
        //Récupérer opportunity par id
        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        //Affecter opportunity à candidature
        candidacy.setOpportunity(opportunity);
        Candidacy savedCandidacy = candidacyRepository.save(candidacy);

        //Save file
        File file = new File();
        file.setNameFile(multipartFile.getOriginalFilename());
        file.setPath("/uploadsFiles/" + file.getNameFile());
        file.setCandidacy(savedCandidacy);
        File savedFile = fileRepository.save(file);

        //Affecter candidature à file
        savedCandidacy.setFile(savedFile);
        candidacyRepository.save(savedCandidacy);
        return savedCandidacy;
    }

    @Override
    public List<Candidacy> retrieveAllCandidacies() {
        return candidacyRepository.findAll();
    }

    @Override
    public Candidacy retrieveCandidacy(Integer idCandidacy) {
        return candidacyRepository.findById(idCandidacy).orElse(null);
    }

    @Override
    public Candidacy updateCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public void removeCandidacy(Integer idCandidacy) {
        candidacyRepository.deleteById(idCandidacy);
    }

    @Override
    public void calculateScore(Integer idCandidacy) {
        Candidacy candidacy = candidacyRepository.findById(idCandidacy).orElse(null);

        candidacy.setScore((candidacy.getAverage_1year() + candidacy.getAverage_2year() + candidacy.getAverage_3year()) / 3);
        candidacyRepository.save(candidacy);
    }

    @Override
    public float calculateCandidacyScoreByOpportunity(Integer idOpportunity, Integer idCandidacy) {
        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        Candidacy candidacy = candidacyRepository.findById(idCandidacy).orElse(null);
        if (opportunity != null && candidacy != null) {
            String formula = opportunity.getFormula();
            Expression expression = new ExpressionBuilder(formula)
                    .variable("average1").variable("average2").variable("average3")
                    .build()
                    .setVariable("average1", candidacy.getAverage_1year())
                    .setVariable("average2", candidacy.getAverage_2year())
                    .setVariable("average3", candidacy.getAverage_3year());
            candidacy.setScore((float) expression.evaluate());
            candidacyRepository.save(candidacy);
        }
        return candidacy.getScore();
    }



    @Override
    public Candidacy updateCandidacyStatus(Integer idCandidacy) {
        Candidacy candidacy = candidacyRepository.findById(idCandidacy).orElse(null);
        calculateScore(idCandidacy);

        if (candidacy.getScore() >= 14) {
            candidacy.setStatus(Status.ACCEPTED);
        } else if (candidacy.getScore() >= 11) {
            candidacy.setStatus(Status.INPROGRESS);
        } else {
            candidacy.setStatus(Status.REFUSED);
        }
        return candidacyRepository.save(candidacy);
    }

    @Override
    public Integer countCandidaciesByOpportunity(Integer idOpportunity) {
        //return candidacyRepository.countCandidaciesByOpportunity(idOpportunity);
        return opportunityRepository.findById(idOpportunity).get().getCandidacyList().size();
    }


    /*-------------- File --------------*/
    @Override
    public File retrieveFile(Integer idFile) {
        return fileRepository.findById(idFile).orElse(null);
    }


    @Override
    public File saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File newFile = new File();
        newFile.setNameFile(fileName);
        newFile.setPath("/uploads/" + fileName);
        File savedFile = fileRepository.save(newFile);
        String uploadDir = "uploads/" + savedFile.getIdFile();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileRepository.save(savedFile);
    }

    public List<Candidacy> findCandidaciesByOpportunity(Integer idOpportunity) {
        return candidacyRepository.findByOpportunityIdOpportunity(idOpportunity);
    }

    @Override
    public void updateCandidacyStatus_TypeSelection(Integer idCandidacy) {

        Candidacy candidacy = candidacyRepository.findById(idCandidacy).orElse(null);
        Opportunity opportunity = candidacy.getOpportunity();

        if (opportunity.getTypeSelection() == TypeSelection.valueOf("ON_FILE")) {
            if (candidacy.getScore() < opportunity.getNoteEliminatoire()) {
                candidacy.setStatus(Status.REFUSED);
            } else {
                candidacy.setStatus(Status.ACCEPTED);
            }
            candidacyRepository.save(candidacy);
        }
        else if (opportunity.getTypeSelection() == TypeSelection.valueOf("BY_CAPACITY")) {
            List<Candidacy> candidacies = candidacyRepository.findByOpportunityOrderByScoreDesc(opportunity);
            int acceptedCount = 0;
            for (Candidacy candidacy1 : candidacies) {
                if (acceptedCount < opportunity.getCapacity()) {
                    candidacy.setStatus(Status.ACCEPTED);
                    acceptedCount++;
                } else {
                    candidacy.setStatus(Status.REFUSED);
                }
                candidacyRepository.save(candidacy1);
            }

        }

    }

    @Override
    public void updateCandidacyStatus_ByOpportunity(Integer idOpportunity) {
        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        if (opportunity == null) {
            throw new IllegalArgumentException("Opportunity with ID " + idOpportunity + " not found");
        }

        if (opportunity.getTypeSelection() == TypeSelection.ON_FILE) {
            List<Candidacy> candidacies = opportunity.getCandidacyList().stream()
                    .sorted(Comparator.comparing(Candidacy::getScore).reversed())
                    .collect(Collectors.toList());

            int acceptedCount = 0;
            for (Candidacy candidacy : candidacies) {
                if (acceptedCount >= opportunity.getCapacity() || candidacy.getScore() < opportunity.getNoteEliminatoire()) {
                    candidacy.setStatus(Status.REFUSED);
                } else {
                    candidacy.setStatus(Status.ACCEPTED);
                    acceptedCount++;
                }
            }
        } else if (opportunity.getTypeSelection() == TypeSelection.BY_CAPACITY) {
            List<Candidacy> candidacies = opportunity.getCandidacyList().stream()
                    .sorted(Comparator.comparing(Candidacy::getScore).reversed())
                    .collect(Collectors.toList());

            int acceptedCount = 0;
            for (Candidacy candidacy : candidacies) {
                if (acceptedCount >= opportunity.getCapacity()) {
                    candidacy.setStatus(Status.REFUSED);
                } else {
                    candidacy.setStatus(Status.ACCEPTED);
                    acceptedCount++;
                }
            }
        }
        opportunityRepository.save(opportunity);
    }

    /*-------------- Mailing --------------*/
    public void sendOpportunityEmailToStudents() throws MessagingException {

        Role studentRole = Role.Student;
        List<User> studentUsers = userRepository.findByRole(studentRole);

        for (User user : studentUsers) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Opportunité de mobilité internationale");
            message.setText("Cher(e) Etudiants ,\n\nNous avons le plaisir de vous informer que de nouvelles opportunités de mobilité internationale sont maintenant disponibles sur notre site.\n\nConnectez-vous dès maintenant pour découvrir les opportunités et postuler en ligne !\n\nCordialement,\nL'équipe de mobilité internationale.");

            mailSender.send(message);

        }
    }
}


