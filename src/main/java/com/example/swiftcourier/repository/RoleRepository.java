package com.example.swiftcourier.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.Role;


/**
 * 
 * Repository that extends JPA -> with regard to Role DAO
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	

}
