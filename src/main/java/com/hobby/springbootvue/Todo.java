package com.hobby.springbootvue;

import lombok.*;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity  
@Data  
@NoArgsConstructor
public class Todo {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  

  @NonNull
  private String title;  

  private Boolean completed = false;
      
}