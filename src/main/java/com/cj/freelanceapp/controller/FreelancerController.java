package com.cj.freelanceapp.controller;


import com.cj.freelanceapp.ServiceImp.FreelancerServiceImp;
import com.cj.freelanceapp.ServiceImp.SkillServiceImp;
import com.cj.freelanceapp.dto.FreelancerDTO;
import com.cj.freelanceapp.dto.FreelancerProfileDTO;
import com.cj.freelanceapp.dto.UserDTO;
import com.cj.freelanceapp.exception.EthioFreelancingApplicationException;
import com.cj.freelanceapp.helpers.EDUCATIONLEVEL;
import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.Skill;
import com.cj.freelanceapp.model.User;
import com.cj.freelanceapp.security.SuccessfullLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class FreelancerController {
    /**
     * FREELANCER MODULE CONTROLLER WITH REST END POINT DEFINITIONS
     * AUTHOR MILLION SHARBE
     * DATE JULY 28 , 2022
     */
    /**
     * SUCCESSFULL LOGIN HANDLER INJECTION
     */
    @Autowired
    SuccessfullLoginHandler successfullLoginHandler;
    /**
     * FREELANCER SERVICE INJECTION
     */
    @Autowired
    private FreelancerServiceImp freelancerServiceImp;
    /**
     * SKILL SERVICE INJECTION
     */
    @Autowired
    private SkillServiceImp skillServiceImp;

    /**
     * INJECTING FREELANCER SERVICE TO THE
     * FREELANCER CONTROLLER
     * @param freelancerServiceImp
     */


    /**
     * @return ALL FREELANCERS
     */
    @RequestMapping("/freelancers")
    public ModelAndView freelancers() {
        /**
         * DECLARE NEW LIST TO HOLD FREELANCER DTOS
         */
        List<FreelancerDTO> freelancerDTOS = new ArrayList<>();
        /**
         * ITERATE THROUGH THE LIST OF FREELANCERS
         * AND CONVERT IT TO FREELANCER DTO AND ADD IT TO
         * LIST OF FREELANCER DTO
         */
        freelancerServiceImp.all_freelancer().forEach(e -> {
            Freelancer freelancer = (Freelancer) e;
            FreelancerDTO freelancerDTO =
                    FreelancerDTO
                            .builder()
                            .Id(freelancer.getId())
                            .user(
                                    UserDTO
                                            .builder()
                                            .email(freelancer.getUser().getEmail())
                                            .fullName(freelancer.getUser().getFullName())
                                            .id(freelancer.getUser().getId())
                                            .isActive(freelancer.getUser().isActive())
                                            .password(freelancer.getUser().getPassword())
                                            .phoneNumber(freelancer.getUser().getPhoneNumber())
                                            .role(freelancer.getUser().getRole())
                                            .rating(freelancer.getRating())
                                            .image(freelancer.getUser().getImage())
                                            .build()
                            )
                            .build();
            freelancerDTOS.add(freelancerDTO);
        });

        /**
         * return new model and view object
         */
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("freelancers", freelancerDTOS);
        return modelAndView;
    }


    @RequestMapping("/api/freelancers")
    public List<FreelancerDTO> getAllFreelancers() {
        List<FreelancerDTO> freelancerDTOS = new ArrayList<>();
        /**
         * ITERATE THROUGH THE LIST OF FREELANCERS
         * AND CONVERT IT TO FREELANCER DTO AND ADD IT TO
         * LIST OF FREELANCER DTO
         */
        freelancerServiceImp.all_freelancer().forEach(e -> {
            Freelancer freelancer = (Freelancer) e;
            FreelancerDTO freelancerDTO =
                    FreelancerDTO
                            .builder()
                            .Id(freelancer.getId())
                            .user(
                                    UserDTO
                                            .builder()
                                            .email(freelancer.getUser().getEmail())
                                            .fullName(freelancer.getUser().getFullName())
                                            .id(freelancer.getUser().getId())
                                            .isActive(freelancer.getUser().isActive())
                                            .password(freelancer.getUser().getPassword())
                                            .phoneNumber(freelancer.getUser().getPhoneNumber())
                                            .role(freelancer.getUser().getRole())
                                            .rating(freelancer.getRating())
                                            .build()
                            )
                            .build();
            freelancerDTOS.add(freelancerDTO);
        });
        return freelancerDTOS;
    }

    /**
     * @param id
     * @return FREELANCER WITH SPECIFIED ID
     */
    @RequestMapping("/api/get_freelancer")
    public FreelancerDTO getFreelancer(long id) {
        FreelancerDTO freelancerDTO = FreelancerDTO
                .builder()
                .build();
        return freelancerDTO;
    }

    @RequestMapping("/freelancerhome")
    public ModelAndView home() {
        User user = successfullLoginHandler.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("freelancer-home");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    /**
     * ADD FREELANCER TO THE DATABASE
     *
     * @param freelancerDTO
     * @return
     */
    @RequestMapping("/api/add_customer")
    public String addFreelancer(FreelancerDTO freelancerDTO) {
        return "freelancer added";
    }

    /**
     * @return all job applications related with specific freelancer
     */
    @RequestMapping("api/edit_job")
    public String allJob() {
        return "jobs";
    }

    /**
     * @return FREELANCER BY CATEGORY
     */
    @RequestMapping("/uploadCV")
    public ModelAndView freelancer_category(@RequestParam("cv") MultipartFile cv) {
        Freelancer freelancer = freelancerServiceImp.getFreelancerByUser(successfullLoginHandler.getUser());
        String fileName = cv.getOriginalFilename();
        try {
            if (!cv.isEmpty()) {
                cv.transferTo(new File("C:\\Users\\Thinkpad\\Desktop\\FYP\\freelanceapp\\FreelancerCVs\\" + fileName));
                freelancer.setCv(fileName);
            } else {
                throw new EthioFreelancingApplicationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("successfullupload");
    }

    /**
     * FREELANCER PROFILE REQUEST HANDLER
     *
     * @return
     */
    @RequestMapping("/freelancerprofile")
    public ModelAndView freelancerProfile() {
        Freelancer freelancer = freelancerServiceImp.getFreelancerByUser(successfullLoginHandler.getUser());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("freelancer", freelancer);
        return modelAndView;

    }

    @RequestMapping("/buildprofile")
    public ModelAndView buildProfile() {
        Freelancer freelancer = freelancerServiceImp.getFreelancerByUser(successfullLoginHandler.getUser());
        List<Skill> skills = skillServiceImp.all_skill();
        List<String> els = new ArrayList<>();
        for (EDUCATIONLEVEL educationlevel : EDUCATIONLEVEL.values())
        {
            els.add(educationlevel.name());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("skills", skills);
        modelAndView.addObject("els",els);
        modelAndView.setViewName("buildprofile");
        return modelAndView;

    }

        @RequestMapping("/saveprofile")
    public ModelAndView buildProfile(FreelancerProfileDTO freelancerProfileDTO) {
            /**
             * GET FREELANCER BY USING LOGGED IN USER ACCOUNT
             */
        Freelancer freelancer = freelancerServiceImp.getFreelancerByUser(successfullLoginHandler.getUser());
            /**
             * GET SKILL CHOOSEN BY THE USER
             */
        Skill skill = skillServiceImp.getSkillBySkillName(freelancerProfileDTO.getSkill());
            /**
             * CONVERT DTO DATA TO FREELANCER SETTERS
             */
        freelancer.setAvailability(freelancerProfileDTO.getAvailability());
        freelancer.setBio(freelancerProfileDTO.getBio());
        freelancer.setEducationLevel(freelancerProfileDTO.getEducationLevel());
        freelancer.setSkill(skill);
            /**
             * SAVE THE FREELANCER TO THE DATABASE
             */
        freelancerServiceImp.add_freelancer(freelancer);
            /**
             *
             */
        ModelAndView modelAndView = new ModelAndView();
            /**
             * REDIRECT IT THE LOGIN PAGE
             */
        modelAndView.setViewName("freelancerprofile");
            /**
             * RETURN THE MODEL AND VIEW OBJECT
             */
        return modelAndView;
    }



}
