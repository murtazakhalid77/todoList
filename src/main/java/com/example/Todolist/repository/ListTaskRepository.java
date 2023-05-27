package com.example.Todolist.repository;

import com.example.Todolist.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListTaskRepository extends JpaRepository<TaskList,Long> {

    public  void deleteByListIdAndTaskId(Long id, Long id1);

    @Query(value = "SELECT * FROM task_list WHERE list_id = ?1", nativeQuery = true)
    List<TaskList> findByListId(long listId);
}
