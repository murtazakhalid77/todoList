package com.example.Todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String comment;
    @JsonIgnore
    @OneToMany(mappedBy = "task",cascade =CascadeType.ALL)
    private List<ListTask> listTasks;
}