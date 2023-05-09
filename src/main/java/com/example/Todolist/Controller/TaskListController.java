package com.example.Todolist.Controller;

import com.example.Todolist.domain.ListTask;
import com.example.Todolist.domain.list;
import com.example.Todolist.service.ListTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskListController {
    @Autowired
    ListTaskService listTaskService;
    @PostMapping("/list/{listId}/{taskId}")
    private ResponseEntity<ListTask> addTaskInList(@PathVariable Long listId, @PathVariable Long taskId){
        return ResponseEntity.ok(listTaskService.addTaskInList(listId,taskId));
    }

    @DeleteMapping("/list/{listId}/{taskId}")
    private void deleteTaskFromList(@PathVariable Long listId, @PathVariable Long taskId){
         listTaskService.deleteTaskFromList(listId,taskId);
    }

}
