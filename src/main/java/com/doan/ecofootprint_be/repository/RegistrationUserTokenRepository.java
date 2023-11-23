package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.RegistrationUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface RegistrationUserTokenRepository extends JpaRepository<RegistrationUserToken, Integer> {

	public RegistrationUserToken findByToken(String token);

	public boolean existsByToken(String token);
	
	@Query("	SELECT 	token	"
			+ "	FROM 	RegistrationUserToken "
			+ " WHERE 	users.id = :userId")
	public String findByUserId(int userId);

	@Transactional
	@Modifying
	@Query("	DELETE 							"
			+ "	FROM 	RegistrationUserToken 	"
			+ " WHERE 	users.id = :userId")
	public void deleteByUserId(int userId);

}
