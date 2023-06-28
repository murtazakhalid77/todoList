package com.example.Todolist.service;

import com.example.Todolist.domain.Task;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        try {
            Optional<Task> t = taskRepository.getByDescription(task.getDescription());
            if (!t.isPresent())
                return taskRepository.save(task);
        } catch (Exception ex) {
            throw new RuntimeException("Error Occurred");
        }
        update(task);
        throw new RuntimeException("Task Already Present");
    }

    public Task update(Task task) {
        Task task2 = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + task.getId()));
        task2.setDescription(task.getDescription());
        task2.setComment(task.getComment());
        task2.setTime(task.getTime());

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskByDescription(String description) {
        return taskRepository.getByDescription(description);
    }

    public String deleteTaskById(Long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);
            if (task.isPresent()) {
                taskRepository.deleteById(id);
                return "Task Deleted Successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error Occurred");
        }

        throw new RuntimeException("Task not deleted");
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingResourceOptional = taskRepository.findById(id);

        if (existingResourceOptional.isPresent()) {
            Task existingResource = existingResourceOptional.get();


            existingResource.setDescription(task.getDescription());
            existingResource.setComment(task.getComment());
            existingResource.setTime(task.getTime());

            return taskRepository.save(existingResource);
        }
        throw new RuntimeException("Resource not found for ID: " + id);
    }
}

