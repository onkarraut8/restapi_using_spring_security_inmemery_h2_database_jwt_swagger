package com.example.SpringSecurityUsingInMemoryDatabase.Services;


import java.util.List;

import com.example.SpringSecurityUsingInMemoryDatabase.Modal.User;

public interface UserService {

	public User register(User request);

	public List<User> getUsers();

}
