package com.cj.freelanceapp.controller;

import com.cj.freelanceapp.ServiceImp.CustomerServiceImp;
import com.cj.freelanceapp.ServiceImp.JobApplicationServiceImp;
import com.cj.freelanceapp.ServiceImp.JobServiceImp;
import com.cj.freelanceapp.dto.CustomerDTO;
import com.cj.freelanceapp.dto.UserDTO;
import com.cj.freelanceapp.exception.InvalidAdminException;
import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.Job;
import com.cj.freelanceapp.model.JobApplication;
import com.cj.freelanceapp.security.SuccessfullLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class CustomerController {
    /**
     *
     */
    @Autowired
    private CustomerServiceImp customerServiceImp;
    @Autowired
    private SuccessfullLoginHandler successfullLoginHandler;
    @Autowired
    JobApplicationServiceImp jobApplicationServiceImp;
    @Autowired
    JobServiceImp jobServiceImp;

    /**
     *
     * @return
     */
    @RequestMapping("/api/customers")
    public List<Customer> getAllCustomers() {
        return customerServiceImp.all_customer();
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/api/customer")
    public Customer getAdmin(long id)
    /**
     * RETURN A SINGLE CUSTOMER WITH THIS ID
     */
    {
        return customerServiceImp.get_customer(id);
    }






    /**
     *
     * @return
     */
    @RequestMapping(value = "/api/customer/findAll")
    public List<CustomerDTO> findAll() {
        try {
            List<Customer> customers = customerServiceImp.all_customer();
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            for (Customer customer : customers) {
                CustomerDTO adminDTO = CustomerDTO.builder()
                        .Id(customer.getId())
                        .user(
                                /**
                                 * BUILDER PATTERNS
                                 * FOR CREATING CUSTOMER DTO
                                 */
                                UserDTO.builder()
                                        .id(customer.getUser().getId())
                                        .fullName(customer.getUser().getFullName())
                                        .email(customer.getUser().getEmail())
                                        .isActive(customer.getUser().isActive())
                                        .password(customer.getUser().getPassword())
                                        .phoneNumber(customer.getUser().getPhoneNumber())
//                                        .rating(customer.getUser().getRating())
                                        .role(customer.getUser().getRole())
                                        .build()
                        )
                        .build();
                /**
                 * ADDING CUSTOMER DTO TO CUSTOMER DATA TRANSFER LIST
                 */
                customerDTOS.add(adminDTO);
            }
            return customerDTOS;
        } catch (InvalidAdminException exception) {
            throw new InvalidAdminException("Admin  not found");
        }
    }

//    @RequestMapping("/customerprofile")
//    public ModelAndView freelancerProfile() {
//        Customer customer = customerServiceImp.findCustomerByUser(successfullLoginHandler.getUser());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("customer", freelancer);
//        return modelAndView;
//
//    }

    @RequestMapping("/cjobapplications")
    public ModelAndView allJobs() {
        Customer customer = customerServiceImp.findCustomerByUser(successfullLoginHandler.getUser());
        List<Job> jobs = jobServiceImp.my_jobs(customer);

        List<JobApplication> jobApplications = new ArrayList<>();

        jobs.forEach(e->{

           jobApplications.addAll(jobApplicationServiceImp.job_job_application(e));
        });
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jobaaplications",jobApplications);
        modelAndView.setViewName("jobapplications");
        return modelAndView;
    }

}
