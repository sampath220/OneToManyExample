package com.sample.mapper;

import com.sample.dto.StudentDTO;
import com.sample.entities.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    ModelMapper mapper=new ModelMapper();

    public StudentDTO convertToDto(Student student) {
        StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    public Student convertToEntity(StudentDTO studentDTO) {
        Student student = mapper.map(studentDTO, Student.class);
        return student;
    }
}
