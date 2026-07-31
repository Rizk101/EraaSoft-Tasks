package com.item.service;

import com.item.model.User;

public interface UserService {

	boolean register(User user);

	User authenticate(String email, String password);

	boolean resetPassword(String email, String newPassword);

	boolean deleteUserById(Long id);
}
