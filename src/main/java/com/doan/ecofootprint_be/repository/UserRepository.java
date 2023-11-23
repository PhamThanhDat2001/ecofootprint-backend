package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
//public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

//	public boolean existsByUserName(String userName);

	public boolean existsByEmail(String email);
	
	@Query("	SELECT 	status 		"
			+ "	FROM 	Users 		"
			+ " WHERE 	email = :email")
	public Users.UserStatus findStatusByEmail(String email);

	public Users findByUsername(String name);
	
	public Users findByEmail(String email);
}
