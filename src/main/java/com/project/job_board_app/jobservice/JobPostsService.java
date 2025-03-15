package com.project.job_board_app.jobservice;

import com.project.job_board_app.entity.JobPosts;
import com.project.job_board_app.jobrepository.JobPostsRepository;
import com.project.job_board_app.model.JobPostsModel;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostsService {

    @Autowired
    JobPostsRepository jobPostsRepository;

    @Transactional
    public List<JobPostsModel> getAllJobs() {
        List<JobPosts> jobPosts = jobPostsRepository.findAll();

        return jobPosts.stream().map(JobPostsService::setJobPostsModel).collect(Collectors.toList());
    }

    private static JobPostsModel setJobPostsModel(JobPosts job) {
        JobPostsModel model = new JobPostsModel();
        model.setJobNumber(job.getJobNumber());
        model.setJobTitle(job.getJobTitle());
        model.setCompanyName(job.getCompanyName());
        model.setJobPostedDate(job.getJobPostedDate());
        model.setJobPostedTime(job.getJobPostedTime());

        // Handling location properly
        String location = job.getLocation();
        if (location != null && !location.isEmpty()) {
            location = location.replaceAll("[{}]", "");
            List<String> locations = Arrays.stream(location.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            model.setLocation(locations);
        }

        model.setExperience(job.getExperience());
        model.setSalary(job.getSalary());

        // Handling key skills properly
        String keySkills = job.getKeySkills();
        if (keySkills != null && !keySkills.isEmpty()) {
            List<String> skillsList = Arrays.stream(keySkills.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            model.setKeySkills(skillsList);
        }

        model.setJobDescription(job.getJobDescription());
        model.setApplyLink(job.getApplyLink());
        return model;
    }

    @Transactional
    public List<JobPostsModel> searchJobs(String keyword) {
       List<JobPosts> jobPosts = jobPostsRepository.findBySearchKey(keyword);
        return jobPosts.stream().map(JobPostsService::setJobPostsModel).collect(Collectors.toList());
    }

    @Transactional
    public JobPostsModel getJobById(String id) throws NotFoundException {
        JobPosts jobPostById = jobPostsRepository.findByJobNumber(id);
        if (jobPostById != null && jobPostById.getJobNumber() != null) {
            return setJobPostsModel(jobPostById);
        } else {
            throw new NotFoundException("jobs not found " + id);
        }
    }

    @Transactional
    public JobPostsModel saveJob(JobPostsModel job) {
        JobPosts byJobNumber = jobPostsRepository.findByJobNumber(job.getJobNumber());
        if (byJobNumber != null && byJobNumber.getJobNumber() != null) {
            return setJobPostsModel(byJobNumber);
        }

        JobPosts jobPosts = setJobPostsEntity(job);
        jobPosts = jobPostsRepository.save(jobPosts);
        return setJobPostsModel(jobPosts);
    }

    private JobPosts setJobPostsEntity(JobPostsModel job) {
        JobPosts jobPost = new JobPosts();
        jobPost.setJobNumber(job.getJobNumber());
        jobPost.setJobTitle(job.getJobTitle());
        jobPost.setCompanyName(job.getCompanyName());
        jobPost.setJobPostedDate(job.getJobPostedDate());
        jobPost.setJobPostedTime(job.getJobPostedTime());

        List<String> locations = job.getLocation();
        if (locations != null && !locations.isEmpty()) {

            String locationString = String.join(", ", locations);
            jobPost.setLocation(locationString);
        }

        jobPost.setExperience(job.getExperience());
        jobPost.setSalary(job.getSalary());

        List<String> keySkills = job.getKeySkills();
        if (keySkills != null && !keySkills.isEmpty()) {
            String keySkillsString = String.join(", ", keySkills);
            jobPost.setKeySkills(keySkillsString);
        }

        jobPost.setJobDescription(job.getJobDescription());
        jobPost.setApplyLink(job.getApplyLink());
        return jobPost;

    }

}
