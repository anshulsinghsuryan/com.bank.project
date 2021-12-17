package com.bank.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.application.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("UPDATE User u SET u.role =:role WHERE u.id =:id")
	@Transactional
	@Modifying
	public void updateRole(@Param("role") String role, @Param("id") Long id);
	
	@Query("UPDATE User u SET u.status =:status WHERE u.id =:id")
	@Transactional
	@Modifying
	public void updateStatus(@Param("status") String status, @Param("id") Long id);
	
	public List<User> findUserByRole(String role);
	
	public List<User> findUserByStatus(String status);
}
