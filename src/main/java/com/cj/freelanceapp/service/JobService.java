package com.cj.freelanceapp.service;


import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    /**
     * @param job
     */
    void add_job(Job job);

    /**
     * @param id
     * @return
     */

    Job get_job(long id);

    /**
     * @return
     */

    List<Job> all_job();

    /**
     * @param customer
     * @return
     */
    List<Job> my_jobs(Customer customer);

    /**
     * @param pageable
     * @return
     */

    Page<Job> listJobs(Pageable pageable);

    /**
     * @param id
     */

    void update_job(long id);

    /**
     * @param id
     */

    void delete_job(long id);

    /**
     *
     */

    void delete_all();

    /**
     * JOB SEARCH
     *
     * @param searchTerm
     * @return
     */
    List<Job> searchJob(String searchTerm);

    /**
     * MY JOB SEARCH
     *
     * @param searchTerm
     * @return
     */
    List<Job> myJobsSearch(String searchTerm, Customer customer);
}
