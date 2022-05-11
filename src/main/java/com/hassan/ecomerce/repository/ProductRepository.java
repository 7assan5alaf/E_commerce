package com.hassan.ecomerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hassan.ecomerce.model.Products;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products, Long> {
//	  @Query(value = "select p.name,p.price,p.weight,p.description,p.imageName from Categories c inner join Products p on c.id=p.category_id where c.id=?1")
	  List<Products> findAllByCategories_Id(Long id);
	  @Query("select p from Products p where " +"concat(p.name,p.price)"
	           +"like %?1%")
	           public Page<Products> search(String keyword,Pageable pageable);
	  

}
