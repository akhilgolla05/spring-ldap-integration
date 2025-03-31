package com.example.ad_integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapAuthController {
	
	@GetMapping("/")
	public String index() {
		return "Hello, You have successfully Logged in";
	}

}
