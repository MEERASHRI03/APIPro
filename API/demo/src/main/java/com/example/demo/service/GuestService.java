package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Guest;
import com.example.demo.repository.GuestRepository;

@Service
public class GuestService {
    @Autowired
    GuestRepository guestrepository;

    //API Methods
    public List<Guest> getAllGuests(){
        return guestrepository.findAll();
    }
    public Guest getGuestById(Long id) {
        return guestrepository.findById(id).orElse(null); // Returns null if not found
    }
    public Guest createGuest(Guest guest)
    {
        return guestrepository.save(guest);
    }
    //Post Multiple Values
    public List<Guest> createGuests(List<Guest> guests)
    {
        return guestrepository.saveAll(guests);
    }
    public void deleteGuest(Long id)
    {
        guestrepository.deleteById(id);
    }
    public Guest updateGuest(Long id,Guest guestdetails)
    {
        guestdetails.setId(id);
        return guestrepository.save(guestdetails);
    }

    //Pagination
    public Page<Guest> getPageGuests(@RequestParam int page,@RequestParam int size){
        PageRequest pageable=PageRequest.of(page, size);
        return guestrepository.findAll(pageable);
    }

    //Sorting
    public List<Guest> getSorted(String name){
        return guestrepository.findAll(Sort.by(Sort.Direction.ASC,name));
    }
    //JPA Methods
    public List<Guest> getByName(String name)
    {
        return guestrepository.getByName(name);
    }
    //JPQL Methods
    public List<Guest> findByPassType(String pass)
    {
        return guestrepository.findByPassType(pass);
    }
    public List<Guest> findByPayment(String payment)
    {
        return guestrepository.findByPayment(payment);
    }
}
