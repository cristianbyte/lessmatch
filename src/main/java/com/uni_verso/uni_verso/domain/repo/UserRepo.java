package com.uni_verso.uni_verso.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_verso.uni_verso.domain.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
}
