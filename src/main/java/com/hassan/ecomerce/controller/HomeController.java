package com.hassan.ecomerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hassan.ecomerce.golbal.GolbalCart;
import com.hassan.ecomerce.model.Products;
import com.hassan.ecomerce.service.CatService;
import com.hassan.ecomerce.service.ProductService;


@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	CatService catService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	public String getProducts(Model model ,String word) {
		return listPages(model,1,word);
	}
	@GetMapping("/page/{curnetPage}")
	public String listPages(Model model,@PathVariable("curnetPage") int page,@Param(value = "keyword") String keyword) {
		Page<Products>pages=productService.getAll(page,keyword);
		List<Products>products=pages.getContent();
		long totalItems=pages.getTotalElements();
		int totalPages=pages.getTotalPages();
		model.addAttribute("curnetPage", page);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("products", products);
		model.addAttribute("keyword", keyword);
		model.addAttribute("categories", catService.getAll());
        model.addAttribute("cartCount", GolbalCart.carts.size());
		return "shop";
	}
//	@GetMapping("/shop")
//	public String viewShop(Model model) {
//	  model.addAttribute("products", productService.getAll());
//	  model.addAttribute("categories", catService.getAll());
//	  model.addAttribute("cartCount", GolbalCart.carts.size());
//		return "shop";
//	}
	@GetMapping("/shop/category/{id}")
	public String viewProducts(@PathVariable("id") Long id,Model model) {
		 model.addAttribute("categories", catService.getAll());
		 model.addAttribute("products", productService.getAllByCategory(id));
		  return "shop";
	}

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable("id") Long id,Model model) {
    	model.addAttribute("product", productService.getProduct(id).get());
    	model.addAttribute("cartCount", GolbalCart.carts.size());
    	return "viewProduct";
    }
}
