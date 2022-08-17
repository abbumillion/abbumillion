package com.cj.freelanceapp.repository;


import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    List<Job> findByCustomer(Customer customer);





}
