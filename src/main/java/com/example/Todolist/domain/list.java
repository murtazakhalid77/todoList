package com.example.Todolist.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
public class list {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

////    @JsonIgnore
//    @ManyToMany(mappedBy = "list",cascade = CascadeType.ALL)
//    private Set<Task> tasks = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "list",cascade =CascadeType.ALL)
    private List<ListTask> listTasks;

}
