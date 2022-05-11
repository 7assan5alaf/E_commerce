package com.hassan.ecomerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hassan.ecomerce.model.Categories;
import com.hassan.ecomerce.repository.CatRepository;

@Service
public class CatService {
  @Autowired
  private CatRepository catRepository;

 public void setCatRepository(CatRepository catRepository) {
	 this.catRepository = catRepository;
  }
 
 public List<Categories> getAll(){
	 return catRepository.findAll();
 }
  
   public void addCat( Categories categories) {
	   catRepository.save(categories);
   }

  public void deleteCat(Long id) {
	catRepository.deleteById(id);
}
  public Optional<Categories> findCat(Long id) {
	  return Optional.of(catRepository.findById(id).get());
  }

}
