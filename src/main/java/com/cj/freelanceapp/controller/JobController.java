package com.cj.freelanceapp.controller;

import com.cj.freelanceapp.ServiceImp.CustomerServiceImp;
import com.cj.freelanceapp.ServiceImp.JobServiceImp;
import com.cj.freelanceapp.ServiceImp.SkillServiceImp;
import com.cj.freelanceapp.dto.JobDTO;
import com.cj.freelanceapp.helpers.EDUCATIONLEVEL;
import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.model.Skill;
import com.cj.freelanceapp.model.User;
import com.cj.freelanceapp.security.SuccessfullLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class JobController
        /**
         * THIS IS A JOB MODULE CONTROLLER DEFINITIONS WITH
         * REST API END POINTS FOR INTERACTING WITH THE APPLICATION THROUGH
         * THE OPEN REST END POINTS HAS REST CONTROLLER ANNOTATION
         * AUTHOR THOMAS FASIL
         * DATE JUL,2022
         */
{
    /**
     * JOB DATA TRANSFER OBJECT
     */
    private JobDTO jobDTO;
    /**
     * JOB SERVICE IMPLEMENTATION INJECTION
     */
    @Autowired
    private JobServiceImp jobServiceImp;
    /**
     * SKILL SERVICE IMPLEMENTATION INJECTION
     */
    @Autowired
    private SkillServiceImp skillServiceImp;

    /**
     * CUSTOMER SERVICE IMPLEMENTATION INJECTION
     */
    @Autowired
    CustomerServiceImp customerServiceImp;

    @Autowired
    SuccessfullLoginHandler successfullLoginHandler;


    /**
     * METHOD FOR POSTING JOB
     *
     * @param jobDTO
     * @return
     */
    @RequestMapping(value = "/postjob")
    public ModelAndView postJob(JobDTO jobDTO) {
        /**
         * GET THE SKILL OBJECT
         */

        System.out.println(jobDTO.getDescription());

        StringBuilder stringBuilder = new StringBuilder(jobDTO.getDescription());


        User user = successfullLoginHandler.getUser();
        Skill skill = skillServiceImp.getSkillBySkillName(jobDTO.getSkill());
        Customer customer = customerServiceImp.findCustomerByUser(user);
        Job job = Job.builder()
                .budget(jobDTO.getBudget())
                .customer(customer)
                .description(stringBuilder.toString())
                .endDate(jobDTO.getEnddate())
                .posted(jobDTO.getPosted())
                .skill(skill)
                .type(jobDTO.getType())
                .educationLevel(jobDTO.getEducationLevel())
                .build();
        jobServiceImp.add_job(job);
        List<Job> allJobs = jobServiceImp.my_jobs(customerServiceImp.findCustomerByUser(successfullLoginHandler.getUser()));
        ModelAndView modelAndView = new ModelAndView("myjobs");
        modelAndView.addObject("allJobs", allJobs);
        return modelAndView;
    }

    /**
     * METHOD FOR DISPLAYING JOB FORM
     * WITH SKILLS LIST
     *
     * @return
     */
    @RequestMapping("/jobform")
    public ModelAndView post() {
        List<Skill> skills = skillServiceImp.all_skill();
        List els = List.of(EDUCATIONLEVEL.values());
        ModelAndView modelAndView = new ModelAndView("jobform");
        modelAndView.addObject("skills", skills);
        modelAndView.addObject("els", els);
        return modelAndView;
    }


    @RequestMapping("/searchskillSubmit")
    public ModelAndView skillSearch(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(value = "size", defaultValue = "400", required = false) Integer size,
                                    @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        Page<Skill> allUsers = skillServiceImp.searchByTerm(searchTerm.trim(), PageRequest.of(page, size, Sort.by("skillName")));
        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("maxTraySize", size);
        modelAndView.addObject("currentPage", page);
        return modelAndView;
    }

    @RequestMapping("/api/myjobs")
    public List<Job> myJob(long id) {
        return jobServiceImp.all_job();
    }

    /**
     * @param page
     * @param size
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/jobs")
    public ModelAndView jobs(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                             @RequestParam(value = "size", defaultValue = "16", required = false) Integer size,
                             HttpServletRequest request, HttpServletResponse response) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        List<String> list = new ArrayList<>();
        authorities.forEach(e -> {
            list.add(e.getAuthority());
        });
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jobs");
        List<Job> allJobs = jobServiceImp.all_job();
        modelAndView.addObject("allJobs", allJobs);
        return modelAndView;
    }

    @RequestMapping("/myjobs")
    public ModelAndView myJobs(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                               @RequestParam(value = "size", defaultValue = "16", required = false) Integer size,
                               HttpServletRequest request, HttpServletResponse response) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> list = new ArrayList<>();
        authorities.forEach(e -> {
            list.add(e.getAuthority());
        });
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myjobs");
        List<Job> allJobs = jobServiceImp.my_jobs(customerServiceImp.findCustomerByUser(successfullLoginHandler.getUser()));
        modelAndView.addObject("allJobs", allJobs);
        return modelAndView;
    }
    /**
     * @param job
     * @return
     */
    @RequestMapping("/job{id}")
    public ModelAndView editJob(@PathVariable Long id) {
        Job job = jobServiceImp.get_job(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("job",job);
        modelAndView.setViewName("job");
        return modelAndView;
    }
    @RequestMapping("/myjob{id}")
    public ModelAndView myjob(@PathVariable Long id) {
        Job job = jobServiceImp.get_job(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("job",job);
        modelAndView.setViewName("myjob");
        return modelAndView;
    }

    @RequestMapping("/jobsearch")
    public ModelAndView jobsearch(@RequestParam("searchTerm") String searchTerm) {
        List<Job> jobs = jobServiceImp.searchJob(searchTerm);
//        System.out.println(jobs.size());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allJobs",jobs);
        modelAndView.setViewName("jobs");
        return modelAndView;
    }

    @RequestMapping("/myjobsearch")
    public ModelAndView myjobsearch(@RequestParam("searchTerm") String searchTerm) {
        List<Job> jobs = jobServiceImp.searchJob(searchTerm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allJobs",jobs);
        modelAndView.setViewName("myjobs");
        return modelAndView;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/deletejob{id}")
    public ModelAndView deleteJob(@PathVariable Long id) {
        jobServiceImp.delete_job(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myjobs");
        List<Job> allJobs = jobServiceImp.my_jobs(customerServiceImp.findCustomerByUser(successfullLoginHandler.getUser()));
        modelAndView.addObject("allJobs", allJobs);
        return modelAndView;
    }
}