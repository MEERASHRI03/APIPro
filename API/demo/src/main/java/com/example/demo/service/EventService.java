package com.example.demo.service;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Service
public class EventService {
    @Autowired
    EventRepository eventrepository;

    // Regex for event name validation (Only letters and spaces, min 8 chars)
    private static final String EVENT_NAME_REGEX = "^[A-Za-z ]{8,}$";
    private static final Pattern EVENT_NAME_PATTERN = Pattern.compile(EVENT_NAME_REGEX);

    // Regex for event type validation (Only 'Concert' or 'Music Festival')
    private static final String EVENT_TYPE_REGEX = "^(Concert|Music Festival)$";
    private static final Pattern EVENT_TYPE_PATTERN = Pattern.compile(EVENT_TYPE_REGEX);

    // API Methods
    public List<Event> getAllEvents() {
        return eventrepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventrepository.findById(id).orElse(null);
    }
    
    public Event createEvent(Event event) {
        validateEvent(event);  // Now validating event before saving
        return eventrepository.save(event);
    }

    public List<Event> createEvents(List<Event> events) {
        for (Event event : events) {
            validateEvent(event);  // Validate each event in bulk-create
        }
        return eventrepository.saveAll(events);
    }

    public void deleteEvent(Long id) {
        eventrepository.deleteById(id);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        validateEvent(eventDetails);  //Validate before updating
        eventDetails.setId(id);
        return eventrepository.save(eventDetails);
    }

    // Pagination
    public Page<Event> getPageEmployee(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return eventrepository.findAll(pageable);
    }

    // Sorting
    public List<Event> getSorted(String eventName) {
        return eventrepository.findAll(Sort.by(Sort.Direction.ASC, eventName));
    }

    // JPA Methods
    public List<Event> getByDate(String date) {
        return eventrepository.getByDate(date);
    }

    // JPQL Methods
    public List<Event> findByEventType(String eventType) {
        return eventrepository.findByEventType(eventType);
    }

    // Custom Exception Class for Event Validation
    public class InvalidEventException extends RuntimeException {
        public InvalidEventException(String message) {
            super(message);
        }
    }

    // Validation Methods
    private void validateEvent(Event event) {
        if (!isValidEventName(event.getEventName())) {
            throw new InvalidEventException("Invalid event name! Must contain only letters and spaces, at least 8 characters.");
        }
        if (!isValidEventType(event.getEventType())) {
            throw new InvalidEventException("Invalid event type! Must be 'Concert' or 'Music Festival'.");
        }
    }

    private boolean isValidEventName(String eventName) {
        return eventName != null && EVENT_NAME_PATTERN.matcher(eventName).matches();
    }

    private boolean isValidEventType(String eventType) {
        return eventType != null && EVENT_TYPE_PATTERN.matcher(eventType).matches();
    }
}
