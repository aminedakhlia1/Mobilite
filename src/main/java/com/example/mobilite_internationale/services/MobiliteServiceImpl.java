package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.*;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import com.example.mobilite_internationale.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MobiliteServiceImpl implements MobiliteInterface {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    CandidacyRepository candidacyRepository;

    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRepository userRepository;

    /*-------------- User --------------*/

    @Override
    public User addUser(User user) {return userRepository.save(user);}

    @Override
    public List<User> retrieveAllUsers() {return userRepository.findAll();}

    @Override
    public User retrieveUser(Long idUser) {return userRepository.findById(idUser).orElse(null);}

    @Override
    public User updateUser(User user) {return userRepository.save(user);}

    @Override
    public void removeUser(Long idUser) {userRepository.deleteById(idUser);}
    /*-------------- Message --------------*/

    @Override
    public Message addMessage(Message mess) {
        return messageRepository.save(mess);
    }


    @Override
    public List<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message retrieveMessage(Integer idMess) {
        return messageRepository.findById(idMess).orElse(null);
    }

    @Override
    public Message updateMessage(Message mess) {
        return messageRepository.save(mess);
    }

    @Override
    public void removeMessage(Integer idmess) {
        messageRepository.deleteById(idmess);

    }

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


    /*-------------- Candidacy --------------*/
    @Override
    public Candidacy addCandidacy(Candidacy candidacy) {
        return candidacyRepository.save(candidacy);
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


    /*-------------- File --------------*/
    @Override
    public File addFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public List<File> retrieveAllFiles() {
        return fileRepository.findAll();
    }


}
