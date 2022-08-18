package com.cj.freelanceapp.controller;


import com.cj.freelanceapp.ServiceImp.FreelancerServiceImp;
import com.cj.freelanceapp.ServiceImp.JobApplicationServiceImp;
import com.cj.freelanceapp.ServiceImp.JobServiceImp;
import com.cj.freelanceapp.dto.JobApplicationDTO;
import com.cj.freelanceapp.helpers.STATUS;
import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.model.JobApplication;
import com.cj.freelanceapp.security.SuccessfullLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
public class JobApplicationController {
    @Autowired
    SuccessfullLoginHandler successfullLoginHandler;
    /**
     * JOB APPLICATION SERVICE INJECTION
     */
    @Autowired
    private JobApplicationServiceImp jobApplicationServiceImp;
    /**
     * JOB SERVICE INJECTION
     */
    @Autowired
    FreelancerServiceImp freelancerServiceImp;
    @Autowired
    JobServiceImp jobServiceImp;
    private Long jobId;

    /**
     * APPLY FORM REQUEST
     * @param id
     * @return
     */
    @RequestMapping("/apply")
    public ModelAndView addJobApplication(@RequestParam("coverLetter") String coverLetter,
                                          @RequestParam("id") Long id) {
        Job job = jobServiceImp.get_job(id);
        Freelancer freelancer = freelancerServiceImp.getFreelancerByUser(successfullLoginHandler.getUser());
        JobApplication jobApplication = JobApplication
                .builder()
                .applicationDate(new Date().toGMTString())
                .coverLetter(coverLetter)
                .job(job)
                .freelancer(freelancer)
                .status(STATUS.INREVIEW.name())
                .build();
        jobApplicationServiceImp.add_job_application(jobApplication);
        return new ModelAndView("appliedsuccessfully");
    }

    /**
     * APPLY FOR JOB REQUEST
     * @param jobApplicationDTO
     * @return
     */
//    @RequestMapping("/applyforjob{id}")
//    public String applyforjob(JobApplicationDTO jobApplicationDTO) {
//        System.out.println(jobServiceImp.get_job(jobId));
//        System.out.println(jobApplicationDTO);
//        return "jobapplications";
//    }

    /**
     * JOB APPLICATION
     * @param id
     * @return
     */
    @RequestMapping("/jobapplication{id}")
    public String getJobApplication(long id) {
        jobApplicationServiceImp.get_job_application(id);
        return "jobapplication.jsp";
    }

    /**
     * MY JOB APPLICATION
     * @param id
     * @return
     */
    @RequestMapping("/myjobapplications")
    public String myJob(int id) {
        return "myjobs.jsp";
    }

    /**
     * JOB APPLICATIONS
     * @return
     */
    @RequestMapping("/jobapplications")
    public ModelAndView allJobs() {
        List<JobApplication> jobApplications = jobApplicationServiceImp.all_job_application();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jobaaplications",jobApplications);
        modelAndView.setViewName("jobApplications");
        return modelAndView;
    }

    /**
     * EDIT JOB APPLICATION
     * @param jobApplicationDTO
     * @return
     */
    @RequestMapping("/editjobapplication")
    public String editJob(JobApplicationDTO jobApplicationDTO) {
        return "editjob.jsp";
    }

    /**
     * DELETE JOB APPLICATION
     * @param jobApplication
     * @return
     */
    @RequestMapping("/api/deletejobapplication")
    public String deleteJobApplication(JobApplicationDTO jobApplication) {
        return "deletejob.jsp";
    }

}
