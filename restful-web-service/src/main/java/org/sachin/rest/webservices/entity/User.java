package org.sachin.rest.webservices.entity;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	private Integer id;

	@Size(min = 2, max = 25, message = "Name should be minimum 2 and max 25 characters.")
	private String name;

	@Past
	private Date birthDay;

	public User() {

	}

	public User(Integer id, String name, Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDay = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return name;
	}
}
