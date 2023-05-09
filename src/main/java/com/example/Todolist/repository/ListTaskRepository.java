package com.example.Todolist.repository;

import com.example.Todolist.domain.ListTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListTaskRepository extends JpaRepository<ListTask,Long> {

    public  void deleteByListIdAndTaskId(Long id, Long id1);
}
