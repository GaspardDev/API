package com.management.contact.service;

import com.management.contact.dao.ContactDao;
import com.management.contact.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactDao contactDao;


    //Méthode qui cherche tous les contacts dans la base et renvoit une liste de contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contactDao.findAll().forEach(contacts::add);
        return contacts;
    }
    //Méthode de recherche par ID
    public Optional<Contact> getContact(long id){
        return contactDao.findById(id);
    }

    //Méthode d'ajout d'un contact
    public Contact addContact(Contact contact){
        contactDao.save(contact);
        return contact;
    }

    //Méthode de suppression d'un contact
    public void removeContact(long index){
        contactDao.deleteById(index);
    }

    //Méthode pour filtrer le tableau Professionnel ou non
    public ArrayList<Contact> findAllProfessionnalContact() { return contactDao.findAllProfessionnalContact(); }
    public ArrayList<Contact> findAllNoProfessionnalContact() { return contactDao.findAllNoProfessionnalContact(); }
}
