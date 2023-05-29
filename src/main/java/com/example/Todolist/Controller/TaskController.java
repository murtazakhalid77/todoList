package com.example.Todolist.Controller;

import com.example.Todolist.domain.Task;
import com.example.Todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    private ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }
    @PostMapping("/saveTask/task")
    private ResponseEntity<Task> save(@RequestBody Task task){
        return ResponseEntity.ok(taskService.save(task));
    }

    @GetMapping("/task/{description}")
    private ResponseEntity<Optional<Task>> getTaskByDescription(@PathVariable("description") String description){
        return ResponseEntity.ok(taskService.getTaskByDescription(description));

    }
    @DeleteMapping("/task/deleteTask/{id}")
    private ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.deleteTaskById(id));

    }
    @PatchMapping("/task/updateTask/{id}")
    private ResponseEntity<Task> updateTaskById(@PathVariable Long id,Map<String, Object> fields){
        return ResponseEntity.ok(taskService.updateTask(id,fields));
    }

}
