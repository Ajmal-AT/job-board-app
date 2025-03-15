package com.project.job_board_app.jobrepository;

import com.project.job_board_app.entity.JobPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostsRepository extends JpaRepository<JobPosts, Long> {

    @Query("SELECT j FROM JobPosts j " +
            "WHERE LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.keySkills) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobPosts> findBySearchKey(@Param("keyword") String keyword);

    JobPosts findByJobNumber(String id);

}
