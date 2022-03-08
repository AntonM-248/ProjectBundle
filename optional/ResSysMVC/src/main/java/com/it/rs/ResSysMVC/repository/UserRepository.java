package com.it.rs.ResSysMVC.repository;

import com.it.rs.ResSysMVC.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
