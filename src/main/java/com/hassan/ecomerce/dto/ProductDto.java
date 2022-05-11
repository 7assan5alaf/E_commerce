package com.hassan.ecomerce.dto;

public class ProductDto {
	private Long id;
	private String name;
   private Long categoryId;
   private double price;
   private double weight;
   private String description;
   private String imageName;
   
   
public ProductDto() {
}
public ProductDto( String name, Long category_id, double price, double weghit, String description,
		String imageName) {
	super();
	this.name = name;
	this.categoryId = category_id;
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
public Long getCategoryId() {
	return categoryId;
}
public void setCategoryId(Long category_id) {
	this.categoryId = category_id;
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
