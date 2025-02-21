package com.lessmatch.lessmatch.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lessmatch.lessmatch.domain.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
}
