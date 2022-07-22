package com.user.demo.service;

import com.user.demo.dto.UserProfileDTO;
import com.user.demo.exception.UserNotFoundException;

public interface UserService{

	public UserProfileDTO getUserByUserId(int userId);
	public UserProfileDTO createUser(UserProfileDTO dto)  throws Exception;
	public String login(int userId, String password);
	public String deleteUserByUserId(int userId)  throws UserNotFoundException;
	public String updateUser(int userId,  UserProfileDTO userDto) throws UserNotFoundException;
	
}
