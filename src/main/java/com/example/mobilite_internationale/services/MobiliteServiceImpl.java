package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.dto.CandidacyDTO;
import com.example.mobilite_internationale.dto.SpecialityDTO;
import com.example.mobilite_internationale.dto.UserDTO;
import com.example.mobilite_internationale.entities.*;
import com.example.mobilite_internationale.entities.File;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.Blob;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public Opportunity addOpportunityAndAssignToUser(Opportunity opportunity, Integer idUser) {
        User user = userRepository.findById(idUser).get();
        Role role = user.getRole();
        if (role == Role.University || role == Role.Society) {
            opportunity.setUser(user);
            return opportunityRepository.save(opportunity);
        } else {
            throw new IllegalArgumentException("User must have UNIVERSITY or SOCIETY role to add opportunities.");
        }
    }



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
                + "\nType: " + opportunity.getTypeOpportunity()
                + "\nDescription: " + opportunity.getDescription()
                + "\nStart date: " + opportunity.getStartDate()
                + "\nEnd date: " + opportunity.getEndDate()
                + "\nSpeciality: " + opportunity.getSpeciality()
                + "\nType Selection: " + opportunity.getTypeSelection()
                + "\nNote Elimonatoire: " + opportunity.getNoteEliminatoire()
                + "\nFormula: " +opportunity.getFormula();

        // Génération du QR code en utilisant la bibliothèque QRGen
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", baos);
        return baos.toByteArray();
    }

    //Stat
    @Override
    public List<SpecialityDTO> getPopularSpecialties() {
        List<Object[]> resultList = opportunityRepository.findSpecialityCounts();

        return resultList.stream()
                .map(result -> new SpecialityDTO(result[0].toString(), ((Long) result[1]).intValue()))
                .collect(Collectors.toList());
    }

    /*-------------- Candidacy --------------*/
    @Override
    public Candidacy addCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
    }

    @Override
    public Candidacy addCandidacyWithFileAndAssignToOpportunityAndUser(Candidacy candidacy, Integer idOpportunity,
                                                                MultipartFile multipartFile, Integer idUser) {

        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        candidacy.setOpportunity(opportunity);

        User user = userRepository.findById(idUser).orElse(null);
        if (user.getRole() != Role.Student) {
            throw new IllegalArgumentException("Only users with the student role can create candidacies.");
        }
            candidacy.setUser(user);
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
    public List<Candidacy> findCandidaciesByOpportunity(Integer idOpportunity) {
        return candidacyRepository.findByOpportunityIdOpportunity(idOpportunity);
    }

    @Override
    public Integer countCandidaciesByOpportunity(Integer idOpportunity) {
        //return candidacyRepository.countCandidaciesByOpportunity(idOpportunity);
        return opportunityRepository.findById(idOpportunity).get().getCandidacyList().size();
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

    @Override
    public List<CandidacyDTO> getCandidacyHistoryForStudent(Integer idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user.getRole() != Role.Student) {
            throw new IllegalArgumentException("Only users with the student role can create candidacies.");
        }
        List<Candidacy> candidacies = candidacyRepository.findAllByUser(user);

        // Map candidacies to DTOs
        List<CandidacyDTO> candidacyDTOs = new ArrayList<>();
        for (Candidacy candidacy : candidacies) {
            CandidacyDTO candidacyDTO = new CandidacyDTO();
            candidacyDTO.setAverage1Year(candidacy.getAverage_1year());
            candidacyDTO.setAverage2Year(candidacy.getAverage_2year());
            candidacyDTO.setAverage3Year(candidacy.getAverage_3year());
            candidacyDTO.setScore(candidacy.getScore());
            candidacyDTO.setSpeciality(candidacy.getSpeciality());
            candidacyDTO.setStatus(candidacy.getStatus());
            candidacyDTO.setOpportunityName(candidacy.getOpportunity().getNameOpportunity());
            candidacyDTOs.add(candidacyDTO);
        }

        return candidacyDTOs;
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

    @Override
    public void sendEmailsForNewOpportunities(Integer idOpportunity) {
        Opportunity opportunity = opportunityRepository.findById(idOpportunity).orElse(null);
        List<User> students = userRepository.findByRole(Role.Student);
        for (User student : students) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(student.getEmail());
            message.setSubject("New Opportunity is Available !");
            message.setText("Dear Students \n\n"
                    + "A new opportunity called \"" + opportunity.getNameOpportunity() + "\" is now available.\n\n "
                    + "for more information on this opportunity please see the pdf file below.\n\n"
                    + "here is the link to submit your candidacy. \n\n"
                    + "Best regards,\n"
                    + "MobiliTech");
            mailSender.send(message);
        }
    }



}


