package com.cj.freelanceapp.ServiceImp;


import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.repository.JobRepo;
import com.cj.freelanceapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImp implements JobService {
    @Autowired
    private JobRepo jobRepo;

    /**
     * ADD JOB TO DATABASE
     *
     * @param job
     */
    @Override
    public void add_job(Job job) {
        /**
         * SAVING JOB
         */
        jobRepo.save(job);
    }

    /**
     * GET JOB BY ID
     *
     * @param id
     * @return
     */
    @Override
    public Job get_job(long id) {
        return jobRepo.findByJobId(id);
    }

    /**
     * GET ALL JOBS
     *
     * @return
     */
    @Override
    public List<Job> all_job() {
        return jobRepo.findAll();
    }

    /**
     * GET ALL JOBS RELATED TO SPECIFIC CUSTOMER
     *
     * @param customer
     * @return
     */
    @Override
    public List<Job> my_jobs(Customer customer) {
        /**
         *
         */
        return jobRepo.findByCustomer(customer);
    }


    /**
     * GET JOBS BY PAGE
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Job> listJobs(Pageable pageable) {
        /**
         *
         */
        return jobRepo.findAll(pageable);
    }

    /**
     * UPDATE JOB
     *
     * @param id
     */
    @Override
    public void update_job(long id) {

    }

    /**
     * DELETE JOB BY ID
     *
     * @param id
     */
    @Override
    public void delete_job(long id) {
        jobRepo.deleteById(id);
    }

    /**
     * DELETE ALL JOBS
     */
    @Override
    public void delete_all() {
        jobRepo.deleteAll();
    }

	/**
	 *
	 * @param searchTerm
	 * @return
	 */
	@Override
    public List<Job> searchJob(String searchTerm) {
        List<Job> jobs = new ArrayList<>();
        jobRepo.findAll().forEach(e -> {
            Job job = e;
            if (
                    job.getCustomer().getUser().getFullName().contains(searchTerm) ||
                            job.getCustomer().getUser().getEmail().contains(searchTerm) ||
                            job.getCustomer().getUser().getPhoneNumber().contains(searchTerm) ||
                            job.getDescription().contains(searchTerm) ||
                            job.getEducationLevel().contains(searchTerm) ||
                            job.getEndDate().contains(searchTerm) ||
                            job.getPosted().contains(searchTerm) ||
                            job.getSkill().getSkillCategory().contains(searchTerm) ||
                            job.getSkill().getSkillDescription().contains(searchTerm) ||
                            job.getSkill().getSkillName().contains(searchTerm) ||
                            job.getSkill().getSkillCategory().contains(searchTerm)
            ) {
                jobs.add(job);
            }

        });
        return jobs;
    }

	/**
	 *
	 * @param searchTerm
	 * @param customer
	 * @return
	 */
    @Override
    public List<Job> myJobsSearch(String searchTerm, Customer customer) {
		List<Job> jobs = new ArrayList<>();
		my_jobs(customer).forEach(e -> {
			Job job = e;
			if (
					job.getCustomer().getUser().getFullName().contains(searchTerm) ||
							job.getCustomer().getUser().getEmail().contains(searchTerm) ||
							job.getCustomer().getUser().getPhoneNumber().contains(searchTerm) ||
							job.getDescription().contains(searchTerm) ||
							job.getEducationLevel().contains(searchTerm) ||
							job.getEndDate().contains(searchTerm) ||
							job.getPosted().contains(searchTerm) ||
							job.getSkill().getSkillCategory().contains(searchTerm) ||
							job.getSkill().getSkillDescription().contains(searchTerm) ||
							job.getSkill().getSkillName().contains(searchTerm) ||
							job.getSkill().getSkillCategory().contains(searchTerm)
			) {
				jobs.add(job);
			}
        });
        return jobs;
    }
}
