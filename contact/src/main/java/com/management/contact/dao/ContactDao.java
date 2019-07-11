package com.management.contact.dao;

import com.management.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface ContactDao extends JpaRepository <Contact, Long> {

    /* Query native pour récuperer les listes séparées des profesionnel et non professionel*/
    @Query(value = "SELECT * FROM Contact c WHERE c.is_professionnal = 1", nativeQuery = true)
    ArrayList<Contact> findAllProfessionnalContact();
    @Query(value = "SELECT * FROM Gestion_Contact.Contact c WHERE c.is_professionnal = 0", nativeQuery = true)
    ArrayList<Contact> findAllNoProfessionnalContact();
}

