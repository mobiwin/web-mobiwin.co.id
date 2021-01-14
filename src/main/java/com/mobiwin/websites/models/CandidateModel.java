package com.mobiwin.websites.models;

import java.util.Date;

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

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "candidate_skill")
    private String candidateSkill;

    @Column(name = "candidate_desc")
    private String candidateDesc;
    
    @Column(name = "candidate_cv_path")
    private Date candidateCvPath;

    @Column(name = "created_at")
    private Date createdAt;
}
