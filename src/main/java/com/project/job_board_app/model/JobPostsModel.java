package com.project.job_board_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class JobPostsModel {

    @JsonProperty(value = "job_number")
    private String jobNumber;

    @JsonProperty(value = "job_title")
    private String jobTitle;

    @JsonProperty(value = "company_name")
    private String companyName;

    @JsonProperty(value = "job_posted_date")
    private String jobPostedDate;

    @JsonProperty(value = "job_posted_time")
    private String jobPostedTime;

    @JsonProperty(value = "location")
    private List<String> location;

    @JsonProperty(value = "experience")
    private String experience;

    @JsonProperty(value = "salary")
    private String salary;

    @JsonProperty(value = "key_skills")
    private List<String> keySkills;

    @JsonProperty(value = "job_description")
    private String jobDescription;

    @Column(name = "applyLink")
    private String applyLink;

}
