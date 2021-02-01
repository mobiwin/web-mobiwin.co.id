package com.mobiwin.websites.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_tb")
public class ContactModel {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="departement")
    private String departement;

    @Column(name="subject")
    private String subject;

    @Column(name="pesan")
    private String pesan;


    public ContactModel() {
    }

    public ContactModel(Long id, String name, String email, String departement, String subject, String pesan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departement = departement;
        this.subject = subject;
        this.pesan = pesan;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPesan() {
        return this.pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ContactModel id(Long id) {
        setId(id);
        return this;
    }

    public ContactModel name(String name) {
        setName(name);
        return this;
    }

    public ContactModel email(String email) {
        setEmail(email);
        return this;
    }

    public ContactModel departement(String departement) {
        setDepartement(departement);
        return this;
    }

    public ContactModel subject(String subject) {
        setSubject(subject);
        return this;
    }

    public ContactModel pesan(String pesan) {
        setPesan(pesan);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", departement='" + getDepartement() + "'" +
            ", subject='" + getSubject() + "'" +
            ", pesan='" + getPesan() + "'" +
            "}";
    }

}
