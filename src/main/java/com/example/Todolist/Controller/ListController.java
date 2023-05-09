package com.example.Todolist.Controller;


import com.example.Todolist.domain.list;
import com.example.Todolist.service.ListService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ListController {
    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping("/list/getAll")
    private ResponseEntity<List<list>> getAll(){

        return ResponseEntity.ok(listService.getAll());
    }

//    @GetMapping("/list-tasks")
//    private ResponseEntity<Set<Task>> getListTasks(@RequestBody ToDoList list){
//        return ResponseEntity.ok(toDoListService.getListTasks(list));
//    }
    @GetMapping("/list/listById/{id}")
    private ResponseEntity<list> getListById(@PathVariable Long id){
        return ResponseEntity.ok(listService.getListById(id));
    }
    @PostMapping("/list/saveList")
    private ResponseEntity<list> save(@RequestBody list list){
        return ResponseEntity.ok(listService.saveList(list));
    }

//    @PutMapping("/list/{listId}/{taskId}")
//    private ResponseEntity<list> addTaskInList(@PathVariable Long listId, @PathVariable Long taskId){
//        return ResponseEntity.ok(toDoListService.addTaskInList(listId,taskId));
//    }
//
//    @DeleteMapping("/list/{listId}/{taskId}")
//    private ResponseEntity<List<list>> deleteTaskFromList(@PathVariable Long listId, @PathVariable Long taskId){
//        return ResponseEntity.ok(toDoListService.deleteTaskFromList(listId,taskId));
//    }

    @GetMapping("/list/getByName/{listName}")
    private ResponseEntity<list> getListByName(@PathVariable String listName){
        return ResponseEntity.ok(listService.getListByName(listName));
    }
    @DeleteMapping("/list/deleteList/{id}")
    private ResponseEntity<String> deleteListByid(@PathVariable Long id){
        return ResponseEntity.ok(listService.deleteListById(id));
    }

}
