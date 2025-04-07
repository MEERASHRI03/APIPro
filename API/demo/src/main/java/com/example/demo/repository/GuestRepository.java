package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
    //JPA Methods
    public List<Guest> getByName(String name);
    
    //JPQL Methods
    @Query("SELECT g FROM Guest g WHERE pass=:Pass")
    public List<Guest> findByPassType(@Param("Pass") String pass);
    @Query("SELECT g FROM Guest g WHERE payment=:Payment")
    public List<Guest> findByPayment(@Param("Payment") String payment);
}
