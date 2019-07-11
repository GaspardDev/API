package com.management.contact.controller;

import com.management.contact.exceptions.EntiteIntrouvableException;
import com.management.contact.model.Contact;
import com.management.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contact")
public class ContactController {


    @Autowired
    ContactService contactService;

    //Méthode qui cherche tous les contacts dans la base et renvoit une liste de contacts
    @GetMapping("")
    public ArrayList<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    /* Query native pour récuperer les listes séparées des profesionnel et non professionel*/
    @GetMapping("/getAllPro")
    public ArrayList<Contact> getAllProfessionnalContact() { return contactService.findAllProfessionnalContact(); }

    @GetMapping("/getAllNoPro")
    public ArrayList<Contact> getAllNoProfessionnalContact() { return contactService.findAllNoProfessionnalContact(); }

    //Méthode de recherche par ID
    @RequestMapping("/{id}")
    public Optional<Contact> getContactById(@PathVariable long id){
        isExist(id);
        return contactService.getContact(id);
    }

    //Méthode de création d'un contact
    @RequestMapping(method=RequestMethod.POST,value = "/add" )
    @ResponseStatus(HttpStatus.OK)
    public void addContact(@RequestBody Contact contact){

        Objects.requireNonNull(contact.getLastName(), "Il manque le nom de famille !");
        Objects.requireNonNull(contact.getFirstName(), "Il manque le prénom !");
        Objects.requireNonNull(contact.getAddress(), "L'adresse est manquante !");
        Objects.requireNonNull(contact.getIsProfessionnal(), "Renseignez si c'est un professionnel ou non !");
        Objects.requireNonNull(contact.getBirthDate(), "Date d'anniversaire manquante !");

        Contact _contact =
                contactService.addContact(
                        new Contact(
                                contact.getLastName(),
                                contact.getFirstName(),
                                contact.getAddress(),
                                contact.getIsProfessionnal(),
                                contact.getBirthDate()
                        )
                );

        Objects.requireNonNull(_contact, "La création du contact a échouée");
    }


    //Méthode de modification d'un contact
    @PutMapping(value = "/update/{id}")
    public Contact updateContact(@PathVariable long id,
                                 @Valid @RequestBody Contact contact) {

        isExist(id);
        Objects.requireNonNull(contact.getLastName(), "Il manque le nom de famille !");
        Objects.requireNonNull(contact.getFirstName(), "Il manque le prénom !");
        Objects.requireNonNull(contact.getAddress(), "L'adresse est manquante !");
        Objects.requireNonNull(contact.getIsProfessionnal(), "Renseignez si c'est un professionnel ou non !");
        Objects.requireNonNull(contact.getBirthDate(), "Date d'anniversaire manquante !");


        contact.setLastName(contact.getLastName());
        contact.setFirstName(contact.getFirstName());
        contact.setAddress(contact.getAddress());
        contact.setIsProfessionnal(contact.getIsProfessionnal());
        contact.setBirthDate(contact.getBirthDate());

        Contact updatedContact = contactService.addContact(contact);

        Objects.requireNonNull(contact, "La mise à jour du contact a échouée");

        return updatedContact;
    }

    //Méthode de suppression d'un contact
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        isExist(id);
        contactService.removeContact(id);
        return new ResponseEntity<>("le contact a bien été supprimé.", HttpStatus.OK);
    }

    private void isExist(Long id) {
        if (!contactService.getContact(id).isPresent()) {
            throw new EntiteIntrouvableException("Le contact n'existe pas.");
        }
    }
}
