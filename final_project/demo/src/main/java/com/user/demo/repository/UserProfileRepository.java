package com.user.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.demo.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
    UserProfile findByemailId(String emailId);
	UserProfile findByUserId(int userId);
	Optional<UserProfile> findByPersonalIdentificationNumber(Long personalIdentificationNumber);
	
	//@Query("select up from UserProfile up where up.userId=?1 and up.password=?2")
	Optional<UserProfile> findByUserIdAndPassword(int userId,String password);

}
