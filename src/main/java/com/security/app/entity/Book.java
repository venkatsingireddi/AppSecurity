package com.security.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Api(description="This will be store the information of the book")
@ApiModel(description="This will be store the information of the book")
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	int id;
	@Size(min=2,message="name should be greater than 2 character")
	@ApiModelProperty(notes="name should be have more than 2 character")
	String name;
	
	@Size(min=2,message="password should be greater than 2 character")
	@ApiModelProperty(notes="password should be have more than 2 character")
	String password;
	public Book() {
		super();
	}
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}


		
}
