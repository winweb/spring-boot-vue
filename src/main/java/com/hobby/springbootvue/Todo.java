package com.hobby.springbootvue;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    private Long id;

    @NonNull
    private String title;

    private Boolean completed;

    public Boolean getCompleted(){
        return completed == null ? false : completed;
    }
}