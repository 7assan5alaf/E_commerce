package com.hassan.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hassan.ecomerce.golbal.GolbalCart;
import com.hassan.ecomerce.model.Products;
import com.hassan.ecomerce.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addCart(@PathVariable("id") Long id,Model model) {
		GolbalCart.carts.add(productService.getProduct(id).get());
		return "redirect:/shop";
	}
	@GetMapping("/cart")
	public String getCart(Model model) {
		model.addAttribute("cartCount", GolbalCart.carts.size());
		model.addAttribute("cart", GolbalCart.carts);
		model.addAttribute("total", GolbalCart.carts.stream().mapToDouble(Products::getPrice));
		return "cart";
	}
	@GetMapping("/cart/removeItem/{index}")
	public String removeItem(@PathVariable("index")int index) {
		GolbalCart.carts.remove(index);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String getCheckout(Model model) {
		model.addAttribute("total", GolbalCart.carts.stream().mapToDouble(Products::getPrice));
		return"checkout";
	}
}
