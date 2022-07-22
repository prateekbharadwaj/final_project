package com.user.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.demo.dto.LoginDTO;
import com.user.demo.dto.UserProfileDTO;
import com.user.demo.entity.UserProfile;
import com.user.demo.exception.UserNotFoundException;
import com.user.demo.repository.UserProfileRepository;

@Service
public class UserServiceImpl implements UserService {
     

	 private UserProfileRepository userRepo;
	 @Autowired
	 public void setUserRepo(UserProfileRepository userRepo) {
			this.userRepo = userRepo;
	}
	
	 @Override
	public UserProfileDTO getUserByUserId(int userId) {
		UserProfileDTO returnUserDTO = new UserProfileDTO();
		UserProfile userProfileByUserId = userRepo.findByUserId(userId);
		 if (userProfileByUserId == null)
		 { 
			 //throw new UsernameNotFoundException(userId);
		 }
		 BeanUtils.copyProperties(userProfileByUserId, returnUserDTO);
		 
		return returnUserDTO;
	}

	@Override
	public UserProfileDTO createUser(UserProfileDTO dto) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(dto);
		Optional<UserProfile> optUserProfile = userRepo.findByPersonalIdentificationNumber(dto.getPersonalIdentificationNumber());
		if(optUserProfile.isPresent()) {
			throw new Exception("User is already present");
		}
		System.out.println("After Exception");
		UserProfile userProfile = UserProfileDTO.convertDTOToEntity(dto);
		userRepo.save(userProfile);
		return dto;
	}

	@Override
	public String login(int userId, String password) {
		// TODO Auto-generated method stub
		System.out.println("USERID"+userId+"PASS"+password);
		System.out.println(userRepo.findByUserIdAndPassword(userId, password));
		Optional<UserProfile> optUserProfile = userRepo.findByUserIdAndPassword(userId, password);
		System.out.println(optUserProfile);
		if(optUserProfile.isPresent() ) {
			return "Logged in successfully";
		}else {
			return "Invalid username and password";
		}
	}

	@Override
	public String deleteUserByUserId(int userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		UserProfile userProfile = userRepo.findByUserId(userId);
		System.out.println(userProfile);
		if( userProfile == null ) {
			throw new UserNotFoundException("User doesnt exist");
		}
		userRepo.delete(userProfile);
		
		return "User Deleted Successfully";
	}

	@Override
	public String updateUser(int userId, UserProfileDTO userDto) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		UserProfile updateUserProfile = userRepo.findByUserId(userId);
		
		if(updateUserProfile == null) {
			throw new UserNotFoundException("User Doesnt Exist");
		}
		updateUserProfile.setMobileNumber(userDto.getMobileNumber());
		updateUserProfile.setPermanentAddress(userDto.getPermanentAddress());
		updateUserProfile.setOfficeAddress(userDto.getOfficeAddress());
		
		userRepo.save(updateUserProfile);
		
		return "Mobilenumber, permanentAddress and OfficeAddress details are updated successfully for UserId "+userId;
	}

}
