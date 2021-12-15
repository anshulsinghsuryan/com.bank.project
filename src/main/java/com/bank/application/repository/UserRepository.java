package com.bank.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.application.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
