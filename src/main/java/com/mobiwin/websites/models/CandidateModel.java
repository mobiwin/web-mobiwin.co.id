package com.mobiwin.websites.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate_tb")
public class CandidateModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ip_user")
    private String ipUser;

    @Column(name = "id_career")
    private String idCareer;

    @Column(name = "status")
    private String status;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "candidate_email")
    private String candidateEmail;

    @Column(name = "candidate_skill")
    private String candidateSkill;

    @Column(name = "candidate_desc")
    private String candidateDesc;
    
    @Column(name = "candidate_cv_path")
    private String candidateCvPath;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpUser() {
        return this.ipUser;
    }

    public void setIpUser(String ipUser) {
        this.ipUser = ipUser;
    }

    public String getIdCareer() {
        return this.idCareer;
    }

    public void setIdCareer(String idCareer) {
        this.idCareer = idCareer;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCandidateName() {
        return this.candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return this.candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateSkill() {
        return this.candidateSkill;
    }

    public void setCandidateSkill(String candidateSkill) {
        this.candidateSkill = candidateSkill;
    }

    public String getCandidateDesc() {
        return this.candidateDesc;
    }

    public void setCandidateDesc(String candidateDesc) {
        this.candidateDesc = candidateDesc;
    }

    public String getCandidateCvPath() {
        return this.candidateCvPath;
    }

    public void setCandidateCvPath(String candidateCvPath) {
        this.candidateCvPath = candidateCvPath;
    }

}
