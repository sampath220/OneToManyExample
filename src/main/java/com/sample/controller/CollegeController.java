package com.sample.controller;

import com.sample.entities.College;
import com.sample.exception.ResourceNotFoundException;
import com.sample.repository.CollegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollegeController {

    @Autowired
    private CollegeRepo collegeRepo;

    @GetMapping("/college")
    public List<College> getAllColleges() {
        return collegeRepo.findAll();
    }

    @PostMapping("/college")
    public College createCollege(@RequestBody College college) {
        return collegeRepo.save(college);
    }

    @PutMapping("/college/{clgId}")
    public College updateCollege(@PathVariable int clgId, @RequestBody College college) {
        return collegeRepo.findById(clgId).map(clg -> {
            clg.setAddress(college.getAddress());
            clg.setName(college.getName());
            return collegeRepo.save(clg);
        }).orElseThrow(() -> new ResourceNotFoundException("CollegeId " + college.getId() + " not found"));
    }

    @DeleteMapping("/college/{clgId}")
    public ResponseEntity<String> deleteCollege(@PathVariable int clgId) {

        return collegeRepo.findById(clgId).map(clg -> {
            collegeRepo.delete(clg);
            return new ResponseEntity<>(clg.getName() + " is deleted", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("CollegeId " + clgId + " not found"));
    }
}
