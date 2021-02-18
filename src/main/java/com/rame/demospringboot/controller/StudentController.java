package com.rame.demospringboot.controller;

import com.rame.demospringboot.model.Student;
import com.rame.demospringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.rame.demospringboot.model.Student.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping("/all")
    public Iterable<Student> getAllCustomer() {
        return repo.findAll();
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id) {
        Student updatedStudent = null;
        Optional<Student> student = repo.findById(id);
        if(student.isPresent()) {
            updatedStudent = student.get();
            updatedStudent.setFirstName(updatedStudent.getFirstName() + "_updated");

        }

        return repo.save(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
                repo.deleteById(id);
    }



}