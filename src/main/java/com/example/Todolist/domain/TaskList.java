package com.example.Todolist.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
@Entity
public class TaskList {
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

