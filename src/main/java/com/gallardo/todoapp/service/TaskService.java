package com.gallardo.todoapp.service;

import com.gallardo.todoapp.exceptions.ToDoExceptions;
import com.gallardo.todoapp.mapper.TaskInDTOToTask;
import com.gallardo.todoapp.persistence.entity.Task;
import com.gallardo.todoapp.persistence.entity.TaskStatus;
import com.gallardo.todoapp.persistence.repository.TaskRepository;
import com.gallardo.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    //toda logica de negocio aqui

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll() {
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {

        Optional<Task> optionalTask = this.repository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskFinished(id);
    }

    public void deleteById(Long id) {

        Optional<Task> optionalTask = this.repository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }


}
