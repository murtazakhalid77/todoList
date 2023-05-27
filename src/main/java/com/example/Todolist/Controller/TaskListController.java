package com.example.Todolist.Controller;

import com.example.Todolist.domain.TaskList;
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
//    @PostMapping("/list/{listId}/{taskId}")
//    private ResponseEntity<ListTask> addTaskInList(@PathVariable Long listId, @PathVariable Long taskId){
//        return ResponseEntity.ok(listTaskService.addTaskInList(listId,taskId));
//    }

    @PostMapping("/taskSave/taskList")
    private ResponseEntity<TaskList> addTaskInList2(@RequestBody TaskList taskList){
        return ResponseEntity.ok(listTaskService.addTaskInList(taskList));
    }
    @GetMapping("todoList/{listName}")
    private ResponseEntity<List<TaskList>> getTaskByListName(@PathVariable(name="listName") String listName){
        return ResponseEntity.ok(listTaskService.getTasksByListName(listName));
    }

    @DeleteMapping("/list/{listId}/{taskId}")
    private void deleteTaskFromList(@PathVariable Long listId, @PathVariable Long taskId){
         listTaskService.deleteTaskFromList(listId,taskId);
    }

}
