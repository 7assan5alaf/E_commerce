package com.hassan.ecomerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     
     @NotEmpty
     @Column(nullable = false)
     private String name;
     @ManyToMany(mappedBy = "roles")
     private List<User>users=new ArrayList<User>();
     
	public Role() {
	
	}
	public Role( @NotEmpty String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
     
     
}
