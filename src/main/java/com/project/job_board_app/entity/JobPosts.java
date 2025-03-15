package com.project.job_board_app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "job_posts")
public class JobPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_number")
    private String jobNumber;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_posted_date")
    private String jobPostedDate;

    @Column(name = "job_posted_time")
    private String jobPostedTime;

    @Column(name = "location", columnDefinition = "text")
    private String location;

    @Column(name = "experience")
    private String experience;

    @Column(name = "salary")
    private String salary;

    @Column(name = "key_skills", columnDefinition = "text")
    private String keySkills;

    @Column(name = "job_description", columnDefinition = "text")
    private String jobDescription;

    @Column(name = "applyLink")
    private String applyLink;

}
