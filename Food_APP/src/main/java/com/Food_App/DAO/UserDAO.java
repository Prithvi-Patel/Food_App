package com.Food_App.DAO;

import java.util.List;

import com.Food_App.Models.User;

public interface UserDAO {
	List<User> getAllUsers();
	User getUserById(int userId);
	void addUser(User u);
	void updateUser(User u);
	void deleteUser(int userId);
	User getUserByUsername(String username);
	
}
