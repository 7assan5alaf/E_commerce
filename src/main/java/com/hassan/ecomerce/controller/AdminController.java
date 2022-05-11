package com.hassan.ecomerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hassan.ecomerce.dto.ProductDto;
import com.hassan.ecomerce.model.Categories;
import com.hassan.ecomerce.model.Products;
import com.hassan.ecomerce.service.CatService;
import com.hassan.ecomerce.service.ProductService;

@Controller
public class AdminController {
	 
	
	 public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productimages";
	 @Autowired
	 private CatService catService;
	 public void setCatService(CatService catService) {
			this.catService = catService;
		}
	 @Autowired
	 private ProductService productService;
	 public void setProductService(ProductService productService) {
			this.productService = productService;
		}
	 
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String  getCat(Model model) {
		List<Categories>categories=catService.getAll();
		model.addAttribute("categories", categories);
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String addCat(Model model) {
		model.addAttribute("category", new Categories());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String addCategory(@ModelAttribute("category") Categories categories) {
		catService.addCat(categories);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String update(@PathVariable("id") Long id,Model model) {
		Optional<Categories>category=catService.findCat(id);
		if(category.isPresent()) {
			model.addAttribute("category", category);
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		catService.deleteCat(id);
		return "redirect:/admin/categories";
	}
	
	//section of products
	
	@GetMapping("/admin/products")
	public String  getProd(Model model) {
		model.addAttribute("products",productService.getAll());
		return "products";
	}
	@GetMapping ("/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("productDTO", new ProductDto());
	    model.addAttribute("categories", catService.getAll());
	    return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String addProducts(
			@ModelAttribute("productDTO") ProductDto productDto,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName") String image) throws IOException {
		
		  Products products=new Products();
		  products.setId(productDto.getId());
		  products.setDescription(productDto.getDescription());
		  products.setName(productDto.getName());
		  products.setPrice(productDto.getPrice());
		  products.setWeight(productDto.getWeight());
		  products.setCategories(catService.findCat(productDto.getCategoryId()).get());
		 String imageUUM;
		 if(!file.isEmpty()) {
			 imageUUM=file.getOriginalFilename();
			 Path fileNameAndPath=Paths.get(uploadDir, imageUUM);
			 Files.write(fileNameAndPath,file.getBytes());
		 }else {
			 imageUUM=image;
		 }
		 
		 products.setImageName(imageUUM);
		 productService.addProduct(products);
		return"redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deletProduct(@PathVariable("id") Long id) {
		productService.removeProduct(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(Model model,@PathVariable("id") Long id) {
		Products product =productService.getProduct(id).get();
		ProductDto prOptional=new ProductDto();
		prOptional.setDescription(product.getDescription());
		prOptional.setCategoryId(product.getCategories().getId());
		prOptional.setId(product.getId());
		prOptional.setImageName(product.getImageName());
		prOptional.setName(product.getName());
		prOptional.setPrice(product.getPrice());
		prOptional.setWeight(product.getWeight());
		    model.addAttribute("categories", catService.getAll());
			model.addAttribute("productDTO", prOptional);
		    return "productsAdd";
	}
}
