package com.cj.freelanceapp.service;


import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.model.JobApplication;

import java.util.List;

public interface JobApplicationService {
    /**
     *
     * @param jobApplicationModel
     */
    void add_job_application(JobApplication jobApplication);

    /**
     *
     * @param id
     * @return
     */
    JobApplication get_job_application(long id);

    /**
     *
     * @return
     */
    List<JobApplication> all_job_application();

    List<JobApplication> freelancer_job_application(Freelancer freelancer);

    List<JobApplication> job_job_application(Job job);

    /**
     *
     * @param id
     */
    void update_job_application(long id);

    /**
     *
     * @param id
     */
    void delete_job_application(long id);

    /**
     *
     */
    void delete_all();
}
