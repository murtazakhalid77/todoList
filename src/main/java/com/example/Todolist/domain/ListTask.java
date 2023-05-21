package com.example.Todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
@Entity
public class ListTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "listId",referencedColumnName = "id")
    private list list;
    @ManyToOne
    @JoinColumn(name = "taskId",referencedColumnName = "id")
    private   Task task;
}

