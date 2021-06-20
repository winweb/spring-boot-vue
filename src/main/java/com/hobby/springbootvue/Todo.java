package com.hobby.springbootvue;

import lombok.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  

  @NonNull
  private String title;

  private Boolean completed;

  public Boolean getCompleted(){
    return completed == null ? false : completed;
  }
}