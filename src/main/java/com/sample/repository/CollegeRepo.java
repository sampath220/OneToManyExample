package com.sample.repository;


import com.sample.entities.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepo extends JpaRepository<College, Integer> {

}
