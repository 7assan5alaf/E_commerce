package com.hassan.ecomerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hassan.ecomerce.golbal.GolbalCart;
import com.hassan.ecomerce.model.Role;
import com.hassan.ecomerce.model.User;
import com.hassan.ecomerce.repository.RoleRepository;
import com.hassan.ecomerce.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	RoleRepository repository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/login")
	public String login() {
		GolbalCart.carts.clear();
		return "login";
	}
 
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	@GetMapping("/forgotpassword")
	public String forgetPassword() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		List<Role>roles=new ArrayList<Role>();
		roles.add(repository.findById(2l).get());
		user.setRoles(roles);
		userRepository.save(user);
		request.login(user.getEmail(),user.getPassword());
		return "redirect:/";
	}
	
	
}
