package com.sample.repository;

import com.sample.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);

    List<Student> findByAddress(String address);

    List<Student> findByNameAndAddress(String name, String address);

    List<Student> findByCollegeId(int id);

    Optional<Student> findByIdAndCollegeId(int id, int CollegeId);
}
