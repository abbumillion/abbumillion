package com.cj.freelanceapp.repository;


import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByJob(Job job);
    List<JobApplication> findByFreelancer(Freelancer freelancer);

}
