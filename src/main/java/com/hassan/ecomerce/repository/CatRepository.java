package com.hassan.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hassan.ecomerce.model.Categories;
@Repository
public interface CatRepository extends JpaRepository<Categories, Long> {

}
