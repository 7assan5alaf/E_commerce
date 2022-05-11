package com.hassan.ecomerce.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Categories {

@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String Name;
 
  public Categories(){
	  
  }
  
public Categories(String name) {
	Name = name;
	this.id=id;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
 
}
