package com.hassan.ecomerce.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hassan.ecomerce.model.Role;
import com.hassan.ecomerce.model.User;
import com.hassan.ecomerce.repository.RoleRepository;
import com.hassan.ecomerce.repository.UserRepository;

@Component
public class GoogleAuth2SuccessHandler implements AuthenticationSuccessHandler {

	 @Autowired
	 RoleRepository repository;
	 @Autowired
	 UserRepository userRepository;
	 
	 private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findByEmail(email).isPresent()) {
			
		}else {
			User user=new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<Role>roles=new ArrayList<Role>();
			roles.add(repository.findById(2l).get());
			 user.setRoles(roles);
			 userRepository.save(user);
		}
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
