package com.example.swiftcourier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.User;

/**
 * 
 * Repository that extends JPA -> with regard to User DAO
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * This method returns User from Database
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
}