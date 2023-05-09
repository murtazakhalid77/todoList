package com.example.Todolist.repository;

import com.example.Todolist.domain.list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<list,Long> {
    public list getByName(String name);
}
