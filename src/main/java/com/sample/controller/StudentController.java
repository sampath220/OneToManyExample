package com.sample.controller;

import com.sample.dto.StudentDTO;
import com.sample.entities.College;
import com.sample.entities.Student;
import com.sample.exception.ResourceNotFoundException;
import com.sample.mapper.StudentMapper;
import com.sample.repository.CollegeRepo;
import com.sample.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CollegeRepo collegeRepo;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/college/{clgId}/students")
    public List<Student> getAllStudentsByClgId(@PathVariable int clgId) {
        return studentRepo.findByCollegeId(clgId);
    }

    @PostMapping("/college/{clgId}/student")
    public StudentDTO insertStudent(@PathVariable int clgId, @RequestBody Student student) {
        // Student student=studentMapper.convertToEntity(studentDTO);
        return collegeRepo.findById(clgId).map(college -> {
            student.setCollege(college);
            return studentMapper.convertToDto(studentRepo.save(student));
        }).orElseThrow(() -> new ResourceNotFoundException("CollegeId " + clgId + " not found"));
//        studentRepo.save(student);
//        System.out.println(student);
//        return new ResponseEntity<>(studentMapper.convertToDto(student), HttpStatus.OK);
    }

    @PutMapping("/college/{clgId}/student/{stId}")
    public Student updateStudent(@PathVariable int clgId,@PathVariable int stId, @RequestBody Student studentRequest) {
        if(!collegeRepo.existsById(clgId)) {
            throw new ResourceNotFoundException("CollegeId " + clgId + " not found");
        }

        return studentRepo.findById(stId).map(student -> {
            student.setAddress(studentRequest.getAddress());
            student.setName(studentRequest.getName());
            return studentRepo.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + stId + "not found"));
    }

    @DeleteMapping("/college/{clgId}/student/{stdId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int clgId,@PathVariable int stdId) {

        return studentRepo.findByIdAndCollegeId(stdId,clgId).map(student -> {
            studentRepo.delete(student);
            return new ResponseEntity<>(student.getName() + " is deleted", HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + stdId + " not found"));
    }

}
