package com.example.ad_integration.config;

import com.example.ad_integration.utils.CustomLdapAuthoritiesPopulator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	 @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 return http
				 .csrf(AbstractHttpConfigurer::disable)
				 .cors(AbstractHttpConfigurer::disable)
				 .authorizeHttpRequests(auth->
						 auth.requestMatchers("/auth/login","/auth/signup")
								 .permitAll().anyRequest().authenticated())
				 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .httpBasic(Customizer.withDefaults())
				 .build();
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
	 public AuthenticationManager authenticationManager(BaseLdapPathContextSource source, CustomLdapAuthoritiesPopulator authoritiesPopulator) {
		 LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
		 factory.setUserDnPatterns("cn={0},ou=users,ou=srs,dc=srs,dc=com");
		 factory.setLdapAuthoritiesPopulator(authoritiesPopulator);
		 return factory.createAuthenticationManager();
	 }

	 @Bean
	 public PasswordEncoder passwordEncoder(){
		 return new BCryptPasswordEncoder();
	 }
	


}
