package com.example.Todolist.service;

import com.example.Todolist.domain.Task;
import com.example.Todolist.domain.list;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
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
            Task t = taskRepository.getByDescriptionAndComment(task.getDescription(),task.getComment());
            if(t==null)
                return taskRepository.save(task);
        }catch (Exception ex){
            throw new RuntimeException("Error Occurred");
        }
        throw new RuntimeException("Task Already Present");
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getTaskByDescriptionAndComment(String description, String comment){
        return taskRepository.getByDescriptionAndComment(description,comment);
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

    public Task updateTask(Long id, Map<String, Object> fields) {
        Task task = taskRepository.findById(id).get();
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Task.class,key);

            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,task,value);
        });
        return taskRepository.save(task);
    }
}
