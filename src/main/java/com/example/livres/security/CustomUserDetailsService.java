package com.example.livres.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.livres.entities.Role;
import com.example.livres.entities.User;
import com.example.livres.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;

    
    
    @Autowired
    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRole()))
                            .collect(Collectors.toList())
            );
            System.out.println("user = "+ authUser.toString());
            return authUser;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }*/
    
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws
    UsernameNotFoundException {
    	User user = userRepo.findByUsername(userName);
    	if (user==null)
    	 throw new UsernameNotFoundException("Utilisateur introuvable !");
    	
    	List<GrantedAuthority> auths = new ArrayList<>();
    	
    	auths = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRole()))
        .collect(Collectors.toList());
    	
    	
    	
    		 for(Role R : user.getRoles()) {
    			 System.out.println("role == "+ R);
    			 GrantedAuthority auhority = new SimpleGrantedAuthority(R.getRole());
    			 auths.add(auhority);
    		 }
    	
    	System.out.println("roles = "+ auths);

    	
    	
    	return new org.springframework.security.core.
    	userdetails.User(user.getUsername(),user.getPassword(),auths);
    	 }
    
    
    
    
    
    
    
    
    
}
