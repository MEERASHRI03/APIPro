package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    //JPA Methods
    public List<User> findByDesignation(String designation);

    //JPQL Methods
    @Query("SELECT u FROM User u where u.name=:Name")
    public List<User> findByUserName(@Param("Name") String name);

    
}