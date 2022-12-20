package com.curdoperation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity//identify the class
public class Student {//encapulation_process

	@Id//indicate primary key
	@GeneratedValue//provide specification values to primary key
	private Long id;

	private String name;

	private String passportNumber;
	
}
