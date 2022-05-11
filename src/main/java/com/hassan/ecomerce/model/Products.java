package com.hassan.ecomerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "Categories_Id",referencedColumnName = "id")
   private Categories categories;
   private double price;
   private double weight;
   private String description;
   
   private String imageName;
   
   
public Products() {
	
}
public Products( String name, Categories categories, double price, double weghit, String description,
		String imageName) {
	super();
	this.id = id;
	this.name = name;
	this.categories = categories;
	this.price = price;
	this.weight = weghit;
	this.description = description;
	this.imageName = imageName;
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
public Categories getCategories() {
	return categories;
}
public void setCategories(Categories categories) {
	this.categories = categories;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getWeight() {
	return weight;
}
public void setWeight(double weghit) {
	this.weight = weghit;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
   

}
