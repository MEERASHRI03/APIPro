package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //JPA Methods
    public List<Task> getByTaskName(String taskName);

    //JPQL Methods
    @Query("SELECT t FROM Task t WHERE t.taskStatus=:TaskStatus")
    public List<Task> findByTaskStatus(@Param("TaskStatus") String taskStatus);

    
}
