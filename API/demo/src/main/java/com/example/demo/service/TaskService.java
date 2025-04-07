package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskrepository;
    
    //API Methods
    public List<Task> getAllTasks(){
        return taskrepository.findAll();
    }
    public Task getTaskById(Long id) {
        return taskrepository.findById(id).orElse(null); // Returns null if not found
    }
    public Task createTask(Task task)
    {
        return taskrepository.save(task);
    }
    //Post Multiple Values
    public List<Task> createTasks(List<Task> tasks)
    {
        return taskrepository.saveAll(tasks);
    }
    public void deleteTask(Long id)
    {
        taskrepository.deleteById(id);
    }
    public Task updateTask(Long id,Task taskdetails)
    {
        taskdetails.setId(id);
        return taskrepository.save(taskdetails);
    }

    //Pagination
    public Page<Task> getPageTask(@RequestParam int page,@RequestParam int size){
        PageRequest pageable=PageRequest.of(page, size);
        return taskrepository.findAll(pageable);
    }

    //Sorting
    public List<Task> getSorted(String taskName)
    {
        return taskrepository.findAll(Sort.by(Sort.Direction.ASC,taskName));
    }
    //JPA Methods
    public List<Task> getByTaskName(String taskName)
    {
        return taskrepository.getByTaskName(taskName);
    }
    //JPQL Methods 
    public List<Task> findByTaskStatus(String taskStatus)
    {
        return taskrepository.findByTaskStatus(taskStatus);
    }
}
