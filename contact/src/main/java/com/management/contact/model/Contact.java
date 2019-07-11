package com.management.contact.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private long id;

    @Column(name = "last_name", nullable = false)
    @Size(max = 45)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @Size(max = 45)
    private String firstName;

    @Column(name = "address", nullable = false)
    @Size(max = 45)
    private String address;

    @Column(name = "is_professionnal", nullable = false)
    private int isProfessionnal;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;


    //Constructor
    public Contact() {
    }

    public Contact(@Size(max = 45) String lastName, @Size(max = 45) String firstName, @Size(max = 45) String address, int isProfessionnal, Date birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.isProfessionnal = isProfessionnal;
        this.birthDate = birthDate;
    }


    //Getters and Setters
    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsProfessionnal() {
        return isProfessionnal;
    }

    public void setIsProfessionnal(int isProfessionnal) {
        this.isProfessionnal = isProfessionnal;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", isProfessionnal=" + isProfessionnal +
                ", birthDate=" + birthDate +
                '}';
    }
}
