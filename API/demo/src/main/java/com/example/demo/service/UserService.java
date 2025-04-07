package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    //API Methods
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Returns null if not found
    }
    
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            User existingUser = new User();
            existingUser.setEmail("Email already exists!"); // Setting message in the name field
            return existingUser;
        }
        return userRepository.save(user);
    }
    

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userdetails) {
        userdetails.setId(id);
        return userRepository.save(userdetails);
    }

    // Updated createUsers method for batch insert
    public List<User> createUsers(List<User> users) {
        // Save all users at once
        return userRepository.saveAll(users);
    }
    //Pagination
    public Page<User> getPageEmployee(@RequestParam int page,@RequestParam int size){
        PageRequest pageable=PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }
    //Sorting
    public List<User> getSorted(String designation)
    {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,designation));
    }
    //JPA Methods
    public List<User> findByDesignation(String designation)
    {
        return userRepository.findByDesignation(designation);
    }
    //JPQL Methods
    public List<User> findByUserName(String name){
        return userRepository.findByUserName(name);
    }
    
}