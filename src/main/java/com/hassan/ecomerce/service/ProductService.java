package com.hassan.ecomerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hassan.ecomerce.dto.ProductDto;
import com.hassan.ecomerce.model.Categories;
import com.hassan.ecomerce.model.Products;
import com.hassan.ecomerce.repository.ProductRepository;


@Service
public class ProductService {
   
	@Autowired
	private ProductRepository productRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Page<Products>getAll(int pageNumber ,String keyword){
		Sort sort=Sort.by("name").ascending();
		Pageable page=PageRequest.of(pageNumber-1, 4,sort);
		
		if(keyword!=null) {
			return productRepository.search(keyword, page);
		}
		return productRepository.findAll(page);
	}
	public List<Products>getAll(){
		return (List<Products>) productRepository.findAll();
	}
	 
	public void addProduct(Products product) {
		productRepository.save(product);
	}
	public void removeProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Products> getProduct(Long id) {
		return productRepository.findById(id);
	}
	
	public List<Products>getAllByCategory(Long id){
		return productRepository.findAllByCategories_Id(id);
		
	}
}
