package com.project.job_board_app.jobcontroller;

import com.project.job_board_app.constants.ConstantFile;
import com.project.job_board_app.jobservice.JobPostsService;
import com.project.job_board_app.model.JobPostsModel;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ConstantFile.JOB_ROUTE)
@Transactional
public class JobPostsController {

    @Autowired
    JobPostsService jobPostsService;

    @GetMapping("")
    public List<JobPostsModel> getAllJobs() {
        return jobPostsService.getAllJobs();
    }

    @GetMapping("/search/{key_word}")
    public List<JobPostsModel> searchJobs(@PathVariable(name = "key_word") String keyword) {
        return jobPostsService.searchJobs(keyword);
    }

    @GetMapping("/get-job/{id}")
    public JobPostsModel getJobById(@PathVariable String id) throws NotFoundException {
        return jobPostsService.getJobById(id);
    }
    @PostMapping("/save")
    public JobPostsModel addJob(@RequestBody JobPostsModel job) {
        return jobPostsService.saveJob(job);
    }

}
