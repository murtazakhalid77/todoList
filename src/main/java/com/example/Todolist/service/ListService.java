package com.example.Todolist.service;

import com.example.Todolist.domain.list;
import com.example.Todolist.repository.TaskRepository;
import com.example.Todolist.repository.ListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final TaskRepository taskRepository;

    public ListService(ListRepository listRepository, TaskRepository taskRepository) {
        this.listRepository = listRepository;
        this.taskRepository = taskRepository;
    }

    public List<list> getAll() {
        return listRepository.findAll();
    }

    public list saveList(list list) {
        try {
            Optional<list> l = listRepository.getByName(list.getName());
            if (!l.isPresent())
                return listRepository.save(list);
        } catch (Exception e) {
            throw new RuntimeException("Error Occurred");
        }
        throw new RuntimeException("List already Present");
    }

//    public void addTaskInList(Long listId, Long taskId) {
//        Optional<list> toDoList = toDoListRepository.findById(listId);
//        Optional<Task> task = taskRepository.findById(taskId);
//        if(toDoList.isPresent()&&task.isPresent()){
//            toDoList.get().getTasks().add(task.get());
//            task.get().getList().add(toDoList.get());
////            taskRepository.save(task.get());
//            list savedList =  toDoListRepository.save(toDoList.get());
//            taskRepository.save(task.get());
//            return toDoList.get();
//        }
//        throw  new RuntimeException("List does not Exist!");
//        return
//    }


    public list getListById(Long id) {
        Optional<list> list = listRepository.findById(id);
        if (list.isPresent()) {
            return list.get();
        }
        throw new RuntimeException("List not found");
    }

//    public List<list> deleteTaskFromList(Long listId, Long taskId) {
//        Optional<list> toDoList = toDoListRepository.findById(listId);
//        Optional<Task> task = taskRepository.findById(taskId);
//        if(task.isPresent()&&toDoList.isPresent()){
////            toDoList.get().getTasks().remove(task.get());
//            task.get().getList().remove(toDoList.get());
////            System.out.println();
//            toDoListRepository.save(toDoList.get());
//            taskRepository.save(task.get());
//            return toDoListRepository.findAll();
//        }
//        throw  new RuntimeException("Task was not deleted");
//    }

//    public list getListByName(String name) {
//        return listRepository.getByName(name);
//    }

    public String deleteListById(Long id) {
        try {
            Optional<list> toDoList = listRepository.findById(id);
            if (toDoList.isPresent()) {
                listRepository.deleteById(id);
                return "List Deleted Successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error Occurred");
        }

        throw new RuntimeException("List not deleted");
    }

}