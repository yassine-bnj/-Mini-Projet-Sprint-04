package com.example.livres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	
	  

	    @Autowired 
        PasswordEncoder passwordEncoder; 
	    private CustomUserDetailsService userDetailsService;

	    /*@Autowired
	    public SecurityConfig(CustomUserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	        
	    }*/
	    
		
		 protected void configure(AuthenticationManagerBuilder auth) throws
		Exception {
		 auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		 }
	

	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    	httpSecurity.csrf().disable();
	    	
	       httpSecurity.authorizeHttpRequests().requestMatchers("/showCreate").hasAnyAuthority("ADMIN","AGENT");
		        
	       httpSecurity.authorizeHttpRequests().requestMatchers("/saveLivre").hasAnyAuthority("ADMIN","AGENT");
	       httpSecurity.authorizeHttpRequests().requestMatchers("/ListeLivres").hasAnyAuthority("ADMIN","AGENT","USER");
	        httpSecurity.authorizeHttpRequests().
	        requestMatchers("/supprimerLivre","/updateLivre","/modifierLivre","/showCreateType"
	        		,"/saveType","/ListeTypes","/supprimerType","/modifierType","/updateType"
	        		).hasAuthority("ADMIN");
	       
	        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**", "/login", "/register", "/register/save").permitAll();
	       httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
	       httpSecurity.formLogin()
	       .loginPage("/login")
			.defaultSuccessUrl("/ListeLivres", true)
			.loginProcessingUrl("/login")
			.failureUrl("/login?error=true")
			.permitAll();
	       httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	       
	       httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
	       return httpSecurity.build();
	    }

	    @Bean
		  public PasswordEncoder passwordEncoder () {
		  return new BCryptPasswordEncoder();
		  }
	     

	 
	  
}
