package com.hassan.ecomerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hassan.ecomerce.model.CustomUser;
import com.hassan.ecomerce.model.User;
import com.hassan.ecomerce.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	 @Autowired
	 UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>optional=repository.findByEmail(username);
		optional.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		return optional.map(CustomUser::new).get();
		
	}

}
