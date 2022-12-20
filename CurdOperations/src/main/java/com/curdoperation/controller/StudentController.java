package com.curdoperation.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curdoperation.entity.Student;
import com.curdoperation.exception.StudentNotFoundException;
import com.curdoperation.repository.StudentRepository;

@RestController//RESTful services
//RESTful services means base URL for services supported MIME type and set of operation
//MIME types : XML,text,JSON,user-defined...
public class StudentController {

@Autowired
//auto_wire bean on the setter method just like @Required annotation, constructor, a property or methods with arbitrary names and/or multiple arguments
	private StudentRepository studentRepository; //its is like a call the class
	//get all
	@GetMapping("/students")  //GET
	public List<Student> retrieveAllStudents()  { //method name
		return studentRepository.findAll();//from predefined
	}
	//get with one
	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) throws StudentNotFoundException {
		//@PathVariable:to extract the value of the template variables and assign their value to a method variable
		Optional<Student> student = studentRepository.findById(id);

		if (student.isEmpty())
			throw new StudentNotFoundException("id-" + id);

		return student.get();
	}
	//delete with id
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepository.deleteById(id);
	}
	//create 
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	//update student name passport number with id
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Optional<Student> studentOptional = studentRepository.findById(id);

		if (studentOptional.isEmpty())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepository.save(student);

		return ResponseEntity.ok().build();
	}
}