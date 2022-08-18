package com.cj.freelanceapp.ServiceImp;

import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.User;
import com.cj.freelanceapp.repository.FreelancerRepo;
import com.cj.freelanceapp.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreelancerServiceImp implements FreelancerService
/**
 * FREELANCER SERVICE IMPLEMENTATION CLASS
 * WITH ALL FREELANCER RELATED METHODS AND
 * OPERATIONS
 * AUTHOR MILLION SHARBE
 * DATE AUG 16 , 2022
 */

{
    /**
     * FREELANCER REPOSITORY OBJECT INJECTION
     */
    @Autowired
    private FreelancerRepo freelancerRepo;

    /**
     * @param freelancer
     */
    @Override
    public void add_freelancer(Freelancer freelancer) {
        /**
         * ADD FREELANCER TO DATABASE
         */
        freelancerRepo.save(freelancer);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Freelancer get_freelancer(long id) {
        /**
         * GET FREELANCER BY ID
         */
        return freelancerRepo.getReferenceById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Freelancer> all_freelancer() {
        return freelancerRepo.findAll();
    }

    /**
     * @param id
     */
    @Override
    public void update_freelancer(long id) {

    }

    /**
     * @param id
     */
    @Override
    public void delete_freelancer(long id) {
        freelancerRepo.deleteById(id);
    }

    /**
     *
     */
    @Override
    public void delete_all() {
        freelancerRepo.deleteAll();
    }

    @Override
    public Page<Freelancer> listFreelancers(Pageable pageable) {
        return freelancerRepo.findAll(pageable);
    }

    /**
     * @param fullName
     * @param pageable
     * @return
     */
    @Override
    public Page<Freelancer> searchByTerm(String fullName, Pageable pageable) {
        return freelancerRepo.findAll(pageable);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public Freelancer getFreelancerByUser(User user) {
        return freelancerRepo.getFreelancerByUser(user);
    }

    /**
     * @param availability
     * @return
     */
    @Override
    public List<Freelancer> getAllByAvailability(String availability) {
        return freelancerRepo.getAllByAvailability(availability);
    }

    /**
     * @param skill
     * @return
     */
    @Override
    public List<Freelancer> getAllBySkill(String skill) {
        return freelancerRepo.getAllBySkill(skill);
    }

    /**
     * @param searchTerm
     * @return
     */
    @Override
    public List<Freelancer> searchFreelancer(String searchTerm) {
        List<Freelancer> freelancers = new ArrayList<>();
        freelancerRepo.findAll().forEach(e -> {
            Freelancer freelancer = e;
            if (
                    freelancer.getUser().getFullName().contains(searchTerm) ||
                            freelancer.getUser().getEmail().contains(searchTerm) ||
                            freelancer.getUser().getPhoneNumber().contains(searchTerm) ||
                            freelancer.getAvailability().contains(searchTerm) ||
                            freelancer.getEducationLevel().contains(searchTerm) ||
                            freelancer.getBio().contains(searchTerm) ||
                            freelancer.getSkill().getSkillDescription().contains(searchTerm) ||
                            freelancer.getSkill().getSkillName().contains(searchTerm) ||
                            freelancer.getSkill().getSkillCategory().contains(searchTerm)
            ) {
                freelancers.add(freelancer);
            }
        });
        return freelancers;
    }
}
