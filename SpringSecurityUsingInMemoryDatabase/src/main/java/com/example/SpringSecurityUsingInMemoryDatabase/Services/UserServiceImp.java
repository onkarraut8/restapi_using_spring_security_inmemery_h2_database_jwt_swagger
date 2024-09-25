package com.example.SpringSecurityUsingInMemoryDatabase.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringSecurityUsingInMemoryDatabase.Dao.UserRepository;
import com.example.SpringSecurityUsingInMemoryDatabase.Modal.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User login(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow();

		if (user == null) {
			throw new RuntimeException("Invalid credentials");
		}
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		throw new RuntimeException("Invalid credentials");
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}
