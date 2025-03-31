package com.example.ad_integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	 @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	      .authorizeHttpRequests((authorize) -> authorize
	        .anyRequest().fullyAuthenticated()
	      )
	      .formLogin(Customizer.withDefaults());

	    return http.build();
	  }
	 
	 @Bean
	 public LdapTemplate ldapTemplate() {
		 return new LdapTemplate(contextSource());
	 }

	 @Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl("ldap://localhost:389/");
		//contextSource.setUserDn("cn=admin,dc=srs,dc=com");
		//contextSource.setPassword("admin");
		return contextSource;
	}
	 
	 @Bean
	 public AuthenticationManager authenticationManager(BaseLdapPathContextSource source) {
		 LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
		 factory.setUserDnPatterns("cn={0},ou=users,ou=srs,dc=srs,dc=com");
		 return factory.createAuthenticationManager();
	 }
	


}
