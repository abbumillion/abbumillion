package com.cj.freelanceapp.controller;


import com.cj.freelanceapp.ServiceImp.JobApplicationServiceImp;
import com.cj.freelanceapp.ServiceImp.JobServiceImp;
import com.cj.freelanceapp.dto.JobApplicationDTO;
import com.cj.freelanceapp.model.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JobApplicationController {
    /**
     * JOB APPLICATION SERVICE INJECTION
     */
    @Autowired
    private JobApplicationServiceImp jobApplicationServiceImp;
    /**
     * JOB SERVICE INJECTION
     */
    @Autowired
    JobServiceImp jobServiceImp;
    private Long jobId;

    /**
     * APPLY FORM REQUEST
     * @param id
     * @return
     */
    @RequestMapping("/apply{id}")
    public ModelAndView addJobApplication(Long id) {
        jobId = id;
//         job = jobServiceImp.get_job(id);
//         System.out.println(job.getCustomer().getUser().getFullName());
//        JobApplication jobApplication = JobApplication.builder()
//                .coverLetter(jobApplicationDTO.getCoverLetter())
//                .job(jobApplicationDTO.getJob())
//                .applicationDate(jobApplicationDTO.getApplicationDate())
//                .build();
//        jobApplicationServiceImp.add_job_application(jobApplication);
        return new ModelAndView("applyform");
    }

    /**
     * APPLY FOR JOB REQUEST
     * @param jobApplicationDTO
     * @return
     */
    @RequestMapping("/applyforjob")
    public String applyforjob(JobApplicationDTO jobApplicationDTO) {
        System.out.println(jobServiceImp.get_job(jobId));
        System.out.println(jobApplicationDTO);
        return "jobapplications";
    }

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
    public String allJobs() {
        jobApplicationServiceImp.all_job_application();
        return "jobs.jsp";
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
