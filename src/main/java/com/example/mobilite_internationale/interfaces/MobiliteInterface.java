package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.*;

import java.util.List;

public interface MobiliteInterface {
    /*-------------- User --------------*/
    public User addUser (User user);
    public List<User> retrieveAllUsers();
    public User retrieveUser (Long  idUser);
    public User updateUser (User user);
    public void removeUser(Long idUser);
    /*-------------- Message --------------*/
    public Message addMessage (Message mess);
    public List<Message> retrieveAllMessages();
    public Message retrieveMessage (Integer  idMess);
    public Message updateMessage (Message mess);
    public void removeMessage(Integer idmess);


    /*-------------- Opportunity --------------*/
    public Opportunity addOpportunity (Opportunity opportunity);
    public List<Opportunity> retrieveAllOpportunites();
    public Opportunity retrieveOpportunity (Integer  idOpportunity);
    public Opportunity updateOpportunity (Opportunity opportunity);
    public void removeOpportunity(Integer idOpportunity);

    /*-------------- Candidacy --------------*/
    public Candidacy addCandidacy (Candidacy candidacy);
    public List<Candidacy> retrieveAllCandidacies();
    public Candidacy retrieveCandidacy (Integer  idCandidacy);
    public Candidacy updateCandidacy (Candidacy candidacy);
    public void removeCandidacy(Integer idCandidacy);

    /*-------------- File --------------*/
    public File addFile (File file);
    public List<File> retrieveAllFiles();


}
