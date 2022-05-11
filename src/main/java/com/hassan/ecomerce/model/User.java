package com.hassan.ecomerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
       @NotEmpty 
       @Column(nullable = false)
       private String firstName;
       private String lastName;
       @Column(nullable = false,unique = true)
       @NotEmpty
       @Email(message = "{errors.invalid_email}")
       private String email;
       @NotEmpty
       private String password;
       
       @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
       @JoinTable(joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
       inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
       private List<Role>roles=new ArrayList<Role>();
       
       public User() {
		
	}

       public User(User user) {
   		firstName=user.getFirstName();
   		lastName=user.getLastName();
   		email=user.getEmail();
   		password=user.getPassword();
   	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

   

      
}
