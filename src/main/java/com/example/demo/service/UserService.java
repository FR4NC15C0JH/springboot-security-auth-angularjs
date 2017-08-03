package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
	public User findUserByEmail(String email);

	public User findUserByEmailAndPassword(String email, String password);
	
	public User getUser(Long id);

	public void saveUser(User user);
}
