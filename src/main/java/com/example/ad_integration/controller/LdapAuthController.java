package com.example.ad_integration.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapAuthController {
	
	@GetMapping("/")
	public String getUserInformation(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(userDetails);
		String userName =  userDetails.getUsername();
		return "Hello "+ userName+", You have successfully Logged in.";
	}




}
