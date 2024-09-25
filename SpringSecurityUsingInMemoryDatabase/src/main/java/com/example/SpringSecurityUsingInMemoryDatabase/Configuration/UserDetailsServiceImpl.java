package com.example.SpringSecurityUsingInMemoryDatabase.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringSecurityUsingInMemoryDatabase.Dao.UserRepository;
import com.example.SpringSecurityUsingInMemoryDatabase.Modal.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user =
	 * userRepository.findByUsername(username); if (user == null) { throw new
	 * UsernameNotFoundException("User not found"); } // Create a Single
	 * SimpleGrantedAuthority from the single role CustomUserDetail
	 * customUserDetail=new CustomUserDetail(user);
	 * 
	 * return customUserDetail; }
	 */

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRoles())
	                .build();
	    }
	
	 /* @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
	    }*/
}
