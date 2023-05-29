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
            if(!t.isPresent())
                return taskRepository.save(task);
        }catch (Exception ex){
            throw new RuntimeException("Error Occurred");
        }
        update(task);
        throw new RuntimeException("Task Already Present");
    }
    public  Task update(Task task){
        Task task2 = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + task.getId()));
        task2.setDescription(task.getDescription());
        task2.setComment(task.getComment());
        task2.setDueDate(task.getDueDate());
        task2.setTime(task.getTime());
        task2.setStatus(task.isStatus());

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskByDescription(String description){
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
