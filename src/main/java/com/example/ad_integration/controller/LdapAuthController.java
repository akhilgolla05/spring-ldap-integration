package com.example.ad_integration.controller;

import com.example.ad_integration.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LdapAuthController {

	private final AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return ResponseEntity.ok("Hello "+ userDetails.getUsername()+" Logged in Successfully "+" your role is : "+userDetails.getAuthorities());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
		}
	}
	
//	@GetMapping("/")
//	public String getUserInformation(Authentication authentication) {
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		System.out.println(userDetails);
//		String userName =  userDetails.getUsername();
//		return "Hello "+ userName+", You have successfully Logged in.";
//	}




}
