package com.cj.freelanceapp.repository;

import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerRepo extends JpaRepository<Freelancer, Long> {

    Freelancer getFreelancerByUser(User user);

    List<Freelancer> getAllByAvailability(String availability);

    List<Freelancer> getAllBySkill(String skill);


}
