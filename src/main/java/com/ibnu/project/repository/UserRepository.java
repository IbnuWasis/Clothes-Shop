package com.ibnu.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
