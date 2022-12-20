package com.curdoperation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curdoperation.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	//predefined class import here...
}