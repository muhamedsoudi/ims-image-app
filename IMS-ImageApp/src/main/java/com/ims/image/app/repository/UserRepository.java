package com.ims.image.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.image.app.model.User;
import com.ims.image.app.util.constant.UserTypeEnum;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	List<User> findByUserType(UserTypeEnum userType);
}
